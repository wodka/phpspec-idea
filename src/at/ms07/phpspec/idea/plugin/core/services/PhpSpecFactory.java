package at.ms07.phpspec.idea.plugin.core.services;

import com.jetbrains.php.PhpIndex;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import at.ms07.phpspec.idea.plugin.core.PhpSpecClass;
import at.ms07.phpspec.idea.plugin.core.PhpSpecDescribedClass;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecFactory {

	private final PsiTreeUtils utils;
	private final PhpIndex index;
	private PhpSpecLocator locator;

	public PhpSpecFactory(PsiTreeUtils utils, PhpIndex index, PhpSpecLocator locator) {
		this.utils = utils;
		this.index = index;
		this.locator = locator;
	}

	public <T> T create(PhpClass phpClass) {
		if (locator.isSpec(phpClass)) {
			return (T) new PhpSpecClass(phpClass);
		} else {
			return (T) new PhpSpecDescribedClass(phpClass);
		}
	}

	public <T> T create(String fqn) {
		PhpClass phpClass = utils.getClassByFQN(fqn);
		return create(phpClass);
	}

}
