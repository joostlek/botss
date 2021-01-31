package hu.indicium.battle.management.infrastructure.persistence;

import hu.indicium.battle.management.domain.association.Association;
import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.association.AssociationRepository;
import hu.indicium.battle.management.infrastructure.persistence.jpa.AssociationJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class AssociationRepositoryImpl implements AssociationRepository {

    private final AssociationJpaRepository associationJpaRepository;

    @Override
    public boolean existsById(AssociationId associationId) {
        return associationJpaRepository.existsById(associationId);
    }

    @Override
    public Collection<Association> getAllAssociations() {
        return associationJpaRepository.findAll();
    }

    @Override
    public Association getAssociationById(AssociationId associationId) {
        return associationJpaRepository.findAssociationById(associationId);
    }

    @Override
    public Association save(Association association) {
        return associationJpaRepository.save(association);
    }
}
