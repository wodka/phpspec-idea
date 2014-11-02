package at.ms07.phpspec.idea.plugin.core.services;

import com.intellij.openapi.project.Project;
import com.jetbrains.php.PhpIndex;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PsiTreeUtils extends at.ms07.phpspec.idea.plugin.psi.PsiTreeUtils {
	public PsiTreeUtils(Project project, PhpIndex index) {
		super(project, index);
	}
}
