package at.ms07.phpspec.idea.plugin.composer;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class Package {

	private String name;
	private String version;

	public Package() {
	}

	public Package(String name, String version) {
		this.name = name;
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

}
