package se.mariaochlove.fridaymastermix.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton that reads and writes shared preferences in a non locking manner.
 *
 * @author Love Löfdahl.
 */
public enum SharedPreferencesManager {
    INSTANCE;

    private static final String TAG = SharedPreferencesManager.class.getSimpleName();
    private static final String SHARED_PREFERENCES_NAME = "FRIDAY_MASTER_MIX";

    public static final String SPOTIFY_TOKEN = "SPOTIFY_TOKEN";
    public static final String SPOTIFY_TOKEN_EXPIRES = "SPOTIFY_TOKEN_EXPIRES";
    public static final String SPOTIFY_TOKEN_CREATED = "SPOTIFY_TOKEN_CREATED";
    public static final String SPOTIFY_REFRESH_TOKEN = "SPOTIFY_REFRESH_TOKEN";

    private Map<String, String> map;

    /**
     * Returns the singleton instance.
     *
     * @return the singleton instance.
     */
    public static SharedPreferencesManager getInstance() {
        return INSTANCE;
    }

    /**
     * Private constructor.
     */
    SharedPreferencesManager() {
        map = new HashMap<>();
    }

    /**
     * Loads all shared preferences into a map in a {@link Thread} and notifies the provided callback
     * when done. This should only be called once!
     *
     * @param context the context that is used to read the preferences.
     * @param callback the callback that will be notified when the preferences have been loaded.
     */
    public synchronized void readAllPreferences(final Context context, final Callback callback) {
        new Thread() {
            @Override
            public void run() {
                Log.d(TAG, "Reading preferences on thread: " + Thread.currentThread());
                SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

                map.put(SPOTIFY_TOKEN, preferences.getString(SPOTIFY_TOKEN, null));
                map.put(SPOTIFY_TOKEN_EXPIRES, preferences.getString(SPOTIFY_TOKEN_EXPIRES, "0"));
                map.put(SPOTIFY_TOKEN_CREATED, preferences.getString(SPOTIFY_TOKEN_CREATED, "0"));

                Log.d(TAG, "Woke up. Will now call done.");
                callback.done();
            }
        }.start();
    }

    /**
     * Updates the value of a shared preference in the map and writes it to disk in a thread.
     *
     * @param name the name of the shared preference to update.
     * @param value the value of the shared preference.
     * @param context the context that will be used to write the shared preference.
     */
    public synchronized void updatePreference(final String name, final String value, final Context context) {
        map.put(name, value);
        new Thread() {
            @Override
            public void run() {
                SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
                editor.putString(name, value);
                editor.apply();
            }
        }.start();
     }

     /**
     * Returns the value of the shared preference from the map.
     *
     * @param name the name of the shared preference to return the value for.
     * @return the value of the shared preference.
     */
    public synchronized String getSharedPreferences(String name) {
        return map.get(name);
    }

    public interface Callback {
        void done();
    }
}
