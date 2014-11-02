package at.ms07.phpspec.idea.plugin.composer.converter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PackageConverter implements JsonDeserializer<at.ms07.phpspec.idea.plugin.composer.Package> {
	@Override
	public at.ms07.phpspec.idea.plugin.composer.Package deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		return new at.ms07.phpspec.idea.plugin.composer.Package();
	}
}
