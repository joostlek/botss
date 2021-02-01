package hu.indicium.battle.management.application.query;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.payment.Payment;
import hu.indicium.battle.management.domain.participant.payment.PaymentId;
import hu.indicium.battle.management.domain.participant.payment.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class PaymentQueryServiceImpl implements PaymentQueryService {

    private final PaymentRepository paymentRepository;

    @Override
    public Collection<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }

    @Override
    public Collection<Payment> getPaymentsByAssociationId(AssociationId associationId) {
        return paymentRepository.getPaymentsByAssociationId(associationId);
    }

    @Override
    public Collection<Payment> getPaymentsByParticipantId(ParticipantId participantId) {
        return paymentRepository.getPaymentsByParticipantId(participantId);
    }

    @Override
    public Payment getPaymentByPaymentId(PaymentId paymentId) {
        return paymentRepository.getPaymentByPaymentId(paymentId);
    }
}
