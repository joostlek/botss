package hu.indicium.battle.management.domain.participant.payment;

import hu.indicium.battle.management.domain.AssertionConcern;
import hu.indicium.battle.management.domain.participant.Participant;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Payment extends AssertionConcern {
    @EmbeddedId
    private PaymentId paymentId;

    private Double amount;

    private String description;

    private Date createdAt;

    private Date updatedAt;

    private PaymentStatus status;

    @Embedded
    private MollieDetails mollieDetails;

    @ManyToOne()
    private Participant participant;

    public Payment(PaymentId paymentId, Participant participant, Double amount) {
        this.setPaymentId(paymentId);
        this.setParticipant(participant);
        this.setAmount(amount);
        this.setDescription(description);
        this.setCreatedAt(new Date());
        this.setStatus(PaymentStatus.OPEN);
    }

    public boolean isActive() {
        return status == PaymentStatus.OPEN || status == PaymentStatus.PENDING;
    }

    public boolean isPaid() {
        return status == PaymentStatus.PAID;
    }

    public void addMollieDetails(MollieDetails mollieDetails) {
        this.setMollieDetails(mollieDetails);
    }

    public void updateStatus(PaymentStatus paymentStatus) {
        this.setStatus(paymentStatus);
        this.setUpdatedAt(new Date());
    }

    public void setPaymentId(PaymentId paymentId) {
        this.paymentId = paymentId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    private void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public void setMollieDetails(MollieDetails mollieDetails) {
        this.mollieDetails = mollieDetails;
    }
}
