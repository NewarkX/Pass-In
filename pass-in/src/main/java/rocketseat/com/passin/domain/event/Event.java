package rocketseat.com.passin.domain.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "events")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String tittle;
    @Column(nullable = false)
    private String details;
    @Column(nullable = false,unique = true)
    private String slug;
    @Column(nullable = false,name = "maximum_attendees")
    private Integer maximumAttendees;

}
