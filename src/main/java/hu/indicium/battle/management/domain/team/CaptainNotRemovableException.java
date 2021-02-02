package hu.indicium.battle.management.domain.team;

public class CaptainNotRemovableException extends RuntimeException {
    public CaptainNotRemovableException() {
        super("The captain can't be removed from the group.");
    }
}
