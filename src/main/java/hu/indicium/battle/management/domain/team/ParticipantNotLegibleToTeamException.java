package hu.indicium.battle.management.domain.team;

public class ParticipantNotLegibleToTeamException extends RuntimeException {
    public ParticipantNotLegibleToTeamException() {
        super("This participant is not able to start or join a new team.");
    }
}
