package language;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Manages application language settings and provides access
 * to localized text resources.
 */
public class LanguageManager {
	
	private static ResourceBundle bundle;
	
	/**
	 * Sets the application language using language and country codes.
	 *
	 * @param language language code (e.g. "en", "pl")
	 * @param country country code (e.g. "US", "PL")
	 */
	public static void setLanguage(String language, String country) {
        bundle = ResourceBundle.getBundle("language", new Locale(language, country));
    }

	/**
	 * Returns a localized value assigned to the given key.
	 *
	 * @param key resource key to search for
	 * @return localized text value
	 */
    public static String get(String key) {
        return bundle.getString(key);
    }
}
