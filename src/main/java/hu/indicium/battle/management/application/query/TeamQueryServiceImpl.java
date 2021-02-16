package hu.indicium.battle.management.application.query;

import hu.indicium.battle.management.domain.team.Team;
import hu.indicium.battle.management.domain.team.TeamId;
import hu.indicium.battle.management.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
@AllArgsConstructor
public class TeamQueryServiceImpl implements TeamQueryService {

    private final TeamRepository teamRepository;

    @Override
    @PreAuthorize("hasPermission('manage-team') || isPartOfTeam(#teamId.id)")
    public Team getTeamById(TeamId teamId) {
        return teamRepository.getByTeamId(teamId);
    }

    @Override
    @PreAuthorize("hasPermission('join-team')")
    public Team getTeamByJoinCode(String joinCode) {
        return teamRepository.findTeamByJoinCode(joinCode);
    }

    @Override
    @PreAuthorize("hasPermission('manage-team')")
    public Collection<Team> getAllTeams() {
        return teamRepository.getAllTeams();
    }
}
