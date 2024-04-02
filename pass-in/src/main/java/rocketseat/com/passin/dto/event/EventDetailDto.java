package rocketseat.com.passin.dto.event;

public record EventDetailDto(
        String id,
        String tittle,
        String details,
        String slug,
        Integer maximumAttendees,
        Integer attendeeAmount
        ){
}
