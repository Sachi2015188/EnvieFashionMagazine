package team.envie.fashion.enviefashion.chain.main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;

import java.util.Set;

/**
 * ChainPreference
 *
 * ChainPreference cp = new ChainPreference(context or activity);
 * cp.save(something key, something value);
 * cp.get~~~~(key);
 *
 * @param <T>
 */
public class ChainPreference<T extends ChainPreference> implements team.envie.fashion.enviefashion.chain.core.ChainPreferenceInterface<ChainPreference<T>> {

    private final ChainPreference self = this;
    private Context mContext;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private static final String PREFERECE = "PREF";

    /**
     * Constructor
     * @param context - context
     * @param key - String key
     */
    public ChainPreference(Context context, String key) {
        mContext = context;
        mPreferences = mContext.getSharedPreferences(key, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    /**
     * Constructor
     * @param context - activity
     * @param key - String key
     */
    public ChainPreference(Activity context, String key) {
        mContext = context;
        mPreferences = mContext.getSharedPreferences(key, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    /**
     * Constructor
     * @param context
     * @param key
     */
    public ChainPreference(FragmentActivity context, String key) {
        mContext = context;
        mPreferences = mContext.getSharedPreferences(key, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    /**
     * Constructor
     * @param context - context
     */
    public ChainPreference(Context context) {
        mContext = context;
        mPreferences = mContext.getSharedPreferences(PREFERECE, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    /**
     * Constructor
     * @param context - activity
     */
    public ChainPreference(Activity context) {
        mContext = context;
        mPreferences = mContext.getSharedPreferences(PREFERECE, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    /**
     * Constructor
     * @param context - FramgnetActivity
     */
    public ChainPreference(FragmentActivity context) {
        mContext = context;
        mPreferences = mContext.getSharedPreferences(PREFERECE, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }




    // *******************************************************************
    // Method - save
    // *******************************************************************
    /**
     * save(String key, String value);
     * @param key - String key
     * @param value - String value
     * @return self
     */
    @Override
    public ChainPreference<T> save(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
        return self;
    }

    /**
     * save(String key, int value)
     * @param key - String key
     * @param value - int value
     * @return self
     */
    @Override
    public ChainPreference<T> save(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
        return self;
    }

    /**
     * save(String key, boolean value)
     * @param key - String key
     * @param value - boolean value
     * @return
     */
    @Override
    public ChainPreference<T> save(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
        return self;
    }

    /**
     * save(String key, float value)
     * @param key - String key
     * @param value - float value
     * @return
     */
    @Override
    public ChainPreference<T> save(String key, float value) {
        mEditor.putFloat(key, value);
        mEditor.commit();
        return self;
    }

    /**
     * save(String key, long value)
     * @param key - String kye
     * @param value - long value
     * @return
     */
    @Override
    public ChainPreference<T> save(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.commit();
        return self;
    }

    /**
     * save(String key, Set<String> value)
     * @param key - String key
     * @param value - Set<String> value
     * @return
     */
    @Override
    public ChainPreference<T> save(String key, Set<String> value) {
        mEditor.putStringSet(key, value);
        mEditor.commit();
        return self;
    }


    // *******************************************************************
    // Method - get()
    // *******************************************************************
    /**
     * getInt(String key)
     * @param key
     * @return
     */
    @Override
    public int getInt(String key) {
        int value = mPreferences.getInt(key, -1);
        return value;
    }

    /**
     * getString(String key)
     * @param key
     * @return
     */
    @Override
    public String getString(String key) {
        return mPreferences.getString(key, "");
    }

    /**
     * getBoolean(String key)
     * @param key
     * @return
     */
    @Override
    public boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }

    /**
     * getFloat(String key)
     * @param key
     * @return
     */
    @Override
    public float getFloat(String key) {
        return mPreferences.getFloat(key, -1f);
    }

    /**
     * getLong(String key)
     * @param key
     * @return
     */
    @Override
    public long getLong(String key) {
        return mPreferences.getLong(key, -1L);
    }

    /**
     * getSet(String key)
     * @param key
     * @return
     */
    @Override
    public Set<String> getSet(String key) {
        Set<String> set = null;
        return mPreferences.getStringSet(key, set);
    }
}
