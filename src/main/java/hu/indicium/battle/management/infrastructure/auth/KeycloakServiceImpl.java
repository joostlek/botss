package hu.indicium.battle.management.infrastructure.auth;

import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.participant.ParticipantId;
import hu.indicium.battle.management.domain.team.TeamId;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

    public static final String ASSOCIATION_ATTRIBUTE = "associationSlug";

    public static final String TEAM_ID_ATTRIBUTE = "teamId";

    public static final String TEAM_CAPTAIN_ATTRIBUTE = "teamCaptain";

    private final UsersResource usersResource;

    @Override
    public void setAssociationForParticipant(ParticipantId participantId, AssociationId associationId) {
        UserResource userResource = usersResource.get(participantId.getId().toString());
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userRepresentation.singleAttribute(ASSOCIATION_ATTRIBUTE, associationId.getSlug());
        userResource.update(userRepresentation);
    }

    @Override
    public void setTeamForParticipant(ParticipantId participantId, TeamId teamId) {
        UserResource userResource = usersResource.get(participantId.getId().toString());
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userRepresentation.singleAttribute(TEAM_ID_ATTRIBUTE, teamId.getId().toString())
                .singleAttribute(TEAM_CAPTAIN_ATTRIBUTE, "false");
        userResource.update(userRepresentation);
    }

    @Override
    public void setTeamForTeamCaptain(ParticipantId participantId, TeamId teamId) {
        UserResource userResource = usersResource.get(participantId.getId().toString());
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userRepresentation.singleAttribute(TEAM_ID_ATTRIBUTE, teamId.getId().toString())
                .singleAttribute(TEAM_CAPTAIN_ATTRIBUTE, "true");
        userResource.update(userRepresentation);
    }

    @Override
    public void resetTeamForParticipant(ParticipantId participantId) {
        UserResource userResource = usersResource.get(participantId.getId().toString());
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userRepresentation.singleAttribute(TEAM_ID_ATTRIBUTE, "")
                .singleAttribute(TEAM_CAPTAIN_ATTRIBUTE, "false");
        userResource.update(userRepresentation);
    }
}
