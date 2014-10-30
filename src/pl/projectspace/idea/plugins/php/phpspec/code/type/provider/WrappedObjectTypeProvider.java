package pl.projectspace.idea.plugins.php.phpspec.code.type.provider;

import com.intellij.openapi.project.DumbService;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.php.lang.psi.elements.ClassReference;
import com.jetbrains.php.lang.psi.elements.MethodReference;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import org.jetbrains.annotations.Nullable;
import pl.projectspace.idea.plugins.commons.php.code.type.GenericTypeProvider;
import pl.projectspace.idea.plugins.php.phpspec.core.PhpSpecClass;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class WrappedObjectTypeProvider extends GenericTypeProvider {

	@Nullable
	@Override
	public String getTypeFor(PsiElement psiElement) {
		if ( psiElement == null ) {
			return null;
		}

		if (DumbService.isDumb(psiElement.getProject())) {
			return null;
		}

		if (psiElement instanceof ClassReference) {
			ClassReference method = (ClassReference) psiElement;
			/*if (!method.getName().equals("getWrappedObject")) {
				return null;
			}*/

			PhpClass phpClass = PsiTreeUtil.getContextOfType(psiElement, PhpClass.class, false);
			PhpSpecClass specClass = new PhpSpecClass(phpClass);
/*
			System.out.println("===================");
			System.out.println(phpClass.toString());
			System.out.println(phpClass.getName());
			System.out.println(phpClass.getNamespaceName());
			System.out.println(phpClass.getFQN());
			System.out.println("spec Class: "+specClass.getDecoratedObject().getFQN());
*/
			return specClass.getDecoratedObject().getFQN();
		}

		return getTypeFor(psiElement.getPrevSibling());
	}

}
