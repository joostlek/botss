package hu.indicium.battle.management.domain.participant;

import hu.indicium.battle.management.domain.association.AssociationId;

import java.util.Collection;

public interface ParticipantRepository {
    Collection<Participant> getAllParticipants();

    Collection<Participant> getParticipantsByAssociationId(AssociationId associationId);

    Participant getParticipantById(ParticipantId participantId);

    Participant save(Participant participant);

    boolean existsByEmailAddress(String emailAddress);
}
