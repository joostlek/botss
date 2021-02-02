package hu.indicium.battle.management.domain.participant.payment;

public class ParticipantHasAlreadyPaidException extends RuntimeException {
    public ParticipantHasAlreadyPaidException() {
        super("Participant has already paid.");
    }
}
