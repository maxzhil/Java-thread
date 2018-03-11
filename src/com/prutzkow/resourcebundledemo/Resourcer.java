package com.prutzkow.resourcebundledemo;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 
 * @version 1.4
 * 
 */
public class Resourcer {
	public static final String MISSING_PARAMETER_VALUE_MESSAGE_FORMAT = "Value for parameter \"%s\" is missing";

	private static final String DEFAULT_PROPERTY_NAME = "resources.text";
	private static String basename = DEFAULT_PROPERTY_NAME;

	private static ResourceBundle resources = ResourceBundle.getBundle(
			Resourcer.basename, Locale.getDefault());

	private Resourcer() {
	}

	public static void createResourcer() {
		Resourcer.createResourcer(null);
	}

	public static void createResourcer(String basename) {
		if (basename != null) {
			if (!basename.isEmpty()) {
				if (!Resourcer.basename.equals(basename)) {
					Resourcer.basename = basename;
					Resourcer.resources = ResourceBundle.getBundle(
							Resourcer.basename, Locale.getDefault());
				}
			}
		}

		if (isLocaleChanged()) {
			Resourcer.resources = ResourceBundle.getBundle(Resourcer.basename,
					Locale.getDefault());

		}
	}

	private static boolean isLocaleChanged() {
		Locale systemLocale = Locale.getDefault();
		Locale resourcerLocale = Resourcer.resources.getLocale();
		
		return (!resourcerLocale.equals(systemLocale));
	}

	public static String getString(String parameter) {
		Resourcer.createResourcer();
		String resourceValue;

		try {
			resourceValue = Resourcer.resources.getString(parameter);
		} catch (MissingResourceException e) {
			resourceValue = String
					.format(Resourcer.MISSING_PARAMETER_VALUE_MESSAGE_FORMAT,
							parameter);
		}

		return resourceValue;
	}
}
