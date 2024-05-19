package org.essexstreet;

public class SecretType extends TinyType<String> {

    public SecretType(final String value) {
        super(value, s -> "***");
    }

//    public StringType(final String value, final Function<String, String> show) {
//        super(value, show);
//    }
}
