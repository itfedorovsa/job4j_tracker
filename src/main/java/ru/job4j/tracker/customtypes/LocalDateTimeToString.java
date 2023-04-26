package ru.job4j.tracker.customtypes;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import java.time.LocalDateTime;

public class LocalDateTimeToString extends AbstractSingleColumnStandardBasicType<LocalDateTime> {
    public static final LocalDateTimeToString INSTANCE = new LocalDateTimeToString();

    public LocalDateTimeToString() {
        super(VarcharTypeDescriptor.INSTANCE, LocalDateTimeStringDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "LocalDateTimeToString";
    }

}