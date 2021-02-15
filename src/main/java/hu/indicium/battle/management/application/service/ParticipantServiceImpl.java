package hu.indicium.battle.management.application.service;

import hu.indicium.battle.management.application.commands.CreateParticipantCommand;
import hu.indicium.battle.management.domain.association.Association;
import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.association.AssociationRepository;
import hu.indicium.battle.management.domain.participant.*;
import hu.indicium.battle.management.infrastructure.auth.AuthService;
import hu.indicium.battle.management.infrastructure.auth.AuthUser;
import hu.indicium.battle.management.infrastructure.auth.KeycloakService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    private final AssociationRepository associationRepository;

    private final AuthService authService;

    private final KeycloakService keycloakService;

    @Override
    @Transactional
    public ParticipantId createParticipant(CreateParticipantCommand createParticipantCommand) {
        AuthUser authUser = authService.getCurrentUser();

        ParticipantId participantId = authUser.getParticipantId();

        if (participantRepository.existsByEmailAddress(createParticipantCommand.getEmailAddress())) {
            throw new ParticipantEmailAddressAlreadyInUseException();
        }

        ParticipantDetails participantDetails = new ParticipantDetails(createParticipantCommand.getFirstName(), createParticipantCommand.getLastName(), createParticipantCommand.getEmailAddress(), createParticipantCommand.getPhoneNumber());

        AssociationId associationId = AssociationId.fromSlug(createParticipantCommand.getAssociationSlug());

        Association association = associationRepository.getAssociationById(associationId);

        Participant participant = new Participant(participantId, participantDetails, association);

        participantRepository.save(participant);

        keycloakService.setAssociationForParticipant(participantId, associationId);

        return participantId;
    }
}
