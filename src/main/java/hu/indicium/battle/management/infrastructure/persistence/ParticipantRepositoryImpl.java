package hu.indicium.battle.management.infrastructure.persistence;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.ParticipantRepository;
import hu.indicium.battle.management.domain.team.TeamId;
import hu.indicium.battle.management.infrastructure.persistence.jpa.ParticipantJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Repository
@AllArgsConstructor
public class ParticipantRepositoryImpl implements ParticipantRepository {

    private final ParticipantJpaRepository participantJpaRepository;

    @Override
    public Collection<Participant> getAllParticipants() {
        return participantJpaRepository.findAll();
    }

    @Override
    public Collection<Participant> getParticipantsByAssociationId(AssociationId associationId) {
        return participantJpaRepository.getParticipantsByAssociationId(associationId);
    }

    @Override
    public Collection<Participant> getParticipantsByTeamId(TeamId teamId) {
        return participantJpaRepository.getParticipantByTeamId(teamId);
    }

    @Override
    public Participant getParticipantById(ParticipantId participantId) {
        return participantJpaRepository.findParticipantById(participantId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find participant."));
    }

    @Override
    public Participant save(Participant participant) {
        return participantJpaRepository.save(participant);
    }

    @Override
    public boolean existsByEmailAddress(String emailAddress) {
        return participantJpaRepository.existsByParticipantDetailsEmailAddress(emailAddress);
    }
}
