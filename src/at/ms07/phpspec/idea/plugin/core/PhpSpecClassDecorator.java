package at.ms07.phpspec.idea.plugin.core;

import com.jetbrains.php.lang.psi.elements.PhpClass;
import at.ms07.phpspec.idea.plugin.ProjectComponent;
import at.ms07.phpspec.idea.plugin.psi.element.PhpClassDecorator;
import at.ms07.phpspec.idea.plugin.PhpSpecProject;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public abstract class PhpSpecClassDecorator extends PhpClassDecorator {

	/**
	 * Create PhpClass decorator
	 *
	 * @param phpClass
	 */
	public PhpSpecClassDecorator(PhpClass phpClass) {
		super(phpClass);
	}

	@Override
	protected ProjectComponent getComponent() {
		return getDecoratedObject().getProject().getComponent(PhpSpecProject.class);
	}
}
