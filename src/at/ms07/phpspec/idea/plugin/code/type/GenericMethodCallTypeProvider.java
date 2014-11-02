package at.ms07.phpspec.idea.plugin.code.type;

import com.intellij.openapi.project.DumbService;
import com.intellij.psi.PsiElement;
import com.jetbrains.php.lang.psi.elements.MethodReference;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import org.jetbrains.annotations.Nullable;
import at.ms07.phpspec.idea.plugin.psi.element.MethodDecorator;
import at.ms07.phpspec.idea.plugin.psi.element.PsiElementDecorator;
import at.ms07.phpspec.idea.plugin.psi.exceptions.InvalidArgumentException;
import at.ms07.phpspec.idea.plugin.psi.exceptions.MissingElementException;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public abstract class GenericMethodCallTypeProvider extends GenericTypeProvider {

	@Nullable
	@Override
	public String getTypeFor(PsiElement element) {
		if (DumbService.isDumb(element.getProject()) || !(element instanceof MethodReference)) {
			return null;
		}

		try {
			MethodDecorator method = (MethodDecorator) getMethod((MethodReference) element);

			if (!(((PsiElementDecorator) method.getReturnType()).getDecoratedObject() instanceof PhpClass)) {
				return null;
			}

			return method.getReturnType().toString();
		} catch (InvalidArgumentException e) {
			return null;
		} catch (MissingElementException e) {
			return null;
		}
	}

	protected abstract Object getMethod(MethodReference method) throws InvalidArgumentException, MissingElementException;
}
