package at.ms07.phpspec.idea.plugin.psi.reference;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.ArrayUtil;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import com.jetbrains.php.lang.psi.elements.StringLiteralExpression;
import com.jetbrains.php.lang.psi.elements.impl.StringLiteralExpressionImpl;
import org.jetbrains.annotations.NotNull;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpClassReference implements PsiReference {

	private PsiElement reference;
	private PsiElement element;

	public PhpClassReference(PsiElement reference, PsiElement element) {
		this.reference = reference;
		this.element = element;
	}

	/**
	 * @return the full range incl. quotes.
	 * @see com.intellij.psi.PsiReference#getRangeInElement()
	 */
	@Override
	public TextRange getRangeInElement() {
		return new TextRange(1, this.element.getTextLength() - 1);
	}

	/**
	 * @return the resolved template file or null
	 * @see com.intellij.psi.PsiReference#resolve()
	 */
	@Override
	public PsiElement resolve() {
		return reference;
	}

	/**
	 * @return Plain text representation.
	 * @see com.intellij.psi.PsiReference#getCanonicalText()
	 */
	@NotNull
	@Override
	public String getCanonicalText() {
		return ((StringLiteralExpression) this.element).getContents();
	}

	/**
	 * @param newElementName
	 * @return the new string literal with the new text
	 * @throws IncorrectOperationException
	 * @see com.intellij.psi.PsiReference#handleElementRename(String)
	 */
	@Override
	public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
		ASTNode node = this.element.getNode();
		StringLiteralExpressionImpl se = new StringLiteralExpressionImpl(node);

		String[] parts = this.element.getText().split(":");
		StringBuilder sb = new StringBuilder(parts[0]).append(":").append(parts[1]).append(":");
		sb.append(newElementName);
		se.updateText(sb.toString());
		this.element = se;
		return this.element;
	}

	@Override
	public PsiElement bindToElement(@NotNull PsiElement psiElement) throws IncorrectOperationException {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean isReferenceTo(PsiElement element) {
		if (element instanceof PhpClass) {
			((PhpClass) element).getName().equals(((StringLiteralExpression) this.element).getContents());
		}
		return false;
	}


	@NotNull
	@Override
	public Object[] getVariants() {
		return ArrayUtil.EMPTY_OBJECT_ARRAY;
	}

	@Override
	public boolean isSoft() {
		return true;
	}

	/**
	 * @return the full source element
	 * @see com.intellij.psi.PsiReference#getElement()
	 */
	@Override
	public PsiElement getElement() {
		return element;
	}
}
