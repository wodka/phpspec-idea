package at.ms07.phpspec.idea.plugin.code.type;

import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import com.jetbrains.php.lang.psi.elements.PhpNamedElement;
import com.jetbrains.php.lang.psi.resolve.types.PhpTypeProvider2;
import org.jetbrains.annotations.Nullable;
import at.ms07.phpspec.idea.plugin.StateComponentInterface;
import at.ms07.phpspec.idea.plugin.psi.PsiTreeUtils;
import at.ms07.phpspec.idea.plugin.utils.annotation.DependsOnPlugin;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public abstract class GenericTypeProvider implements PhpTypeProvider2 {

	@Override
	public char getKey() {
		return '\u0150';
	}

	/**
	 * Return context instance for given expression - FQN
	 *
	 * @param expression
	 * @param project
	 * @return
	 */
	@Override
	public Collection<? extends PhpNamedElement> getBySignature(String expression, Project project) {
		PsiTreeUtils obj = ServiceManager.getService(project, PsiTreeUtils.class);

		if (obj == null) {
			return Collections.emptyList();
		}

		PhpClass phpClass = obj.getClassByFQN(expression);

		if (phpClass == null) {
			return Collections.emptyList();
		}

		return Arrays.asList(phpClass);
	}


	@Nullable
	@Override
	public String getType(PsiElement element) {

		return getTypeFor(element);
	}

	protected boolean isEnabled(PsiElement element) {
		DependsOnPlugin annotation = this.getClass().getAnnotation(DependsOnPlugin.class);

		BaseComponent component = element.getProject().getComponent(annotation.value());
		if (component instanceof StateComponentInterface) {
			return ((StateComponentInterface) component).isEnabled();
		}

		return false;
	}

	protected abstract String getTypeFor(PsiElement psiElement);

}
