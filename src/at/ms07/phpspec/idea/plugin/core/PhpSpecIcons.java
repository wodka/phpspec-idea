package at.ms07.phpspec.idea.plugin.core;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecIcons {

	public static final javax.swing.Icon Icon = load("/at/ms07/phpspec/idea/plugin/icons/phpspec_16_16.png");
	public static final Icon File = load("/at/ms07/phpspec/idea/plugin/icons/phpspec_file_16_16.png");

	private static Icon load(String path) {
		return IconLoader.getIcon(path, PhpSpecIcons.class);
	}

}
