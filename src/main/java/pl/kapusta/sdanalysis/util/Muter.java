package pl.kapusta.sdanalysis.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Muter {
    public static <T> T mute(Supplier<T> action) {
        try {
            return action.get();
        } catch (Throwable t) {
            return null;
        }
    }
}
