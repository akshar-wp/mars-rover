package rover;

public class UnknownCommandException extends RuntimeException {

    public UnknownCommandException(Character command) {
        super("Invalid command provided '" + command + "'.");
    }
}
