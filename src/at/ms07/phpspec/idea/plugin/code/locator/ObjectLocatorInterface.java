package at.ms07.phpspec.idea.plugin.code.locator;

import at.ms07.phpspec.idea.plugin.psi.exceptions.MissingElementException;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public interface ObjectLocatorInterface {

	public <T> T locate(String name) throws MissingElementException;

}
