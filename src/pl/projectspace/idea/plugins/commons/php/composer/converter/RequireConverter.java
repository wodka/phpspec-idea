package pl.projectspace.idea.plugins.commons.php.composer.converter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import pl.projectspace.idea.plugins.commons.php.composer.Package;
import pl.projectspace.idea.plugins.commons.php.composer.Require;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class RequireConverter implements JsonDeserializer<Require> {
	@Override
	public Require deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		Require require = new Require();

		for (Map.Entry<String, JsonElement> element : jsonElement.getAsJsonObject().entrySet()) {
			require.addPackage(new Package(element.getKey(), element.getValue().getAsString()));
		}

		return require;
	}
}
