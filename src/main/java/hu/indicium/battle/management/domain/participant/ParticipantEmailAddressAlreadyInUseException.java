package hu.indicium.battle.management.domain.participant;

public class ParticipantEmailAddressAlreadyInUseException extends RuntimeException {
    public ParticipantEmailAddressAlreadyInUseException() {
        super("Given email address is already in use.");
    }
}
