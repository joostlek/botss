package hu.indicium.battle.management.application.service;

import hu.indicium.battle.management.application.commands.CreateAssociationCommand;
import hu.indicium.battle.management.domain.association.Association;
import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.domain.association.AssociationRepository;
import hu.indicium.battle.management.domain.association.AssociationSlugAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssociationServiceImpl implements AssociationService {

    private final AssociationRepository associationRepository;

    @Override
    public AssociationId createAssociation(CreateAssociationCommand createAssociationCommand) {
        AssociationId associationId = AssociationId.fromSlug(createAssociationCommand.getSlug());

        if (associationRepository.existsById(associationId)) {
            throw new AssociationSlugAlreadyExistsException();
        }

        Association association = new Association(associationId, createAssociationCommand.getName(), createAssociationCommand.getLogoUrl());

        associationRepository.save(association);

        return associationId;
    }
}
