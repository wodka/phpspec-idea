package at.ms07.phpspec.idea.plugin.command;

import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public abstract class AbstractCommand implements CommandInterface {


	private final Project project;
	private HashMap<String, String> namedParameters;
	private LinkedList<String> parameters;

	public AbstractCommand(Project project) {
		this.project = project;

		namedParameters = new HashMap<String, String>();
		parameters = new LinkedList<String>();
	}

	public HashMap<String, String> getNamedParams() {
		return namedParameters;
	}

	public List<String> getParams() {
		return parameters;
	}

	public void addNamedParameter(String name, String val) {
		namedParameters.put(name, val);
	}

	public void addParameter(String param) {
		parameters.add(param);
	}

	public Project getProject() {
		return project;
	}

	public String[] getCommandLineArgs() {
		List<String> params = new ArrayList<String>();

		params.add(getCommand());

		for (String key : namedParameters.keySet()) {
			if (key.length() == 1) {
				params.add("-" + key + " " + namedParameters.get(key));
			} else {
				params.add("--" + key + "=" + namedParameters.get(key));
			}
		}

		params.addAll(parameters);

		return params.toArray(new String[0]);
	}
}
