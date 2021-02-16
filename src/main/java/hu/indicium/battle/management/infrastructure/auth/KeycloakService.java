package hu.indicium.battle.management.infrastructure.auth;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.team.TeamId;

public interface KeycloakService {

    void setAssociationForParticipant(ParticipantId participantId, AssociationId associationId);

    void setTeamForParticipant(ParticipantId participantId, TeamId teamId);

    void setTeamForTeamCaptain(ParticipantId participantId, TeamId teamId);

    void resetTeamForParticipant(ParticipantId participantId);

    void setPaymentStatusPaidForParticipant(ParticipantId participantId);
}
