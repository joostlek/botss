package hu.indicium.battle.management.domain.team;

import java.util.Collection;
import java.util.Optional;

public interface TeamRepository {
    Collection<Team> getAllTeams();

    Team findTeamByJoinCode(String joinCode);

    Team getByTeamId(TeamId teamId);

    Team save(Team team);

    void delete(Team team);
}
