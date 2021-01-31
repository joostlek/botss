package hu.indicium.battle.management.infrastructure.web.controllers;

import hu.indicium.battle.management.application.commands.CreateAssociationCommand;
import hu.indicium.battle.management.application.query.AssociationQueryService;
import hu.indicium.battle.management.application.service.AssociationService;
import hu.indicium.battle.management.domain.association.Association;
import hu.indicium.battle.management.domain.association.AssociationId;
import hu.indicium.battle.management.infrastructure.util.BaseUrl;
import hu.indicium.battle.management.infrastructure.util.Response;
import hu.indicium.battle.management.infrastructure.util.ResponseBuilder;
import hu.indicium.battle.management.infrastructure.web.dto.AssociationDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(BaseUrl.API_V1 + "/associations")
@AllArgsConstructor
public class AssociationController {

    private final AssociationQueryService associationQueryService;

    private final AssociationService associationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<Collection<AssociationDto>> getAllAssociations() {
        Collection<Association> associations = associationQueryService.getAllAssociations();
        Collection<AssociationDto> dtos = associations.stream()
                .map(AssociationDto::new)
                .collect(Collectors.toSet());
        return ResponseBuilder.ok()
                .data(dtos)
                .build();
    }

    @GetMapping("/{associationSlug}")
    @ResponseStatus(HttpStatus.OK)
    public Response<AssociationDto> getAssociationByAssociationId(@RequestParam String associationSlug) {
        AssociationId associationId = AssociationId.fromSlug(associationSlug);
        Association association = associationQueryService.getAssociationByAssociationId(associationId);
        AssociationDto associationDto = new AssociationDto(association);
        return ResponseBuilder.ok()
                .data(associationDto)
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<AssociationDto> createAssociation(@RequestBody CreateAssociationCommand createAssociationCommand) {
        AssociationId associationId = associationService.createAssociation(createAssociationCommand);
        Association association = associationQueryService.getAssociationByAssociationId(associationId);
        AssociationDto associationDto = new AssociationDto(association);
        return ResponseBuilder.created()
                .data(associationDto)
                .build();
    }
}
