package hu.indicium.battle.management.infrastructure.web.dto;

import hu.indicium.battle.management.domain.association.Association;
import lombok.Data;

@Data
public class AssociationDto {
    private String slug;

    private String name;

    private String logoUrl;

    public AssociationDto(Association association) {
        this.slug = association.getId().getSlug();
        this.name = association.getName();
        this.logoUrl = association.getLogoUrl();
    }
}
