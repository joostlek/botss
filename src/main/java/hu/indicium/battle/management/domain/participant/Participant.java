package hu.indicium.battle.management.domain.participant;

import hu.indicium.battle.management.domain.AssertionConcern;
import hu.indicium.battle.management.domain.association.Association;
import hu.indicium.battle.management.domain.participant.payment.Payment;
import hu.indicium.battle.management.domain.team.ParticipantNotLegibleToTeamException;
import hu.indicium.battle.management.domain.team.Team;
import hu.indicium.battle.management.domain.team.TeamId;
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

    @ManyToOne()
    private Team team;

    @OneToOne(mappedBy = "captain")
    private Team captainOf;

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

    public boolean isInTeam() {
        return this.team != null;
    }

    public boolean isCaptain() {
        return this.captainOf != null;
    }

    public boolean eligibleToJoin() {
        return hasPaid() && !isInTeam();
    }

    public String getFullName() {
        return participantDetails.getFirstName() + participantDetails.getLastName();
    }

    private void setId(ParticipantId participantId) {
        this.id = participantId;
    }

    private void setParticipantDetails(ParticipantDetails participantDetails) {
        this.participantDetails = participantDetails;
    }

    private void setAssociation(Association association) {
        this.association = association;
    }

    private void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void setTeam(Team team) {
        if (team != null && !this.eligibleToJoin()) {
            throw new ParticipantNotLegibleToTeamException();
        }
        this.team = team;
    }
}
