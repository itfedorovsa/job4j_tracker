package ru.job4j.tracker.customtypes;

import org.hibernate.type.LocalDateTimeType;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.ImmutableMutabilityPlan;

import java.time.LocalDateTime;

public class LocalDateTimeStringDescriptor extends AbstractTypeDescriptor<LocalDateTime> {

    public static final LocalDateTimeStringDescriptor INSTANCE = new LocalDateTimeStringDescriptor();

    public LocalDateTimeStringDescriptor() {
        super(LocalDateTime.class, ImmutableMutabilityPlan.INSTANCE);
    }

    @Override
    public <T> T unwrap(LocalDateTime ldt, Class<T> type, WrapperOptions options) {
        if (ldt == null) {
            return null;
        }
        if (String.class.isAssignableFrom(type)) {
            return (T) LocalDateTimeType.FORMATTER.format(ldt);
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <T> LocalDateTime wrap(T str, WrapperOptions options) {
        if (str == null) {
            return null;
        }
        if (str instanceof String) {
            return LocalDateTime.from(LocalDateTimeType.FORMATTER.parse((CharSequence) str));
        }
        throw unknownWrap(str.getClass());
    }

    /**
     * Not implemented
     * @param str LocalDateTime in String
     * @return LocalDateTime
     */
    @Override
    public LocalDateTime fromString(String str) {
        return null;
    }

}