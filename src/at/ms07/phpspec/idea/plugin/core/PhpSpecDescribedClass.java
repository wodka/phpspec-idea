package at.ms07.phpspec.idea.plugin.core;

import com.jetbrains.php.lang.psi.elements.PhpClass;
import at.ms07.phpspec.idea.plugin.psi.exceptions.MissingElementException;
import at.ms07.phpspec.idea.plugin.core.services.PhpSpecLocator;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecDescribedClass extends PhpSpecClassDecorator {

	protected PhpSpecClass spec = null;

	public PhpSpecDescribedClass(PhpClass phpClass) {
		super(phpClass);
	}

	@Override
	public boolean hasRelatedClass() {
		try {
			getSpec();

			return true;
		} catch (MissingElementException e) {
			return false;
		}
	}

	public PhpSpecClass getSpecClass() throws MissingElementException {
		return getSpec();
	}

	protected PhpSpecClass getSpec() throws MissingElementException {
		if (spec == null) {
			spec = getService(PhpSpecLocator.class).locateSpecFor(getDecoratedObject());
		}

		return spec;
	}

}
