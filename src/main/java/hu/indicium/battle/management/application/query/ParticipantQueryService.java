package hu.indicium.battle.management.application.query;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;

import java.util.Collection;

public interface ParticipantQueryService {
    Collection<Participant> getAllParticipants();

    Collection<Participant> getParticipantsByAssociationId(AssociationId associationId);

    Participant getParticipantById(ParticipantId participantId);
}
