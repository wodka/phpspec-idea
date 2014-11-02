package at.ms07.phpspec.idea.plugin.composer.converter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import at.ms07.phpspec.idea.plugin.composer.Configuration;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class ConfigurationConverter implements JsonDeserializer<Configuration> {
	@Override
	public Configuration deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		Configuration config = new Configuration();

		for (Map.Entry<String, JsonElement> element : jsonElement.getAsJsonObject().entrySet()) {
			config.set(element.getKey(), element.getValue().getAsString());
		}

		return config;
	}
}
