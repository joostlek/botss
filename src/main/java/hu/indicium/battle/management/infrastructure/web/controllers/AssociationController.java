package hu.indicium.battle.management.infrastructure.web.controllers;

import hu.indicium.battle.management.application.commands.CreateAssociationCommand;
import hu.indicium.battle.management.application.commands.CreateParticipantCommand;
import hu.indicium.battle.management.application.query.AssociationQueryService;
import hu.indicium.battle.management.application.query.ParticipantQueryService;
import hu.indicium.battle.management.application.query.PaymentQueryService;
import hu.indicium.battle.management.application.service.AssociationService;
import hu.indicium.battle.management.application.service.ParticipantService;
import hu.indicium.battle.management.domain.association.Association;
import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.payment.Payment;
import hu.indicium.battle.management.infrastructure.util.BaseUrl;
import hu.indicium.battle.management.infrastructure.util.Response;
import hu.indicium.battle.management.infrastructure.util.ResponseBuilder;
import hu.indicium.battle.management.infrastructure.web.dto.AssociationDto;
import hu.indicium.battle.management.infrastructure.web.dto.ParticipantDto;
import hu.indicium.battle.management.infrastructure.web.dto.PaymentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(BaseUrl.API_V1 + "/associations")
@AllArgsConstructor
public class AssociationController {

    private final AssociationQueryService associationQueryService;

    private final AssociationService associationService;

    private final ParticipantQueryService participantQueryService;

    private final ParticipantService participantService;

    private final PaymentQueryService paymentQueryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<Collection<AssociationDto>> getAllAssociations() {
        Collection<Association> associations = associationQueryService.getAllAssociations();
        Collection<AssociationDto> dtos = associations.stream()
                .map(AssociationDto::new)
                .collect(Collectors.toSet());
        return ResponseBuilder.ok()
                .data(dtos)
                .build();
    }

    @GetMapping("/{associationSlug}")
    @ResponseStatus(HttpStatus.OK)
    public Response<AssociationDto> getAssociationByAssociationSlug(@PathVariable String associationSlug) {
        AssociationId associationId = AssociationId.fromSlug(associationSlug);
        Association association = associationQueryService.getAssociationByAssociationId(associationId);
        AssociationDto associationDto = new AssociationDto(association);
        return ResponseBuilder.ok()
                .data(associationDto)
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<AssociationDto> createAssociation(@RequestBody CreateAssociationCommand createAssociationCommand) {
        AssociationId associationId = associationService.createAssociation(createAssociationCommand);
        Association association = associationQueryService.getAssociationByAssociationId(associationId);
        AssociationDto associationDto = new AssociationDto(association);
        return ResponseBuilder.created()
                .data(associationDto)
                .build();
    }

    @GetMapping("/{associationSlug}/participants")
    @ResponseStatus(HttpStatus.OK)
    public Response<Collection<ParticipantDto>> getParticipantsByAssociationId(@PathVariable String associationSlug) {
        AssociationId associationId = AssociationId.fromSlug(associationSlug);
        Collection<Participant> participants = participantQueryService.getParticipantsByAssociationId(associationId);
        Collection<ParticipantDto> dtos = participants.stream()
                .map(ParticipantDto::new)
                .collect(Collectors.toSet());
        return ResponseBuilder.ok()
                .data(dtos)
                .build();
    }

    @PostMapping("/{associationSlug}/participants")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<ParticipantDto> createParticipant(@PathVariable String associationSlug, @RequestBody CreateParticipantCommand createParticipantCommand) {
        createParticipantCommand.setAssociationSlug(associationSlug);
        ParticipantId participantId = participantService.createParticipant(createParticipantCommand);
        Participant participant = participantQueryService.getParticipantById(participantId);
        ParticipantDto participantDto = new ParticipantDto(participant);
        return ResponseBuilder.created()
                .data(participantDto)
                .build();
    }

    @GetMapping("/{associationSlug}/payments")
    @ResponseStatus(HttpStatus.OK)
    public Response<Collection<PaymentDto>> getPaymentsByAssociation(@PathVariable String associationSlug) {
        AssociationId associationId = AssociationId.fromSlug(associationSlug);
        Collection<Payment> payments = paymentQueryService.getPaymentsByAssociationId(associationId);
        Collection<PaymentDto> dtos = payments.stream()
                .map(PaymentDto::new)
                .collect(Collectors.toSet());
        return ResponseBuilder.ok()
                .data(dtos)
                .build();
    }
}
