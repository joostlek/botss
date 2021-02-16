package hu.indicium.battle.management.application.query;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.ParticipantRepository;
import hu.indicium.battle.management.domain.team.TeamId;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collection;

@AllArgsConstructor
@Service
public class ParticipantQueryServiceImpl implements ParticipantQueryService {

    private final ParticipantRepository participantRepository;

    @Override
    @PreAuthorize("hasPermission('manage-participant')")
    public Collection<Participant> getAllParticipants() {
        return participantRepository.getAllParticipants();
    }

    @Override
    @PreAuthorize("hasPermission('manage-participant') || (hasPermission('manage-association') && isPartOfAssociation(#associationId.slug))")
    public Collection<Participant> getParticipantsByAssociationId(AssociationId associationId) {
        return participantRepository.getParticipantsByAssociationId(associationId);
    }

    @Override
    @PreAuthorize("hasPermission('manage-team') || isPartOfTeam(#teamId.id)")
    public Collection<Participant> getParticipantsByTeamId(TeamId teamId) {
        return participantRepository.getParticipantsByTeamId(teamId);
    }

    @Override
    @PreAuthorize("hasPermission('manage-participant') || userIdEquals(#participantId.id)")
    public Participant getParticipantById(ParticipantId participantId) {
        return participantRepository.getParticipantById(participantId);
    }
}
