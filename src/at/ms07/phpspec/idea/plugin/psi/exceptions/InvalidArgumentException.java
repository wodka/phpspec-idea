package at.ms07.phpspec.idea.plugin.psi.exceptions;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class InvalidArgumentException extends Exception {
	public InvalidArgumentException(String message) {
		super(message);
	}
}
