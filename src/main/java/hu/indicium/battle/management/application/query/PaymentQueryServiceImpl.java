package hu.indicium.battle.management.application.query;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.payment.Payment;
import hu.indicium.battle.management.domain.participant.payment.PaymentId;
import hu.indicium.battle.management.domain.participant.payment.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class PaymentQueryServiceImpl implements PaymentQueryService {

    private final PaymentRepository paymentRepository;

    @Override
    @PreAuthorize("hasPermission('manage-payment')")
    public Collection<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }

    @Override
    @PreAuthorize("hasPermission('manage-payment') || (hasPermission('manage-association') && isPartOfAssociation(#associationId.slug))")
    public Collection<Payment> getPaymentsByAssociationId(AssociationId associationId) {
        return paymentRepository.getPaymentsByAssociationId(associationId);
    }

    @Override
    @PreAuthorize("hasPermission('manage-participant') || userIdEquals(#participantId.id)")
    public Collection<Payment> getPaymentsByParticipantId(ParticipantId participantId) {
        return paymentRepository.getPaymentsByParticipantId(participantId);
    }

    @Override
    @PostAuthorize("hasPermission('manage-payment') || userIdEquals(returnObject.participant.id.id)")
    public Payment getPaymentByPaymentId(PaymentId paymentId) {
        return paymentRepository.getPaymentByPaymentId(paymentId);
    }
}
