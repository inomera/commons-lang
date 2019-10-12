package com.inomera.telco.commons.lang;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertyUtils {
	public static Properties copyProperties(Properties other) {
		final Properties properties = new Properties();
		properties.putAll(other);
		return properties;
	}

	public static Properties overrideWithSystemArguments(final Properties properties) {
		properties.keySet().forEach(key -> overrideProperty(properties, (String) key));
		return properties;
	}

	private static void overrideProperty(final Properties properties, final String key) {
		properties.compute(key, (k, v) -> {
			String newValue = System.getProperty(key);
			return Optional.ofNullable(newValue).filter(StringUtils::isNotBlank).orElse((String) v);
		});
	}
}
