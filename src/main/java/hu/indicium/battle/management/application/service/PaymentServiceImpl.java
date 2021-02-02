package hu.indicium.battle.management.application.service;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.payment.PaymentRequest;
import be.woutschoovaerts.mollie.data.payment.PaymentResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import hu.indicium.battle.management.application.commands.CreatePaymentCommand;
import hu.indicium.battle.management.domain.association.AssociationRepository;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.ParticipantRepository;
import hu.indicium.battle.management.domain.participant.payment.*;
import hu.indicium.battle.management.infrastructure.mollie.MolliePayment;
import hu.indicium.battle.management.infrastructure.mollie.MollieService;
import hu.indicium.battle.management.infrastructure.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final ParticipantRepository participantRepository;

    private final MollieService mollieService;

    @Override
    public PaymentId createPayment(CreatePaymentCommand createPaymentCommand) {
        ParticipantId participantId = createPaymentCommand.getParticipantId();

        Participant participant = participantRepository.getParticipantById(participantId);

        if (participant.hasPaid()) {
            throw new ParticipantHasAlreadyPaidException();
        }

        if (participant.hasActivePayment()) {
            throw new ParticipantHasActivePaymentException();
        }

        PaymentId paymentId = PaymentId.fromId(UUID.randomUUID());

        Payment payment = new Payment(paymentId, participant, "Battle of the study societies entree", 1.5);

        MolliePayment molliePayment = mollieService.createPayment(payment, createPaymentCommand.getRedirectUrl());

        MollieDetails mollieDetails = new MollieDetails(molliePayment.getExternalId(), "mollie", molliePayment.getExpiresAt(), molliePayment.getCheckoutUrl(), molliePayment.getWebhookUrl(), molliePayment.getRedirectUrl());

        payment.setMollieDetails(mollieDetails);

        paymentRepository.save(payment);

        return paymentId;
    }
}
