package hu.indicium.battle.management.application.commands;

import hu.indicium.battle.management.domain.team.TeamId;
import lombok.Data;

@Data
public class UpdateTeamCommand {
    private TeamId teamId;

    private String name;
}
