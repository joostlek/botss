package hu.indicium.battle.management.infrastructure.web.dto;

import hu.indicium.battle.management.domain.team.Team;
import lombok.Data;

import java.util.UUID;

@Data
public class TeamDto {
    private UUID id;

    private String name;

    private String joinCode;

    private UUID teamCaptain;

    private int teamSize;

    private boolean eligible;

    public TeamDto(Team team) {
        this.id = team.getId().getId();
        this.name = team.getName();
        this.joinCode = team.getJoinCode();
        this.teamCaptain = team.getCaptain().getId().getId();
        this.teamSize = team.getTeamSize();
        this.eligible = team.isEligible();
    }
}
