package hu.indicium.battle.management.domain.team;

public class ParticipantAlreadyInTeamException extends RuntimeException {
    public ParticipantAlreadyInTeamException() {
        super("Participant is already in another team.");
    }
}
