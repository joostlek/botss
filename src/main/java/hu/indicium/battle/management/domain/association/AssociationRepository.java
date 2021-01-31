package hu.indicium.battle.management.domain.association;

import java.util.Collection;

public interface AssociationRepository {
    boolean existsById(AssociationId associationId);

    Collection<Association> getAllAssociations();

    Association getAssociationById(AssociationId associationId);

    Association save(Association association);
}
