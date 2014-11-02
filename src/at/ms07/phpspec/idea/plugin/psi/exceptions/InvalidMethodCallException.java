package at.ms07.phpspec.idea.plugin.psi.exceptions;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class InvalidMethodCallException extends Exception {
	public InvalidMethodCallException(String message) {
		super(message);
	}
}
