package hu.indicium.battle.management.application.query;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.payment.Payment;
import hu.indicium.battle.management.domain.participant.payment.PaymentId;

import java.util.Collection;

public interface PaymentQueryService {
    Collection<Payment> getAllPayments();

    Collection<Payment> getPaymentsByAssociationId(AssociationId associationId);

    Collection<Payment> getPaymentsByParticipantId(ParticipantId participantId);

    Payment getPaymentByPaymentId(PaymentId paymentId);
}
