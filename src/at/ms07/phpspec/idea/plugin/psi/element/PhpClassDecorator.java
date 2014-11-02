package at.ms07.phpspec.idea.plugin.psi.element;

import com.jetbrains.php.lang.psi.elements.PhpClass;
import at.ms07.phpspec.idea.plugin.ProjectComponent;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public abstract class PhpClassDecorator extends PsiElementDecorator {

	/**
	 * Create PhpClass decorator
	 *
	 * @param phpClass
	 */
	public PhpClassDecorator(PhpClass phpClass) {
		super(phpClass);
	}

	/**
	 * Get decorated PhpClass instance
	 *
	 * @return
	 */
	public PhpClass getDecoratedObject() {
		return (PhpClass) this.element;
	}

	public <T> T getService(Class<T> service) {
		return getComponent().getService(service);
	}

	@Override
	public String toString() {
		return getDecoratedObject().getFQN();
	}

	public abstract boolean hasRelatedClass();

	protected abstract ProjectComponent getComponent();

}
