package hu.indicium.battle.management.application.service;

import hu.indicium.battle.management.application.commands.CreateTeamCommand;
import hu.indicium.battle.management.application.commands.DeleteTeamCommand;
import hu.indicium.battle.management.application.commands.UpdateTeamCommand;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.team.TeamId;

public interface TeamService {

    TeamId createTeam(CreateTeamCommand createTeamCommand);

    void updateTeam(UpdateTeamCommand updateTeamCommand);

    void deleteTeam(DeleteTeamCommand deleteTeamCommand);

    void useJoinCodeForParticipant(String joinCode, ParticipantId participantId);

    void removeParticipantFromTeam(TeamId teamId, ParticipantId participantId);
}
