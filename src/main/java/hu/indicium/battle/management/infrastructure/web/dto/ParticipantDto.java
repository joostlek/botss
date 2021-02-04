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

    private boolean hasPaid;

    private UUID teamId;

    public ParticipantDto(Participant participant) {
        this.id = participant.getId().getId();
        this.associationSlug = participant.getAssociation().getId().getSlug();
        this.firstName = participant.getParticipantDetails().getFirstName();
        this.lastName = participant.getParticipantDetails().getLastName();
        this.emailAddress = participant.getParticipantDetails().getEmailAddress();
        this.phoneNumber = participant.getParticipantDetails().getPhoneNumber();
        this.hasPaid = participant.hasPaid();
        if (participant.isInTeam()) {
            this.teamId = participant.getTeam().getId().getId();
        }
    }
}
