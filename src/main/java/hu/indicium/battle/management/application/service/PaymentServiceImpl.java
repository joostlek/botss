package hu.indicium.battle.management.application.service;

import hu.indicium.battle.management.application.commands.CreatePaymentCommand;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.ParticipantRepository;
import hu.indicium.battle.management.domain.participant.payment.*;
import hu.indicium.battle.management.infrastructure.mollie.MolliePayment;
import hu.indicium.battle.management.infrastructure.mollie.MollieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@EnableAsync
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final ParticipantRepository participantRepository;

    private final MollieService mollieService;

    @Override
    @PreAuthorize("hasPermission('create-payment')")
    public PaymentId createPayment(CreatePaymentCommand createPaymentCommand) {
        ParticipantId participantId = createPaymentCommand.getParticipantId();

        Participant participant = participantRepository.getParticipantById(participantId);

        if (participant.hasPaid()) {
            throw new ParticipantHasAlreadyPaidException();
        }

        if (participant.hasActivePayment()) {
            return participant.getActivePayment().getPaymentId();
        }

        PaymentId paymentId = PaymentId.fromId(UUID.randomUUID());

        Payment payment = new Payment(paymentId, participant, "Battle of the study societies entree", 1.5);

        MolliePayment molliePayment = mollieService.createPayment(payment, createPaymentCommand.getRedirectUrl());

        MollieDetails mollieDetails = new MollieDetails(molliePayment.getExternalId(), "mollie", molliePayment.getExpiresAt(), molliePayment.getCheckoutUrl(), molliePayment.getWebhookUrl(), molliePayment.getRedirectUrl());

        payment.addMollieDetails(mollieDetails);

        paymentRepository.save(payment);

        return paymentId;
    }

    @Override
    @Async
    public void updatePayment(String externalId) {
        MolliePayment molliePayment = mollieService.getPayment(externalId);

        Payment payment = paymentRepository.getPaymentByExternalId(externalId);

        Participant participant = payment.getParticipant();

        log.info("Updating transaction for {}", participant.getFullName());

        payment.updateStatus(molliePayment.getStatus());

        paymentRepository.save(payment);
    }
}
