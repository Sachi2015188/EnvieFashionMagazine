package team.envie.fashion.enviefashion.chain.core;


import java.io.File;

/**
 * ChainIntent Interface
 * @param <T>
 */
public interface ChainIntentInterface<T extends ChainIntentInterface<T>> {

    /** to ClassName */
    T name(Class<?> className);

    /** Intent Activity */
    T moveActivity();

    /** finish Activity */
    T finish();

    /** overridePending Activity */
    T overridePending(int enterAnim, int exitAnim);

    T actionView();

    T actionViewVideo(File file);

    T actionViewAudio(File file);
}
