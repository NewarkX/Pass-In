package rocketseat.com.passin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passin.dto.event.EventIdDto;
import rocketseat.com.passin.dto.event.EventRequestDto;
import rocketseat.com.passin.dto.event.EventResponseDto;
import rocketseat.com.passin.services.EventService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
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
}
