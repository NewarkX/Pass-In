package rocketseat.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.event.Event;
import rocketseat.com.passin.domain.repositories.AttendeeRepository;
import rocketseat.com.passin.domain.repositories.EventRepository;
import rocketseat.com.passin.dto.event.EventIdDto;
import rocketseat.com.passin.dto.event.EventRequestDto;
import rocketseat.com.passin.dto.event.EventResponseDto;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventResponseDto getEventDetail(String eventId){
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));
        List<Attendee> attendeeList = this.attendeeRepository.findByEventId(eventId);
        return new EventResponseDto(event,attendeeList.size());
    }

    public EventIdDto createEvent(EventRequestDto eventDto){
        Event newEvent = new Event();
        newEvent.setTitle(eventDto.title());
        newEvent.setDetails(eventDto.details());
        newEvent.setMaximumAttendees(eventDto.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventDto.title()));
        this.eventRepository.save(newEvent);
        return new EventIdDto(newEvent.getId());
    }

    private String createSlug(String text){
        String normalized = Normalizer.normalize(text,Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]","")
                .replaceAll("[^\\w\\s]","")
                .replaceAll("\\s","")
                .toLowerCase();
    }
}
