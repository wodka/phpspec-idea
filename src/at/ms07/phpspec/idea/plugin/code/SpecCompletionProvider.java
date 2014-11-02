package at.ms07.phpspec.idea.plugin.code;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.jetbrains.php.completion.PhpLookupElement;
import com.jetbrains.php.lang.psi.elements.Method;
import org.jetbrains.annotations.NotNull;
import at.ms07.phpspec.idea.plugin.code.completion.GenericCompletionProvider;
import at.ms07.phpspec.idea.plugin.utils.annotation.DependsOnPlugin;
import at.ms07.phpspec.idea.plugin.PhpSpecProject;
import at.ms07.phpspec.idea.plugin.core.services.PhpSpecStaticCompletionProvider;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 * @author Michael Schramm <michael.schramm@gmail.com>
 */
@DependsOnPlugin("phpspec")
public class SpecCompletionProvider extends GenericCompletionProvider {

	protected void addCompletionsFor(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet resultSet) {
		PsiElement element = parameters.getPosition();

		/*
		 * keep annotations for however deep we are
		if (element.getPrevSibling() == null || element.getPrevSibling().getPrevSibling() == null
				|| !(element.getPrevSibling().getPrevSibling() instanceof Variable)) {
			return;
		}
		*/

		PhpSpecStaticCompletionProvider staticCompletionProvider
				= element.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecStaticCompletionProvider.class);

		String dir = parameters.getOriginalFile().getContainingDirectory().toString();
		String file = parameters.getOriginalFile().getName();

		if(
			dir.contains("spec")
			&& file.contains("Spec")
		) {
			String original_class = "";

			original_class += dir.split("spec")[1];
			original_class += "/";
			original_class += file.replace("Spec", "").replace(".class.php", "").replace(".php", "");
			original_class = original_class.replace('/', '\\');

			for (Method method : staticCompletionProvider.getMethodsFor(original_class)) {
				resultSet.addElement(new PhpLookupElement(method));
			}

			// this must be inside of check -> else we have spec auto completion outside as well (what we do not want!)
			for (Method method : staticCompletionProvider.getMethodsFor(PhpSpecStaticCompletionProvider.OBJECT_BEHAVIOUR_CLASS)) {
				resultSet.addElement(new PhpLookupElement(method));
			}
		}
	}
}
