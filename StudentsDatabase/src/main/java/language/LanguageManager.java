package language;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
	private static ResourceBundle bundle;
	
	public static void setLanguage(String language, String country) {
        bundle = ResourceBundle.getBundle("language", new Locale(language, country));
    }

    public static String get(String key) {
        return bundle.getString(key);
    }
}
