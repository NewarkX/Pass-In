package rocketseat.com.passin.dto.attendee.exceptions;

public class AttendeeAlreadyExistException extends RuntimeException{
    public AttendeeAlreadyExistException(String message){
        super(message);
    }
}
