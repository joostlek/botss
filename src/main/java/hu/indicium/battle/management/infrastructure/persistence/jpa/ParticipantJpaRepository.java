package hu.indicium.battle.management.infrastructure.persistence.jpa;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.team.TeamId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

public interface ParticipantJpaRepository extends JpaRepository<Participant, UUID> {
    Collection<Participant> getParticipantsByAssociationId(AssociationId associationId);

    Participant getParticipantById(ParticipantId participantId);

    Collection<Participant> getParticipantByTeamId(TeamId teamId);

    boolean existsByParticipantDetailsEmailAddress(String emailAddress);
}
