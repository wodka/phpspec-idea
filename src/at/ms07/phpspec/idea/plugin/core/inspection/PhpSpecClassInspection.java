package at.ms07.phpspec.idea.plugin.core.inspection;

import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import com.jetbrains.php.lang.psi.visitors.PhpElementVisitor;
import org.jetbrains.annotations.NotNull;
import at.ms07.phpspec.idea.plugin.code.inspection.PhpClassInspection;
import at.ms07.phpspec.idea.plugin.psi.exceptions.MissingElementException;
import at.ms07.phpspec.idea.plugin.utils.PhpClassUtils;
import at.ms07.phpspec.idea.plugin.utils.annotation.DependsOnPlugin;
import at.ms07.phpspec.idea.plugin.PhpSpecProject;
import at.ms07.phpspec.idea.plugin.core.inspection.actions.GenerateSpecForClassFix;
import at.ms07.phpspec.idea.plugin.core.services.PhpSpecLocator;

@DependsOnPlugin("phpspec")
public class PhpSpecClassInspection extends PhpClassInspection {

	@Override
	protected PhpElementVisitor getVisitor(@NotNull ProblemsHolder holder) {
		return new ClassInspector(holder);
	}

	public final class ClassInspector extends PhpElementVisitor {

		private ProblemsHolder holder;

		private PhpSpecLocator locator;

		public ClassInspector(ProblemsHolder holder) {
			this.holder = holder;
			this.locator = holder.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecLocator.class);
		}

		@Override
		public void visitPhpClass(PhpClass phpClass) {
			if (phpClass.isInterface() || phpClass.isAbstract()) {
				return;
			}

			if (!locator.isSpec(phpClass)) {
				try {
					locator.locate(locator.getSpecFQNFor(phpClass));
				} catch (MissingElementException e) {
					LeafPsiElement id = PhpClassUtils.getClassNameIdentifierFrom(phpClass);
					holder.registerProblem(id, "Missing spec for class: " + phpClass.getName(), new GenerateSpecForClassFix(phpClass));
				}
			}
		}
	}

}
