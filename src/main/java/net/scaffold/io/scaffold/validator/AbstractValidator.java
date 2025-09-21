package net.scaffold.io.scaffold.validator;

import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public abstract class AbstractValidator {


    public void isTrue(boolean expression, Supplier<? extends RuntimeException> exceptionSupplier) {
        if (!expression) {
            throw exceptionSupplier.get();
        }
    }

    public <T> T notNull(T value, Supplier<? extends RuntimeException> exceptionSupplier) {
        if (value == null) {
            throw exceptionSupplier.get();
        }
        return value;
    }

    public void isTrue(boolean expression, String message) {
        isTrue(expression, () -> new IllegalArgumentException(message));
    }

    public <T> T notNull(T value, String message) {
        return notNull(value, () -> new IllegalArgumentException(message));
    }

    public void allNotNull(Supplier<? extends RuntimeException> exceptionSupplier, Object... values) {
        for (Object value : values) {
            if (value == null) {
                throw exceptionSupplier.get();
            }
        }
    }

    public void allNotNull(String message, Object... values) {
        allNotNull(() -> new IllegalArgumentException(message), values);
    }
}
