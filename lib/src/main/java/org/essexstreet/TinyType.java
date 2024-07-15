package org.essexstreet;

import java.util.Objects;
import java.util.function.Function;

public abstract class TinyType<T> {

    private final T value;
    private final Function<T, String> show;

    protected TinyType(final T value) {
        this(value, Objects::toString);
    }

    protected TinyType(final T value, final Function<T, String> show) {
        this.value = value;
        this.show = show;
    }

    public String show() {
        return show.apply(value);
    }

    public T unwrap() {
        return value;
    }

    public T value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TinyType<?> that = (TinyType<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return show();
    }
}
