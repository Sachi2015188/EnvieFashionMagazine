package team.envie.fashion.magazin.app.utils;

import android.os.Debug;
import android.util.Log;

import team.envie.fashion.magazin.app.BuildConfig;

/**
 * Logger Class
 *
 * @author Shuhei Iwamoto
 */
public class Logged {

    /**
     * @param msg
     */
    public static final void v(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(getTAG(), getMetaInfo() + ":" + msg);
        }
    }

    /**
     * @param msg
     */
    public static final void d(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(getTAG(), getMetaInfo() + ":" + msg);
        }
    }

    /**
     * log e
     *
     * @param msg
     * @param e   - new Throwable
     */
    public static final void e(String msg, Throwable e) {
        if (BuildConfig.DEBUG) {
            Log.e(getTAG(), getMetaInfo() + ":" + msg, e);
            printThrowable(e);
            if (e.getCause() != null) {
                printThrowable(e.getCause());
            }
        }
    }

    /**
     * log i
     */
    public static final void i(String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(getTAG(), getMetaInfo() + ":" + msg);
        }
    }

    /**
     * log w
     */
    public static final void w(String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(getTAG(), getMetaInfo() + ":" + msg);
        }
    }

    /**
     * log heap
     */
    public static final void heap() {
        if (BuildConfig.DEBUG) {
            String msg = "heap : Free=" + Long.toString(Debug.getNativeHeapFreeSize() / 1024) + "kb" +
                    ", Allocated=" + Long.toString(Debug.getNativeHeapAllocatedSize() / 1024) + "kb" +
                    ", Size=" + Long.toString(Debug.getNativeHeapSize() / 1024) + "kb";
            Log.v(getTAG(), getMetaInfo() + ":" + msg);
        }
    }

    /**
     * Output stacktrace of exception on log
     *
     * @param e
     */
    private static void printThrowable(Throwable e) {
        Log.e(getTAG(), e.getClass().getName() + ":" + e.getMessage());
        for (StackTraceElement element : e.getStackTrace()) {
            Log.e(getTAG(), "  at " + Logged.getMetaInfo(element));
        }
    }

    /**
     * getting meta infomation
     *
     * @return [className#methodName:line]
     */
    private static String getMetaInfo() {
        // 0: VM, 1: Thread, 2: LogUtil#getMetaInfo, 3:LogUtil, 4: source
        final StackTraceElement element = Thread.currentThread().getStackTrace()[4];
        return Logged.getMetaInfo(element);
    }

    /**
     * getting class name, method name, line number
     *
     * @return [className#methodName:line]
     */
    public static String getMetaInfo(StackTraceElement element) {
        final String methodName = element.getMethodName();
        final int lineNumber = element.getLineNumber();
        final String metaInfo = "[---METHOD---#" + methodName + ":" + lineNumber + "]";
        return metaInfo;
    }

    /**
     * @return
     */
    public static String getTAG() {
        final StackTraceElement element = Thread.currentThread().getStackTrace()[4];
        final String fullClassName = element.getClassName();
        final String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        return simpleClassName;
    }
}
