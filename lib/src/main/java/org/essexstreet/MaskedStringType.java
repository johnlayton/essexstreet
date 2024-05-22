package org.essexstreet;

public class MaskedStringType extends TinyType<String> {
    public MaskedStringType(final String value) {
        super(value, s -> "****");
    }
}
