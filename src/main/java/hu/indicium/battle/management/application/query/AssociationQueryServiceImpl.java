package hu.indicium.battle.management.application.query;

import hu.indicium.battle.management.domain.association.Association;
import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.association.AssociationRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AssociationQueryServiceImpl implements AssociationQueryService {

    private final AssociationRepository associationRepository;

    @Override
    public Collection<Association> getAllAssociations() {
        return associationRepository.getAllAssociations();
    }

    @Override
    @PreAuthorize("hasPermission('view-association')")
    public Association getAssociationByAssociationId(AssociationId associationId) {
        return associationRepository.getAssociationById(associationId);
    }
}
