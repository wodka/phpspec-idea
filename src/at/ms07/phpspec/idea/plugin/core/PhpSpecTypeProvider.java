package at.ms07.phpspec.idea.plugin.core;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import com.jetbrains.php.lang.psi.elements.Variable;
import org.jetbrains.annotations.Nullable;
import at.ms07.phpspec.idea.plugin.code.type.GenericTypeProvider;
import at.ms07.phpspec.idea.plugin.psi.exceptions.MissingElementException;
import at.ms07.phpspec.idea.plugin.PhpSpecProject;
import at.ms07.phpspec.idea.plugin.core.services.PhpSpecLocator;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecTypeProvider extends GenericTypeProvider {

	private PhpSpecLocator locator = null;

	@Nullable
	@Override
	protected String getTypeFor(PsiElement element) {

		PhpSpecLocator locator1 = getLocator(element);
		PhpClass phpClass = PsiTreeUtil.getParentOfType(element, PhpClass.class);

		if (!(element instanceof Variable) || !((Variable) element).getName().equals("this")
				|| (phpClass == null) || !locator1.isSpec(phpClass)) {
			return null;
		}

		try {
			PhpSpecDescribedClass spec = locator1.locateDescriptionFor(phpClass);

			return spec.getDecoratedObject().getFQN();
		} catch (MissingElementException e) {
		}

		return null;
	}

	private PhpSpecLocator getLocator(PsiElement element) {
		if (locator == null) {
			locator = element.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecLocator.class);
		}

		return locator;
	}
}
