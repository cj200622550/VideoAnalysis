package com.cj.videoanalysis.logger;

/**
 * Logger is a wrapper of {@link android.util.Log}
 * But more pretty, simple and powerful
 */
public final class LogUtils {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    public static final boolean IS_ON_LOG = true;

    private static final String DEFAULT_TAG = "PRETTYLOGGER";

    private static Printer printer = new LoggerPrinter();

    //no instance
    private LogUtils() {
    }

    /**
     * It is used to get the settings object in order to change settings
     *
     * @return the settings object
     */
    public static Settings init() {
        return init(DEFAULT_TAG);
    }

    /**
     * It is used to change the tag
     *
     * @param tag is the given string which will be used in Logger as TAG
     */
    public static Settings init(String tag) {
        printer = new LoggerPrinter();
        return printer.init(tag);
    }

    public static void resetSettings() {
        printer.resetSettings();
    }

    public static Printer t(String tag) {
        return printer.t(tag, printer.getSettings().getMethodCount());
    }

    public static Printer t(int methodCount) {
        return printer.t(null, methodCount);
    }

    public static Printer t(String tag, int methodCount) {
            return printer.t(tag, methodCount);
    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        if (IS_ON_LOG) {
            printer.log(priority, tag, message, throwable);
        }
    }

    public static void d(String message, Object... args) {
        if (IS_ON_LOG) {
            printer.d(message, args);
        }
    }

    public static void d(Object object) {
        if (IS_ON_LOG) {
            printer.d(object);
        }
    }

    public static void e(String message, Object... args) {
        if (IS_ON_LOG) {
            printer.e(null, message, args);
        }
    }

    public static void e(Throwable throwable, String message, Object... args) {
        if (IS_ON_LOG) {
            printer.e(throwable, message, args);
        }
    }

    public static void i(String message, Object... args) {
        if (IS_ON_LOG) {
            printer.i(message, args);
        }
    }

    public static void v(String message, Object... args) {
        if (IS_ON_LOG) {
            printer.v(message, args);
        }
    }

    public static void w(String message, Object... args) {
        if (IS_ON_LOG) {
            printer.w(message, args);
        }
    }

    public static void wtf(String message, Object... args) {
        if (IS_ON_LOG) {
            printer.wtf(message, args);
        }
    }

    /**
     * Formats the json content and print it
     *
     * @param json the json content
     */
    public static void json(String json) {
        if (IS_ON_LOG) {
            printer.json(json);
        }
    }

    /**
     * Formats the json content and print it
     *
     * @param xml the xml content
     */
    public static void xml(String xml) {
        if (IS_ON_LOG){
            printer.xml(xml);
        }
    }

}
