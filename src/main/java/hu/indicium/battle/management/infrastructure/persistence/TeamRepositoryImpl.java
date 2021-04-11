package hu.indicium.battle.management.infrastructure.persistence;

import hu.indicium.battle.management.domain.team.Team;
import hu.indicium.battle.management.domain.team.TeamId;
import hu.indicium.battle.management.domain.team.TeamRepository;
import hu.indicium.battle.management.infrastructure.persistence.jpa.TeamJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TeamRepositoryImpl implements TeamRepository {

    private final TeamJpaRepository teamRepository;

    @Override
    public Collection<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team findTeamByJoinCode(String joinCode) {
        return teamRepository.findByJoinCode(joinCode)
                .orElseThrow(() -> new EntityNotFoundException("Could not find team."));
    }

    @Override
    public Team getByTeamId(TeamId teamId) {
        return teamRepository.getById(teamId);
    }

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void delete(Team team) {
        teamRepository.delete(team);
    }
}
