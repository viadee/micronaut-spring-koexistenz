package de.viadee.parkhaus.manager.typeconverter;

import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Singleton
public class LocalDateTimeToStringConverter implements TypeConverter<LocalDateTime, String> {

   @Override public Optional<String> convert(LocalDateTime object, Class<String> targetType) {
      return object != null ? Optional.of(object.format(DateTimeFormatter.ISO_DATE_TIME)) : Optional.empty();
   }

   @Override public Optional<String> convert(LocalDateTime object, Class<String> targetType,
         ConversionContext context) {
      return object != null ? Optional.of(object.format(DateTimeFormatter.ISO_DATE_TIME)) : Optional.empty();
   }
}
