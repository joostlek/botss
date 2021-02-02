package hu.indicium.battle.management.domain.participant;

import hu.indicium.battle.management.domain.AssertionConcern;
import hu.indicium.battle.management.domain.association.Association;
import hu.indicium.battle.management.domain.participant.payment.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Participant extends AssertionConcern {
    @EmbeddedId
    private ParticipantId id;

    @Embedded
    private ParticipantDetails participantDetails;

    @ManyToOne()
    private Association association;

    @OneToMany(mappedBy = "participant")
    private Collection<Payment> payments;

    public Participant(ParticipantId id, ParticipantDetails participantDetails, Association association) {
        this.setId(id);
        this.setParticipantDetails(participantDetails);
        this.setAssociation(association);
        this.setPayments(new ArrayList<>());
    }

    public boolean hasActivePayment() {
        for (Payment payment : payments) {
            if (payment.isActive()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPaid() {
        for (Payment payment : payments) {
            if (payment.isPaid()) {
                return true;
            }
        }
        return false;
    }

    public String getFullName() {
        return participantDetails.getFirstName() + participantDetails.getLastName();
    }

    public void setId(ParticipantId participantId) {
        this.id = participantId;
    }

    public void setParticipantDetails(ParticipantDetails participantDetails) {
        this.participantDetails = participantDetails;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
