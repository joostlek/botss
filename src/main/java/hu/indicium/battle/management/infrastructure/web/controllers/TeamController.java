package hu.indicium.battle.management.infrastructure.web.controllers;

import hu.indicium.battle.management.application.commands.CreateTeamCommand;
import hu.indicium.battle.management.application.commands.JoinTeamCommand;
import hu.indicium.battle.management.application.commands.UpdateTeamCommand;
import hu.indicium.battle.management.application.query.ParticipantQueryService;
import hu.indicium.battle.management.application.query.TeamQueryService;
import hu.indicium.battle.management.application.service.TeamService;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.team.Team;
import hu.indicium.battle.management.domain.team.TeamId;
import hu.indicium.battle.management.infrastructure.auth.AuthService;
import hu.indicium.battle.management.infrastructure.auth.AuthUser;
import hu.indicium.battle.management.infrastructure.util.BaseUrl;
import hu.indicium.battle.management.infrastructure.util.Response;
import hu.indicium.battle.management.infrastructure.util.ResponseBuilder;
import hu.indicium.battle.management.infrastructure.web.dto.ParticipantDto;
import hu.indicium.battle.management.infrastructure.web.dto.TeamDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(BaseUrl.API_V1 + "/teams")
public class TeamController {
    private final TeamQueryService teamQueryService;

    private final TeamService teamService;

    private final ParticipantQueryService participantQueryService;

    private final AuthService authService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<Collection<TeamDto>> getAllTeams() {
        Collection<Team> teams = teamQueryService.getAllTeams();
        Collection<TeamDto> dtos = teams.stream()
                .map(TeamDto::new)
                .collect(Collectors.toSet());
        return ResponseBuilder.ok()
                .data(dtos)
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<TeamDto> createTeam(@RequestBody CreateTeamCommand createTeamCommand) {
        TeamId teamId = teamService.createTeam(createTeamCommand);
        Team team = teamQueryService.getTeamById(teamId);
        TeamDto teamDto = new TeamDto(team);
        return ResponseBuilder.created()
                .data(teamDto)
                .build();
    }

    @GetMapping("/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    public Response<TeamDto> getTeamById(@PathVariable UUID teamId) {
        TeamId id = TeamId.fromUUID(teamId);
        Team team = teamQueryService.getTeamById(id);
        TeamDto teamDto = new TeamDto(team);
        return ResponseBuilder.created()
                .data(teamDto)
                .build();
    }

    @PutMapping("/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    public Response<TeamDto> updateTeamInfo(@PathVariable UUID teamId, @RequestBody UpdateTeamCommand updateTeamCommand) {
        TeamId id = TeamId.fromUUID(teamId);
        updateTeamCommand.setTeamId(id);
        teamService.updateTeam(updateTeamCommand);
        Team team = teamQueryService.getTeamById(id);
        TeamDto teamDto = new TeamDto(team);
        return ResponseBuilder.created()
                .data(teamDto)
                .build();
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response<TeamDto> joinTeamWithJoinCode(@RequestBody JoinTeamCommand joinTeamCommand) {
        AuthUser authUser = authService.getCurrentUser();
        teamService.useJoinCodeForParticipant(joinTeamCommand.getJoinCode(), authUser.getParticipantId());
        Team team = teamQueryService.getTeamByJoinCode(joinTeamCommand.getJoinCode());
        TeamDto teamDto = new TeamDto(team);
        return ResponseBuilder.accepted()
                .data(teamDto)
                .build();
    }

    @GetMapping("/{teamId}/participants")
    @ResponseStatus(HttpStatus.OK)
    public Response<Collection<ParticipantDto>> getParticipantsByTeamId(@PathVariable UUID teamId) {
        TeamId id = TeamId.fromUUID(teamId);
        Collection<Participant> participants = participantQueryService.getParticipantsByTeamId(id);
        Collection<ParticipantDto> dtos = participants.stream()
                .map(ParticipantDto::new)
                .collect(Collectors.toSet());
        return ResponseBuilder.ok()
                .data(dtos)
                .build();
    }

    @DeleteMapping("/{teamId}/participants/{participantId}")
    @ResponseStatus(HttpStatus.OK)
    public Response<TeamDto> removeParticipantFromTeam(@PathVariable UUID teamId, @PathVariable UUID participantId) {
        TeamId id = TeamId.fromUUID(teamId);
        ParticipantId participantId1 = ParticipantId.fromUUID(participantId);
        teamService.removeParticipantFromTeam(id, participantId1);
        Team team = teamQueryService.getTeamById(id);
        TeamDto teamDto = new TeamDto(team);
        return ResponseBuilder.created()
                .data(teamDto)
                .build();
    }

}
