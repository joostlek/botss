package hu.indicium.battle.management.infrastructure.persistence.jpa;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.payment.Payment;
import hu.indicium.battle.management.domain.participant.payment.PaymentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface PaymentJpaRepository extends JpaRepository<Payment, UUID> {
    Collection<Payment> getPaymentsByParticipantId(ParticipantId participantId);

    Collection<Payment> getPaymentsByParticipantAssociationId(AssociationId associationId);

    Payment getPaymentByMollieDetailsExternalId(String externalId);

    Payment getPaymentByPaymentId(PaymentId paymentId);
}
