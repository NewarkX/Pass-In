package rocketseat.com.passin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passin.dto.event.AttendeeBadgeResponseDto;
import rocketseat.com.passin.services.AttendeeService;

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
}
