package at.ms07.phpspec.idea.plugin.psi.element;

import com.intellij.psi.PsiElement;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public abstract class PsiElementDecorator {

	protected PsiElement element;

	/**
	 * Create PhpClass decorator
	 *
	 * @param element
	 */
	public PsiElementDecorator(PsiElement element) {
		this.element = element;
	}

	/**
	 * Get decorated Element instance
	 *
	 * @return
	 */
	public PsiElement getDecoratedObject() {
		return this.element;
	}

}
