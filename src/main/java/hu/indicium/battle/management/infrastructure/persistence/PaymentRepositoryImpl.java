package hu.indicium.battle.management.infrastructure.persistence;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.payment.Payment;
import hu.indicium.battle.management.domain.participant.payment.PaymentId;
import hu.indicium.battle.management.domain.participant.payment.PaymentRepository;
import hu.indicium.battle.management.infrastructure.persistence.jpa.PaymentJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentRepository;

    @Override
    public Collection<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Collection<Payment> getPaymentsByAssociationId(AssociationId associationId) {
        return paymentRepository.getPaymentsByParticipantAssociationId(associationId);
    }

    @Override
    public Collection<Payment> getPaymentsByParticipantId(ParticipantId participantId) {
        return paymentRepository.getPaymentsByParticipantId(participantId);
    }

    @Override
    public Payment getPaymentByExternalId(String externalId) {
        return paymentRepository.getPaymentByMollieDetailsExternalId(externalId);
    }

    @Override
    public Payment getPaymentByPaymentId(PaymentId paymentId) {
        return paymentRepository.getPaymentByPaymentId(paymentId);
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}
