package hu.indicium.battle.management.domain.participant.payment;

public class ParticipantHasActivePaymentException extends RuntimeException {
    public ParticipantHasActivePaymentException() {
        super("Participant has still an active payment.");
    }
}
