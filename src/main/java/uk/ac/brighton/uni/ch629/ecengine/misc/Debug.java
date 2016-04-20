package uk.ac.brighton.uni.ch629.ecengine.misc;

public class Debug {
    public static boolean isEnabled = true;

    public static void println(String s) {
        if (isEnabled) System.out.println(s);
    }

    public static void print(String s) {
        if (isEnabled) System.out.println(s);
    }

    public static void printf(String format, Object... args) {
        if (isEnabled) System.out.printf(format, args);
    }
}