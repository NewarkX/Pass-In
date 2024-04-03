package rocketseat.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.event.Event;
import rocketseat.com.passin.domain.event.exceptions.EventFullException;
import rocketseat.com.passin.domain.event.exceptions.EventNotFoundException;
import rocketseat.com.passin.domain.repositories.EventRepository;
import rocketseat.com.passin.dto.attendee.AttendeeIdDto;
import rocketseat.com.passin.dto.attendee.AttendeeRequestDto;
import rocketseat.com.passin.dto.event.EventIdDto;
import rocketseat.com.passin.dto.event.EventRequestDto;
import rocketseat.com.passin.dto.event.EventResponseDto;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;

    public EventResponseDto getEventDetail(String eventId){
        Event event = this.getEventById(eventId);
        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);
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

    public AttendeeIdDto registerAttendeeOnEvent(String eventId, AttendeeRequestDto attendeeRequestDto){
        this.attendeeService.verifyAttendeeSubscription(attendeeRequestDto.email(),eventId);
        Event event = this.getEventById(eventId);
        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);
        if(event.getMaximumAttendees() <= attendeeList.size()) throw new EventFullException("Event is full");

        Attendee newAttenddee = new Attendee();
        newAttenddee.setName(attendeeRequestDto.name());
        newAttenddee.setEmail(attendeeRequestDto.email());
        newAttenddee.setEvent(event);
        newAttenddee.setCreatedAt(LocalDateTime.now());
        this.attendeeService.registerAttendee(newAttenddee);
        return new AttendeeIdDto(newAttenddee.getId());
    }

    private Event getEventById(String eventId){
        return this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));
    }

    private String createSlug(String text){
        String normalized = Normalizer.normalize(text,Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]","")
                .replaceAll("[^\\w\\s]","")
                .replaceAll("\\s","")
                .toLowerCase();
    }
}
