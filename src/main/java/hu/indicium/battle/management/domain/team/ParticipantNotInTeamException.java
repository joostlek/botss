package hu.indicium.battle.management.domain.team;

public class ParticipantNotInTeamException extends RuntimeException {
    public ParticipantNotInTeamException() {
        super("Participant is not in this team.");
    }
}
