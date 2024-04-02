package rocketseat.com.passin.services;

import org.springframework.stereotype.Service;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.repositories.AttendeeRepository;

import java.util.List;

@Service
public class AttendeeService {
    private AttendeeRepository attendeeRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId){
        List<Attendee> attendeesList = this.attendeeRepository.findByEventId(eventId);
        return attendeesList;
    }
}
