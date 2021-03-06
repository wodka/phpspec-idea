package at.ms07.phpspec.idea.plugin.psi.element;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.php.lang.psi.elements.MethodReference;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import at.ms07.phpspec.idea.plugin.psi.exceptions.InvalidArgumentException;
import at.ms07.phpspec.idea.plugin.psi.exceptions.MissingElementException;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public abstract class MethodDecorator {

	protected final MethodReference element;
	protected PhpClass target;
	protected Object returnType = null;

	public MethodDecorator(PsiElement element) throws InvalidArgumentException {
		if (!(element instanceof MethodReference)) {
			throw new InvalidArgumentException("Passed PsiElement should be an instance of MethodReference");
		}

		this.element = (MethodReference) element;
	}

	public PhpClass getTarget() {
		if (target == null) {
			target = PsiTreeUtil.getParentOfType(element, PhpClass.class);
		}

		return target;
	}

	public boolean hasParameter(int no) {
		return (element.getParameters().length > no);
	}

	public boolean hasParameter(int no, Class type) {
		return (hasParameter(no) && type.isAssignableFrom(element.getParameters()[no].getClass()));
	}

	public PsiElement getParameter(int no) {
		return element.getParameters()[no];
	}

	public boolean isResolvableToType() {
		try {
			return (getReturnType() != null);
		} catch (MissingElementException e) {
			return false;
		}
	}

	public Object getReturnType() throws MissingElementException {
		if (returnType == null) {
			returnType = resolveType();
		}

		return returnType;
	}

	protected abstract Object resolveType() throws MissingElementException;

}
