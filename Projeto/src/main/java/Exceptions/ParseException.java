package Exceptions;

/**
 * This exception will be returned when it is not possible to parse file to json object
 */
public class ParseException extends Exception {

    public ParseException(String msg) {
        super(msg);
    }
}
