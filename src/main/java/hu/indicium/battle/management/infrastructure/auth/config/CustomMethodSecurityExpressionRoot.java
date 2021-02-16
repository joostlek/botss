package hu.indicium.battle.management.infrastructure.auth.config;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.participant.ParticipantRepository;
import hu.indicium.battle.management.domain.team.TeamId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.UUID;

@Slf4j
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    Object filterObject;

    Object returnObject;

    private final ParticipantRepository participantRepository;

    /**
     * Creates a new instance
     *
     * @param authentication the {@link Authentication} to use. Cannot be null.
     * @param participantRepository
     */
    public CustomMethodSecurityExpressionRoot(Authentication authentication, ParticipantRepository participantRepository) {
        super(authentication);
        this.participantRepository = participantRepository;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    public boolean hasPermission(String permission) {
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            if (grantedAuthority.getAuthority().equals("SCOPE_botss/" + permission)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTeamCaptain(UUID teamUuid) {
        Participant participant = getParticipantFromToken();
        TeamId teamId = TeamId.fromUUID(teamUuid);
        return participant.isInTeam(teamId) && participant.isCaptain();
    }

    public boolean isPartOfTeam(UUID teamUuid) {
        Participant participant = getParticipantFromToken();
        TeamId teamId = TeamId.fromUUID(teamUuid);
        return participant.isInTeam(teamId);
    }

    public boolean isPartOfAssociation(String associationSlug) {
        Participant participant = getParticipantFromToken();
        AssociationId associationId = AssociationId.fromSlug(associationSlug);
        return participant.isInAssociation(associationId);
    }

    public boolean hasPaid() {
        Participant participant = getParticipantFromToken();
        return participant.hasPaid();
    }

    public boolean userIdEquals(String userId) {
        return authentication.getName().equals(userId);
    }

    public Participant getParticipantFromToken() {
        ParticipantId participantId = ParticipantId.fromUUID(UUID.fromString(authentication.getName()));
        return participantRepository.getParticipantById(participantId);
    }
}
