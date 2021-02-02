package hu.indicium.battle.management.infrastructure.persistence;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.ParticipantRepository;
import hu.indicium.battle.management.infrastructure.persistence.jpa.ParticipantJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public Participant getParticipantById(ParticipantId participantId) {
        return participantJpaRepository.getParticipantById(participantId);
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
