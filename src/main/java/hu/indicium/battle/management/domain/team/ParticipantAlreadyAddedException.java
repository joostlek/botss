package hu.indicium.battle.management.domain.team;

public class ParticipantAlreadyAddedException extends RuntimeException {
    public ParticipantAlreadyAddedException() {
        super("Participant is already in this team.");
    }
}
