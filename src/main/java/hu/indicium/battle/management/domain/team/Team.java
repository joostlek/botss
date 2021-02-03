package hu.indicium.battle.management.domain.team;

import hu.indicium.battle.management.domain.AssertionConcern;
import hu.indicium.battle.management.domain.participant.Participant;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
public class Team extends AssertionConcern {
    @EmbeddedId
    private TeamId id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String joinCode;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Collection<Participant> members;

    @OneToOne
    @JoinColumn(name = "captain_id", referencedColumnName = "id")
    private Participant captain;

    public static final int MIN_PARTICIPANTS = 1;

    public static final int MAX_PARTICIPANTS = 4;

    public Team(TeamId id, String name, Participant captain) {
        this.setId(id);
        this.setName(name);
        this.setCaptain(captain);
        Set<Participant> participants = new HashSet<>();
        participants.add(captain);
        this.setMembers(participants);
        captain.setTeam(this);
        this.refreshJoinCode();
    }

    public void refreshJoinCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        this.joinCode = Integer.toString(code);
    }

    public void addParticipant(Participant participant) {
        if (hasParticipant(participant)) {
            throw new ParticipantAlreadyAddedException();
        }
        if (participant.isInTeam()) {
            throw new ParticipantAlreadyInTeamException();
        }
        if (getTeamSize() >= MAX_PARTICIPANTS) {
            throw new TeamFullException();
        }
        members.add(participant);
        participant.setTeam(this);
    }

    public void removeParticipant(Participant participant) {
        if (!this.hasParticipant(participant)) {
            throw new ParticipantNotInTeamException();
        }
        if (participant.isCaptain()) {
            throw new CaptainNotRemovableException();
        }
        participant.setTeam(null);
        members.remove(participant);
    }

    public boolean isEligible() {
        return getTeamSize() >= MIN_PARTICIPANTS && getTeamSize() <= MAX_PARTICIPANTS;
    }

    public int getTeamSize() {
        return members.size();
    }

    public boolean hasParticipant(Participant participant) {
        return members.contains(participant);
    }

    private void setId(TeamId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setMembers(Collection<Participant> members) {
        this.members = members;
    }

    private void setCaptain(Participant captain) {
        this.captain = captain;
    }
}
