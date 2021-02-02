package hu.indicium.battle.management.infrastructure.web.controllers;


import hu.indicium.battle.management.application.commands.CreatePaymentCommand;
import hu.indicium.battle.management.application.query.ParticipantQueryService;
import hu.indicium.battle.management.application.query.PaymentQueryService;
import hu.indicium.battle.management.application.service.ParticipantService;
import hu.indicium.battle.management.application.service.PaymentService;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.payment.Payment;
import hu.indicium.battle.management.domain.participant.payment.PaymentId;
import hu.indicium.battle.management.infrastructure.util.BaseUrl;
import hu.indicium.battle.management.infrastructure.util.Response;
import hu.indicium.battle.management.infrastructure.util.ResponseBuilder;
import hu.indicium.battle.management.infrastructure.web.dto.ParticipantDto;
import hu.indicium.battle.management.infrastructure.web.dto.PaymentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(BaseUrl.API_V1 + "/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    private final ParticipantQueryService participantQueryService;

    private final PaymentQueryService paymentQueryService;

    private final PaymentService paymentService;

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

    @GetMapping("/{participantId}/payments")
    @ResponseStatus(HttpStatus.OK)
    public Response<Collection<PaymentDto>> getPaymentsByParticipantId(@PathVariable("participantId") UUID participantUuid) {
        ParticipantId participantId = ParticipantId.fromUUID(participantUuid);
        Collection<Payment> payments = paymentQueryService.getPaymentsByParticipantId(participantId);
        Collection<PaymentDto> dtos = payments.stream()
                .map(PaymentDto::new)
                .collect(Collectors.toSet());
        return ResponseBuilder.ok()
                .data(dtos)
                .build();
    }

    @PostMapping("/{participantId}/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<PaymentDto> createPayment(@PathVariable("participantId") UUID participantUuid, @RequestBody CreatePaymentCommand createPaymentCommand) {
        ParticipantId participantId = ParticipantId.fromUUID(participantUuid);
        createPaymentCommand.setParticipantId(participantId);
        PaymentId paymentId = paymentService.createPayment(createPaymentCommand);
        Payment payment = paymentQueryService.getPaymentByPaymentId(paymentId);
        PaymentDto paymentDto = new PaymentDto(payment);
        return ResponseBuilder.ok()
                .data(paymentDto)
                .build();
    }
}
