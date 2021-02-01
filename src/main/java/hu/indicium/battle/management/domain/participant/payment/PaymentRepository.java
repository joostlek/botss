package hu.indicium.battle.management.domain.participant.payment;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.ParticipantId;

import java.util.Collection;

public interface PaymentRepository {
    Collection<Payment> getAllPayments();

    Collection<Payment> getPaymentsByAssociationId(AssociationId associationId);

    Collection<Payment> getPaymentsByParticipantId(ParticipantId participantId);

    Payment getPaymentByExternalId(String externalId);

    Payment getPaymentByPaymentId(PaymentId paymentId);

    Payment save(Payment payment);
}
