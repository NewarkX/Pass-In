package rocketseat.com.passin.dto.event;

import rocketseat.com.passin.domain.event.Event;

public class EventResponseDto {
    EventDetailDto event;

    public EventResponseDto(Event event, Integer numberOfAttendees){
        this.event = new EventDetailDto(
                event.getId(),
                event.getTittle(),
                event.getDetails(),
                event.getSlug(),
                event.getMaximumAttendees(),
                numberOfAttendees
        );
    }
}
