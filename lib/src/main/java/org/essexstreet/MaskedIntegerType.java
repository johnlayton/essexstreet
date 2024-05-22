package org.essexstreet;

public class MaskedIntegerType extends TinyType<Integer> {
    public MaskedIntegerType(final Integer value) {
        super(value, s -> "####");
    }
}
