package uk.co.kayratech.m2m.platform.model.lov;

import java.util.Locale;

public enum Language {
	ENGLISH {

		@Override
		public Locale getLocale() {
			return new Locale("en");
		}
	},
	TURKISH {

		@Override
		public Locale getLocale() {
			return new Locale("tr");
		}
	};

	public abstract Locale getLocale();

	public static Language getLanguageFromLocale(Locale locale) {
		Language[] values = Language.values();
		Language language = null;
		for (int i = 0; i < values.length; i++) {
			if (locale.getLanguage().equals(values[i].getLocale().getLanguage())) {
				language = values[i];
				break;
			}
		}
		return language;
	}
	
	public static Language getDefaultLanguage() {
		return Language.ENGLISH;
	}
}
