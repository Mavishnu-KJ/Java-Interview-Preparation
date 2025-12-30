package java8;

import java.io.IOException;

@FunctionalInterface
public interface ThrowCheckedException<T> {
    public void throwIOException(T t) throws IOException;
}
