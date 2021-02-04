package hu.indicium.battle.management.infrastructure.auth;

import hu.indicium.battle.management.domain.participant.ParticipantId;
import lombok.Data;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

@Data
public class AuthUser {
    private ParticipantId participantId;

    private String firstName;

    private String lastName;

    private String email;

    public AuthUser(Jwt authentication) {
        this.participantId = ParticipantId.fromUUID(UUID.fromString(authentication.getClaimAsString("sub")));
        this.firstName = authentication.getClaimAsString("given_name");
        this.lastName = authentication.getClaimAsString("family_name");
        this.email = authentication.getClaimAsString("email");
    }
}
