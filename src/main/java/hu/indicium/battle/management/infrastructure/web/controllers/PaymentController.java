package hu.indicium.battle.management.infrastructure.web.controllers;

import hu.indicium.battle.management.application.commands.CreatePaymentCommand;
import hu.indicium.battle.management.application.query.ParticipantQueryService;
import hu.indicium.battle.management.application.query.PaymentQueryService;
import hu.indicium.battle.management.application.service.PaymentService;
import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.payment.Payment;
import hu.indicium.battle.management.domain.participant.payment.PaymentId;
import hu.indicium.battle.management.infrastructure.util.BaseUrl;
import hu.indicium.battle.management.infrastructure.util.Response;
import hu.indicium.battle.management.infrastructure.util.ResponseBuilder;
import hu.indicium.battle.management.infrastructure.web.dto.PaymentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(BaseUrl.API_V1)
public class PaymentController {

    private final PaymentService paymentService;

    private final PaymentQueryService paymentQueryService;

    @GetMapping("/payments")
    @ResponseStatus(HttpStatus.OK)
    public Response<Collection<PaymentDto>> getAllPayments() {
        Collection<Payment> payments = paymentQueryService.getAllPayments();
        Collection<PaymentDto> dtos = payments.stream()
                .map(PaymentDto::new)
                .collect(Collectors.toSet());
        return ResponseBuilder.ok()
                .data(dtos)
                .build();
    }

    @GetMapping("/participants/{participantId}/payments")
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

    @GetMapping("/associations/{associationSlug}/payments")
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

    @GetMapping("/payments/{paymentId}")
    @ResponseStatus(HttpStatus.OK)
    public Response<PaymentDto> getPaymentById(@PathVariable UUID paymentId) {
        PaymentId id = PaymentId.fromId(paymentId);
        Payment payment = paymentQueryService.getPaymentByPaymentId(id);
        PaymentDto paymentDto = new PaymentDto(payment);
        return ResponseBuilder.ok()
                .data(paymentDto)
                .build();
    }

    @PostMapping("/participants/{participantId}/payments")
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
