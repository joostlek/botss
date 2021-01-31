package hu.indicium.battle.management.application.commands;

import lombok.Data;

@Data
public class CreateParticipantCommand {

    private String associationSlug;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String phoneNumber;
}
