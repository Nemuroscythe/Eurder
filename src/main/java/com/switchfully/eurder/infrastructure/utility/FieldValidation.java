package com.switchfully.eurder.infrastructure.utility;

import org.slf4j.Logger;

public class FieldValidation {

    public static void stringNullSafeBlankCheck(String stringToCheck, RuntimeException exception, String errorMessage, Logger logger) {
        if (stringToCheck == null || stringToCheck.isBlank()) {
            logger.error(errorMessage);
            throw exception;
        }
    }

    public static void numberPositiveCheck(double numberToCheck, RuntimeException exception, String errorMessage, Logger logger) {
        if (numberToCheck < 0) {
            logger.error(errorMessage);
            throw exception;
        }

    }

    public static void numberPositiveCheck(int numberToCheck, RuntimeException exception, String errorMessage, Logger logger) {
        if (numberToCheck < 0) {
            logger.error(errorMessage);
            throw exception;
        }
    }
}
