package pl.projectspace.idea.plugins.php.phpspec.code;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.jetbrains.php.lang.psi.elements.Method;
import com.jetbrains.php.lang.psi.elements.Variable;
import org.jetbrains.annotations.NotNull;
import pl.projectspace.idea.plugins.commons.php.code.completion.GenericCompletionProvider;
import pl.projectspace.idea.plugins.commons.php.psi.lookup.SimpleTextLookup;
import pl.projectspace.idea.plugins.commons.php.utils.annotation.DependsOnPlugin;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PhpSpecStaticCompletionProvider;


/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
@DependsOnPlugin("phpspec")
public class SpecCompletionProvider extends GenericCompletionProvider {

	protected void addCompletionsFor(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet resultSet) {
		PsiElement element = parameters.getPosition();

		if (element.getPrevSibling() == null || element.getPrevSibling().getPrevSibling() == null
				|| !(element.getPrevSibling().getPrevSibling() instanceof Variable)) {
			return;
		}

		PhpSpecStaticCompletionProvider staticCompletionProvider
				= element.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecStaticCompletionProvider.class);

		for (Method method : staticCompletionProvider.getMethodsFor(PhpSpecStaticCompletionProvider.OBJECT_BEHAVIOUR_CLASS)) {
			resultSet.addElement(new SimpleTextLookup(method.getName()));
		}
	}
}
