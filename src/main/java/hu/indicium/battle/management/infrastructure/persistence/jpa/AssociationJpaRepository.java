package hu.indicium.battle.management.infrastructure.persistence.jpa;

import hu.indicium.battle.management.domain.association.Association;
import hu.indicium.battle.management.domain.association.AssociationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationJpaRepository extends JpaRepository<Association, String> {
    boolean existsById(AssociationId associationId);

    Association findAssociationById(AssociationId associationId);
}
