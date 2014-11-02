package at.ms07.phpspec.idea.plugin.psi.exceptions;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class MissingElementException extends Exception {
	public MissingElementException(String message) {
		super(message);
	}
}
