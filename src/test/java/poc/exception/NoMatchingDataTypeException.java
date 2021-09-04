package poc.exception;

public class NoMatchingDataTypeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoMatchingDataTypeException(final String type) {
        super("No matching data type found for " + type);
    }

}
