package team.envie.fashion.enviefashion.chain.core;

import java.util.Set;

public interface ChainPreferenceInterface<T extends ChainPreferenceInterface> {

    /**
     *
     * @param key
     * @param value
     * @return
     */
    T save(String key, String value);

    /**
     *
     * @param key
     * @param value
     * @return
     */
    T save(String key, int value);

    /**
     *
     * @param key
     * @param value
     * @return
     */
    T save(String key, boolean value);

    /**
     *
     * @param key
     * @param value
     * @return
     */
    T save(String key, float value);

    /**
     *
     * @param key
     * @param value
     * @return
     */
    T save(String key, long value);

    /**
     *
     * @param key
     * @param value
     * @return
     */
    T save(String key, Set<String> value);

    /**
     *
     * @param key
     * @return
     */
    int getInt(String key);

    /**
     *
     * @param key
     * @return
     */
    String getString(String key);

    /**
     *
     * @param key
     * @return
     */
    boolean getBoolean(String key);

    /**
     *
     * @param key
     * @return
     */
    float getFloat(String key);

    /**
     *
     * @param key
     * @return
     */
    long getLong(String key);

    /**
     *
     * @param key
     * @return
     */
    Set<String> getSet(String key);
}
