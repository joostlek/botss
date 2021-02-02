package hu.indicium.battle.management.application.query;

import hu.indicium.battle.management.domain.team.Team;
import hu.indicium.battle.management.domain.team.TeamId;
import hu.indicium.battle.management.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
@AllArgsConstructor
public class TeamQueryServiceImpl implements TeamQueryService {

    private final TeamRepository teamRepository;

    @Override
    public Team getTeamById(TeamId teamId) {
        return teamRepository.getByTeamId(teamId);
    }

    @Override
    public Team getTeamByJoinCode(String joinCode) {
        return teamRepository.findTeamByJoinCode(joinCode);
    }

    @Override
    public Collection<Team> getAllTeams() {
        return teamRepository.getAllTeams();
    }
}
