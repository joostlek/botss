package hu.indicium.battle.management.domain.team;

public class TeamFullException extends RuntimeException {
    public TeamFullException() {
        super("Team is already full.");
    }
}
