package app.demo.carryonnotes.error;

public class EmailTakenException extends Exception{

    private static final String EMAIL_ERROR = "Email already used!";

    public EmailTakenException() {
        super(EMAIL_ERROR);
    }
}
