package hu.indicium.battle.management.application.service;

import hu.indicium.battle.management.application.commands.CreateTeamCommand;
import hu.indicium.battle.management.application.commands.DeleteTeamCommand;
import hu.indicium.battle.management.application.commands.UpdateTeamCommand;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.ParticipantRepository;
import hu.indicium.battle.management.domain.team.ParticipantNotLegibleToTeamException;
import hu.indicium.battle.management.domain.team.Team;
import hu.indicium.battle.management.domain.team.TeamId;
import hu.indicium.battle.management.domain.team.TeamRepository;
import hu.indicium.battle.management.infrastructure.auth.AuthService;
import hu.indicium.battle.management.infrastructure.auth.AuthUser;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final ParticipantRepository participantRepository;

    private final AuthService authService;

    @Override
    @Transactional
    @PreAuthorize("hasPermission('create-team')")
    public TeamId createTeam(CreateTeamCommand createTeamCommand) {
        TeamId teamId = TeamId.fromUUID(UUID.randomUUID());

        AuthUser authUser = authService.getCurrentUser();

        Participant participant = participantRepository.getParticipantById(authUser.getParticipantId());

        if (!participant.eligibleToJoin()) {
            throw new ParticipantNotLegibleToTeamException();
        }

        Team team = new Team(teamId, createTeamCommand.getName(), participant);

        teamRepository.save(team);

        return teamId;
    }

    @Override
    @PreAuthorize("hasPermission('update-team') && isTeamCaptain(#updateTeamCommand.teamId.id)")
    public void updateTeam(UpdateTeamCommand updateTeamCommand) {
        Team team = teamRepository.getByTeamId(updateTeamCommand.getTeamId());

        team.setName(updateTeamCommand.getName());

        teamRepository.save(team);
    }

    @Override
    @PreAuthorize("hasPermission('update-team') && isTeamCaptain(#deleteTeamCommand.teamId.id)")
    public void deleteTeam(DeleteTeamCommand deleteTeamCommand) {
        Team team = teamRepository.getByTeamId(deleteTeamCommand.getTeamId());

        if (team.mayBeRemoved()) {

            Participant captain = team.getCaptain();

            captain.setTeam(null);

            participantRepository.save(captain);

            teamRepository.deleteById(deleteTeamCommand.getTeamId());
        } else {
            throw new IllegalStateException("Team may not be removed");
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasPermission('join-team')")
    public void useJoinCodeForParticipant(String joinCode, ParticipantId participantId) {
        Team team = teamRepository.findTeamByJoinCode(joinCode);

        Participant participant = participantRepository.getParticipantById(participantId);

        team.addParticipant(participant);

        teamRepository.save(team);
    }

    @Override
    @Transactional
    public void removeParticipantFromTeam(TeamId teamId, ParticipantId participantId) {
        Team team = teamRepository.getByTeamId(teamId);

        Participant participant = participantRepository.getParticipantById(participantId);

        team.removeParticipant(participant);

        teamRepository.save(team);
    }
}
