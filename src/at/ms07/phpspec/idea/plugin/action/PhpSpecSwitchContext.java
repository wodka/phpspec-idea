package at.ms07.phpspec.idea.plugin.action;

import com.intellij.ide.actions.OpenFileAction;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import at.ms07.phpspec.idea.plugin.action.PhpClassAction;
import at.ms07.phpspec.idea.plugin.psi.exceptions.MissingElementException;
import at.ms07.phpspec.idea.plugin.utils.annotation.PluginAction;
import at.ms07.phpspec.idea.plugin.PhpSpecProject;
import at.ms07.phpspec.idea.plugin.core.PhpSpecClass;
import at.ms07.phpspec.idea.plugin.core.PhpSpecClassDecorator;
import at.ms07.phpspec.idea.plugin.core.PhpSpecDescribedClass;
import at.ms07.phpspec.idea.plugin.core.services.PhpSpecFactory;

@PluginAction("phpspec")
public class PhpSpecSwitchContext extends PhpClassAction {

	@Override
	protected void perform(PhpClass phpClass) {
		PhpSpecClassDecorator currentContext = getCurrentContext(phpClass);

		if (currentContext == null) {
			// @todo - propose creation of missing spec / class
			return;
		}

		PhpClass destination = null;

		try {
			if (currentContext instanceof PhpSpecClass) {
				destination = ((PhpSpecClass) currentContext).getDescribedClass().getDecoratedObject();
			} else {
				destination = ((PhpSpecDescribedClass) currentContext).getSpecClass().getDecoratedObject();
			}
		} catch (MissingElementException e1) {
			return;
		}

		OpenFileAction.openFile(destination.getContainingFile().getVirtualFile(), phpClass.getProject());
	}

	@Override
	protected boolean isAvailable(final PhpClass phpClass) {
		try {
			return (super.isAvailable(phpClass) && getCurrentContext(phpClass).hasRelatedClass());
		} catch (NullPointerException e1) {
		}

		return false;
	}

	@Override
	protected String getLabel(PhpClass phpClass) {
		PhpSpecClassDecorator currentContext = getCurrentContext(phpClass);

		try {
			if (currentContext instanceof PhpSpecClass) {
				return "Go to: " + ((PhpSpecClass) currentContext).getDescribedClass().getDecoratedObject().getName() + " class";
			} else {
				return "Go to: " + currentContext.getDecoratedObject().getName() + " spec";
			}
		} catch (MissingElementException e) {
		} catch (NullPointerException e) {
		}

		return null;
	}

	private PhpSpecClassDecorator getCurrentContext(PhpClass phpClass) {
		try {
			PhpSpecFactory factory = phpClass.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecFactory.class);

			return factory.create(phpClass);
		} catch (NullPointerException e) {
			return null;
		}
	}
}
