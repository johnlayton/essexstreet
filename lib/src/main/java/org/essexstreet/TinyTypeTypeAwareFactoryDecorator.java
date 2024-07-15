package org.essexstreet;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.logstash.logback.decorate.JsonFactoryDecorator;

import java.io.IOException;
import java.util.function.Function;

public class TinyTypeTypeAwareFactoryDecorator implements JsonFactoryDecorator {

    @Override
    public JsonFactory decorate(final JsonFactory factory) {
        ObjectMapper codec = (ObjectMapper) factory.getCodec();
        codec.registerModule(new TinyTypeMaskedModule());
        return factory;
    }

    public static class TinyTypeMaskedModule extends SimpleModule {

        public TinyTypeMaskedModule() {
            text(StringType.class, StringType::new);
            text(MaskedStringType.class, MaskedStringType::new);
            integer(IntegerType.class, IntegerType::new);
            integer(MaskedIntegerType.class, MaskedIntegerType::new);
        }

        public <T extends TinyType<String>> TinyTypeMaskedModule text(Class<T> clazz, Function<String, T> fn) {
            addDeserializer(clazz, new JsonDeserializer<>() {
                @Override
                public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                    return fn.apply(p.getText());
                }
            });
            addSerializer(clazz, new JsonSerializer<>() {
                @Override
                public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                    gen.getCodec().writeValue(gen, value.show());
                }
            });
            return this;
        }

        public <T extends TinyType<Integer>> TinyTypeMaskedModule integer(Class<T> clazz, Function<Integer, T> fn) {
            addDeserializer(clazz, new JsonDeserializer<>() {
                @Override
                public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                    return fn.apply(p.getIntValue());
                }
            });
            addSerializer(clazz, new JsonSerializer<>() {
                @Override
                public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                    gen.getCodec().writeValue(gen, value.show());
                }
            });
            return this;
        }
    }

/*
    public static final class MyJsonModule extends ValueTypeModule {
        public MyJsonModule() {
            text(StringType.class, StringType::new);
            integer(IntegerType.class, IntegerType::new);
        }
    }
*/
}
