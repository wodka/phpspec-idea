package at.ms07.phpspec.idea.plugin.command;

import com.intellij.openapi.project.Project;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public abstract class PhpSpecCommand {

	protected final Project project;

	public PhpSpecCommand(Project project) {
		this.project = project;
	}

	protected abstract String getProgressTitle();

//    protected abstract String getActionName();

}
