package hu.indicium.battle.management.domain.participant;

import hu.indicium.battle.management.domain.AssertionConcern;
import hu.indicium.battle.management.domain.association.Association;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Entity
@NoArgsConstructor
public class Participant extends AssertionConcern {
    @EmbeddedId
    private ParticipantId participantId;

    @Embedded
    private ParticipantDetails participantDetails;

    @ManyToOne()
    private Association association;

    public Participant(ParticipantId participantId, ParticipantDetails participantDetails, Association association) {
        this.setParticipantId(participantId);
        this.setParticipantDetails(participantDetails);
        this.setAssociation(association);
    }

    public void setParticipantId(ParticipantId participantId) {
        this.participantId = participantId;
    }

    public void setParticipantDetails(ParticipantDetails participantDetails) {
        this.participantDetails = participantDetails;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }
}
