package br.crud.barbershopapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(
        name = "SCHEDULES",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_SCHEDULE_INTERVAL", columnNames = {"start_at", "end_at"})
        }
)
@Getter
@Setter
@ToString
public class ScheduleModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, name = "start_at")
    private OffsetDateTime startAt;

    @Column(nullable = false, name = "end_at")
    private OffsetDateTime endAt;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client = new ClientModel();

    @Override
    public boolean equals( final Object o) {
        if (!(o instanceof ScheduleModel that)) return false; {
            return Objects.equals(id,that.id) &&
                   Objects.equals(startAt, that.startAt) &&
                   Objects.equals(endAt, that.endAt);

        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startAt, endAt);
    }
}
