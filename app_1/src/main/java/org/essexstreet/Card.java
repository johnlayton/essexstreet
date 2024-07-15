package org.essexstreet;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public class Card {

//    @NotBlank
    @NonNull
    private StringType name;
//    @NotBlank
    @NonNull
    private MaskedStringType secret;

    public Card() {
    }

    @NonNull
    public StringType getName() {
        return name;
    }

    public void setName(@NonNull StringType name) {
        this.name = name;
    }


    @NonNull
    public MaskedStringType getSecret() {
        return secret;
    }

    public void setSecret(@NonNull MaskedStringType secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "Card{name=" + name + ", secret=" + secret + '}';
    }
}
