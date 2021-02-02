package hu.indicium.battle.management.application.service;

import hu.indicium.battle.management.application.commands.CreateTeamCommand;
import hu.indicium.battle.management.application.commands.UpdateTeamCommand;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.ParticipantRepository;
import hu.indicium.battle.management.domain.team.ParticipantNotLegibleToTeamException;
import hu.indicium.battle.management.domain.team.Team;
import hu.indicium.battle.management.domain.team.TeamId;
import hu.indicium.battle.management.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final ParticipantRepository participantRepository;

    @Override
    public TeamId createTeam(CreateTeamCommand createTeamCommand) {
        TeamId teamId = TeamId.fromUUID(UUID.randomUUID());

        Participant participant = participantRepository.getParticipantById(createTeamCommand.getTeamCaptainId());

        if (participant.eligibleToJoin()) {
            throw new ParticipantNotLegibleToTeamException();
        }

        Team team = new Team(teamId, createTeamCommand.getName(), participant);

        teamRepository.save(team);

        return teamId;
    }

    @Override
    public void updateTeam(UpdateTeamCommand updateTeamCommand) {
        Team team = teamRepository.getByTeamId(updateTeamCommand.getTeamId());

        team.setName(updateTeamCommand.getName());

        teamRepository.save(team);
    }

    @Override
    public void useJoinCodeForParticipant(String joinCode, ParticipantId participantId) {
        Team team = teamRepository.findTeamByJoinCode(joinCode);

        Participant participant = participantRepository.getParticipantById(participantId);

        team.addParticipant(participant);

        teamRepository.save(team);
    }

    @Override
    public void removeParticipantFromTeam(TeamId teamId, ParticipantId participantId) {
        Team team = teamRepository.getByTeamId(teamId);

        Participant participant = participantRepository.getParticipantById(participantId);

        team.removeParticipant(participant);

        teamRepository.save(team);
    }
}
