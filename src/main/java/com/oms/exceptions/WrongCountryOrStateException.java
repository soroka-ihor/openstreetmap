package com.oms.exceptions;

public class WrongCountryOrStateException extends RuntimeException {
    public WrongCountryOrStateException(String country, String state) {
        super("Wrong request: there's no country " + country + " with the " + state + " state.");
    }
}
