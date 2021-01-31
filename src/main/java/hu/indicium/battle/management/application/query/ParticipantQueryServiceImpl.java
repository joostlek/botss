package hu.indicium.battle.management.application.query;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.ParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@AllArgsConstructor
@Service
public class ParticipantQueryServiceImpl implements ParticipantQueryService {

    private final ParticipantRepository participantRepository;

    @Override
    public Collection<Participant> getAllParticipants() {
        return participantRepository.getAllParticipants();
    }

    @Override
    public Collection<Participant> getParticipantsByAssociationId(AssociationId associationId) {
        return participantRepository.getParticipantsByAssociationId(associationId);
    }

    @Override
    public Participant getParticipantById(ParticipantId participantId) {
        return participantRepository.getParticipantById(participantId);
    }
}
