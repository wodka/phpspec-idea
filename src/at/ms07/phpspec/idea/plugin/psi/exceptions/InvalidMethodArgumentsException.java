package at.ms07.phpspec.idea.plugin.psi.exceptions;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class InvalidMethodArgumentsException extends InvalidMethodCallException {
	public InvalidMethodArgumentsException(String message) {
		super(message);
	}
}
