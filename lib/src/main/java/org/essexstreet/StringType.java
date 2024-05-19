package org.essexstreet;

import java.util.function.Function;

public class StringType extends TinyType<String> {

    public StringType(final String value) {
        super(value);
    }

//    public StringType(final String value, final Function<String, String> show) {
//        super(value, show);
//    }
}
