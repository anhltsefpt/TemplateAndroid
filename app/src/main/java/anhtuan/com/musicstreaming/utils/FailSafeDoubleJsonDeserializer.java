package anhtuan.com.musicstreaming.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;

public class FailSafeDoubleJsonDeserializer implements JsonDeserializer<Double> {
  @Override
  public Double deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context)
      throws JsonParseException {
    if (!element.isJsonPrimitive()) {
      return 0.0;
    }

    try {
      final JsonPrimitive primitive = (JsonPrimitive) element;
      if (element.getAsString().toLowerCase().equals("nan")) {
        return 0.0;
      }
      final Number number = primitive.getAsNumber();
      return number.doubleValue();
    } catch ( final NumberFormatException ignored ) {
      return 0.0;
    }
  }
}

