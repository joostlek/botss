package hu.indicium.battle.management.application.service;

import hu.indicium.battle.management.application.commands.CreateParticipantCommand;
import hu.indicium.battle.management.domain.association.Association;
import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.association.AssociationRepository;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantDetails;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.ParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    private final AssociationRepository associationRepository;

    @Override
    public ParticipantId createParticipant(CreateParticipantCommand createParticipantCommand) {
        ParticipantId participantId = ParticipantId.fromUUID(UUID.randomUUID());

        ParticipantDetails participantDetails = new ParticipantDetails(createParticipantCommand.getFirstName(), createParticipantCommand.getLastName(), createParticipantCommand.getEmailAddress(), createParticipantCommand.getPhoneNumber());

        AssociationId associationId = AssociationId.fromSlug(createParticipantCommand.getAssociationSlug());

        Association association = associationRepository.getAssociationById(associationId);

        Participant participant = new Participant(participantId, participantDetails, association);

        participantRepository.save(participant);

        return participantId;
    }
}
