package hu.indicium.battle.management.domain.team;

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
public class TeamId implements Serializable {
    @Column(name= "id")
    private UUID id;

    public static TeamId fromUUID(UUID uuid) {
        return new TeamId(uuid);
    }

    private TeamId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamId teamId = (TeamId) o;
        return Objects.equals(id, teamId.id);
    }
}
