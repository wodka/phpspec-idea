package at.ms07.phpspec.idea.plugin.action;

import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.php.lang.PhpFileType;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import at.ms07.phpspec.idea.plugin.action.PhpClassAction;
import at.ms07.phpspec.idea.plugin.utils.PhpClassUtils;
import at.ms07.phpspec.idea.plugin.utils.annotation.PluginAction;
import at.ms07.phpspec.idea.plugin.PhpSpecProject;
import at.ms07.phpspec.idea.plugin.core.services.FileFactory;
import at.ms07.phpspec.idea.plugin.core.services.PhpSpecLocator;

import java.io.IOException;
import java.util.Properties;

@PluginAction("phpspec")
public class CreateSpecForClassAction extends PhpClassAction {

	public void perform(final PhpClass phpClass) {
		try {
			createSpecFor(phpClass);
		} catch (Exception e1) {
			Messages.showErrorDialog(phpClass.getProject(), "Failed creating spec for given class.", "Failed Creating Spec File.");
		}
	}

	@Override
	protected boolean isAvailable(final PhpClass phpClass) {
		return
				phpClass != null && super.isAvailable(phpClass) &&
						!phpClass.getProject().getComponent(PhpSpecProject.class)
								.getService(PhpSpecLocator.class).isSpec(phpClass);
	}


	protected void createSpecFor(PhpClass phpClass) throws IOException {
		Project project = phpClass.getProject();
		PhpSpecLocator locator = project.getComponent(PhpSpecProject.class).getService(PhpSpecLocator.class);

		String className = locator.getSpecNameFor(phpClass);
		String namespace = locator.getSpecNamespaceFor(phpClass.getFQN());

		Properties properties = FileTemplateManager.getInstance().getDefaultProperties();
		properties.setProperty("CLASS_NAME", className);
		properties.setProperty("IMPLEMENTATION", phpClass.getFQN());
		properties.setProperty("NAMESPACE", namespace);

		String specPath = PhpClassUtils.getPSRPathFromClassNamespace(namespace);

		VirtualFile baseDir = project.getBaseDir();
		VirtualFile dir = baseDir.findFileByRelativePath(specPath);

		if (dir == null) {
			dir = project.getComponent(PhpSpecProject.class).getService(FileFactory.class)
					.createNamespaceDirectory(baseDir, specPath);
		}

		ApplicationManager.getApplication().runWriteAction(
				project.getComponent(PhpSpecProject.class).getService(FileFactory.class)
						.getCreateFileFromTemplateWriteAction(
								dir,
								className.concat(".php"),
								PhpFileType.INSTANCE,
								"Spec Class.php",
								properties
						)
		);
	}

}
