package hu.indicium.battle.management.infrastructure.persistence.jpa;

import hu.indicium.battle.management.domain.team.Team;
import hu.indicium.battle.management.domain.team.TeamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamJpaRepository extends JpaRepository<Team, UUID> {
    Optional<Team> findByJoinCode(String joinCode);

    Team getById(TeamId teamId);

    void deleteById(TeamId teamId);
}
