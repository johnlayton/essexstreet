package org.essexstreet;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.function.Function;

public class TinyTypeValueModule extends SimpleModule {

    public TinyTypeValueModule() {
        text(StringType.class, StringType::new);
        text(MaskedStringType.class, MaskedStringType::new);
        integer(IntegerType.class, IntegerType::new);
        integer(MaskedIntegerType.class, MaskedIntegerType::new);
    }

    public <T extends TinyType<String>> TinyTypeValueModule text(Class<T> clazz, Function<String, T> fn) {
        addDeserializer(clazz, new JsonDeserializer<>() {
            @Override
            public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                return fn.apply(p.getText());
            }
        });
        addSerializer(clazz, new JsonSerializer<>() {
            @Override
            public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.getCodec().writeValue(gen, value.value());
            }
        });
        return this;
    }

    public <T extends TinyType<Integer>> TinyTypeValueModule integer(Class<T> clazz, Function<Integer, T> fn) {
        addDeserializer(clazz, new JsonDeserializer<>() {
            @Override
            public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                return fn.apply(p.getIntValue());
            }
        });
        addSerializer(clazz, new JsonSerializer<>() {
            @Override
            public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.getCodec().writeValue(gen, value.value());
            }
        });
        return this;
    }

}
