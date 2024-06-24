package ru.nikitinia.odm.bki.exception.util;


import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class IdErrorGenerator {
    public static String generateErrorId() {
        return String.valueOf(UUID.randomUUID());
    }

}
