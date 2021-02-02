package hu.indicium.battle.management.application.query;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.team.TeamId;

import java.util.Collection;

public interface ParticipantQueryService {
    Collection<Participant> getAllParticipants();

    Collection<Participant> getParticipantsByAssociationId(AssociationId associationId);

    Collection<Participant> getParticipantsByTeamId(TeamId teamId);

    Participant getParticipantById(ParticipantId participantId);
}
