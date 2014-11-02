package at.ms07.phpspec.idea.plugin.code;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.PlatformPatterns;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class SpecCompletionContributor extends CompletionContributor {

	public SpecCompletionContributor() {
		SpecCompletionProvider p = new SpecCompletionProvider();

		extend(
				CompletionType.BASIC,
				PlatformPatterns.psiElement(), // TODO there seems to be a way to limit this to specific elements
				p
		);
	}

}
