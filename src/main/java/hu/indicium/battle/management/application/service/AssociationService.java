package hu.indicium.battle.management.application.service;

import hu.indicium.battle.management.application.commands.CreateAssociationCommand;
import hu.indicium.battle.management.domain.association.AssociationId;

public interface AssociationService {
    AssociationId createAssociation(CreateAssociationCommand createAssociationCommand);
}
