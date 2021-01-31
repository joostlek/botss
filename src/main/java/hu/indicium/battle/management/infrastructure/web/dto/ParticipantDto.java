package hu.indicium.battle.management.infrastructure.web.dto;

import hu.indicium.battle.management.domain.participant.Participant;
import lombok.Data;

import java.util.UUID;

@Data
public class ParticipantDto {

    private UUID id;

    private String associationSlug;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String phoneNumber;

    public ParticipantDto(Participant participant) {
        this.id = participant.getParticipantId().getId();
        this.associationSlug = participant.getAssociation().getId().getSlug();
        this.firstName = participant.getParticipantDetails().getFirstName();
        this.lastName = participant.getParticipantDetails().getLastName();
        this.emailAddress = participant.getParticipantDetails().getEmailAddress();
        this.phoneNumber = participant.getParticipantDetails().getPhoneNumber();
    }
}
