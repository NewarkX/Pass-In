package rocketseat.com.passin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passin.dto.attendee.AttendeeIdDto;
import rocketseat.com.passin.dto.attendee.AttendeeRequestDto;
import rocketseat.com.passin.dto.attendee.AttendeesListResponseDto;
import rocketseat.com.passin.dto.event.EventIdDto;
import rocketseat.com.passin.dto.event.EventRequestDto;
import rocketseat.com.passin.dto.event.EventResponseDto;
import rocketseat.com.passin.services.AttendeeService;
import rocketseat.com.passin.services.EventService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final AttendeeService attendeeService;
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getEvent(@PathVariable String id){
        EventResponseDto event = this.eventService.getEventDetail(id);
        return ResponseEntity.ok(event);
    }
    @PostMapping
    public ResponseEntity<EventIdDto> createEvent(@RequestBody EventRequestDto body, UriComponentsBuilder uriComponentsBuilder){
        EventIdDto eventIdDTO = this.eventService.createEvent(body);

        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeIdDto> registerParticipant(@PathVariable String eventId,
                                                          @RequestBody AttendeeRequestDto body,
                                                          UriComponentsBuilder uriComponentsBuilder)
    {
        AttendeeIdDto attendeeIdDto = this.eventService.registerAttendeeOnEvent(eventId,body);

        var uri = uriComponentsBuilder.path("/attendees/{attendeeId/badge}").buildAndExpand(attendeeIdDto.AttendeeId()).toUri();

        return ResponseEntity.created(uri).body(attendeeIdDto);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeesListResponseDto> getEventAttendees(@PathVariable String id){
        AttendeesListResponseDto attendeesListResponseDto = this.attendeeService.getEventsAttendee(id);
        return ResponseEntity.ok(attendeesListResponseDto);
    }
}
