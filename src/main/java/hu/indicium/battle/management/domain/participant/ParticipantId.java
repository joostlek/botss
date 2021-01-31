package hu.indicium.battle.management.domain.participant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ParticipantId implements Serializable {
    @Column(name= "id")
    private UUID id;

    public static ParticipantId fromUUID(UUID uuid) {
        return new ParticipantId(uuid);
    }

    private ParticipantId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantId that = (ParticipantId) o;
        return Objects.equals(id, that.id);
    }
}
