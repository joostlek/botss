package hu.indicium.battle.management.application.query;

import hu.indicium.battle.management.domain.team.Team;
import hu.indicium.battle.management.domain.team.TeamId;

import java.util.Collection;

public interface TeamQueryService {
    Team getTeamById(TeamId teamId);

    Team getTeamByJoinCode(String joinCode);

    Collection<Team> getAllTeams();
}
