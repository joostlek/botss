package hu.indicium.battle.management.application.query;

import hu.indicium.battle.management.domain.association.Association;
import hu.indicium.battle.management.domain.association.AssociationId;

import java.util.Collection;

public interface AssociationQueryService {
    Collection<Association> getAllAssociations();

    Association getAssociationByAssociationId(AssociationId associationId);
}
