package at.ms07.phpspec.idea.plugin.core;

import com.jetbrains.php.lang.psi.elements.PhpClass;
import at.ms07.phpspec.idea.plugin.psi.exceptions.MissingElementException;
import at.ms07.phpspec.idea.plugin.core.services.PhpSpecLocator;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecClass extends PhpSpecClassDecorator {

	private PhpSpecDescribedClass klass;

	public PhpSpecClass(PhpClass phpClass) {
		super(phpClass);
	}

	@Override
	public boolean hasRelatedClass() {
		try {
			getPhpSpecDescribedClass();

			return true;
		} catch (MissingElementException e) {
		}

		return false;
	}

	public PhpSpecDescribedClass getDescribedClass() throws MissingElementException {
		return getPhpSpecDescribedClass();
	}

	protected PhpSpecDescribedClass getPhpSpecDescribedClass() throws MissingElementException {
		if (klass == null) {
			klass = getService(PhpSpecLocator.class).locateDescriptionFor(getDecoratedObject());
		}

		return klass;
	}
}
