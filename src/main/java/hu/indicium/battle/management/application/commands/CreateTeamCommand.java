package hu.indicium.battle.management.application.commands;

import hu.indicium.battle.management.domain.participant.ParticipantId;
import lombok.Data;

@Data
public class CreateTeamCommand {
    private String name;
}
