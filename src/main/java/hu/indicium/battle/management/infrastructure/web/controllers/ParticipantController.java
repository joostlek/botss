package hu.indicium.battle.management.infrastructure.web.controllers;


import hu.indicium.battle.management.application.query.ParticipantQueryService;
import hu.indicium.battle.management.application.service.ParticipantService;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.infrastructure.util.BaseUrl;
import hu.indicium.battle.management.infrastructure.util.Response;
import hu.indicium.battle.management.infrastructure.util.ResponseBuilder;
import hu.indicium.battle.management.infrastructure.web.dto.ParticipantDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(BaseUrl.API_V1 + "/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    private final ParticipantQueryService participantQueryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Response<ParticipantDto> getAllParticipants() {
        Collection<Participant> participants = participantQueryService.getAllParticipants();
        Collection<ParticipantDto> dtos = participants.stream()
                .map(ParticipantDto::new)
                .collect(Collectors.toSet());
        return ResponseBuilder.ok()
                .data(dtos)
                .build();
    }
}
