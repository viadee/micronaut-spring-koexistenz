package de.viadee.parkhaus.manager.typeconverter;

import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;
import jakarta.inject.Singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Singleton
public class StringToLocalDateTimeConverter implements TypeConverter<String, LocalDateTime> {

   @Override public Optional<LocalDateTime> convert(String object, Class<LocalDateTime> targetType) {
      return object != null ? Optional.of(LocalDateTime.parse(object, DateTimeFormatter.ISO_DATE_TIME)) : Optional.empty();
   }

   @Override public Optional<LocalDateTime> convert(String object, Class<LocalDateTime> targetType,
         ConversionContext context) {
      return object != null ? Optional.of(LocalDateTime.parse(object, DateTimeFormatter.ISO_DATE_TIME)) : Optional.empty();
   }

}
