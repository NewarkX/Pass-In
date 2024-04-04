package rocketseat.com.passin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passin.dto.event.AttendeeBadgeResponseDto;
import rocketseat.com.passin.services.AttendeeService;
import rocketseat.com.passin.services.CheckInService;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AteendeeController {
    private final AttendeeService attendeeService;

    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<AttendeeBadgeResponseDto> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder){
        AttendeeBadgeResponseDto attendeeBadgeResponseDto = this.attendeeService.getAttendeeBadge(attendeeId,uriComponentsBuilder);
        return ResponseEntity.ok(attendeeBadgeResponseDto);
    }

    @PostMapping("/{attendeeId}/check-in")
    public ResponseEntity registerCheckIn(@PathVariable String attendeeId,UriComponentsBuilder uriComponentsBuilder){
        this.attendeeService.checkInAttendee(attendeeId);
        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeId).toUri();
        return ResponseEntity.created(uri).build();
    }
}
