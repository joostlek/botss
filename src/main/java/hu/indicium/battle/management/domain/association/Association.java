package hu.indicium.battle.management.domain.association;

import hu.indicium.battle.management.domain.AssertionConcern;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Entity
@NoArgsConstructor
public class Association extends AssertionConcern {
    @EmbeddedId
    private AssociationId id;

    private String name;

    private String logoUrl;

    public Association(AssociationId id, String name, String logoUrl) {
        this.setId(id);
        this.setName(name);
        this.setLogoUrl(logoUrl);
    }

    public void setId(AssociationId id) {
        this.assertArgumentNotNull(id, "Association id must not be null.");

        this.id = id;
    }

    public void setName(String name) {
        this.assertArgumentNotNull(name, "Name must not be null.");
        this.assertArgumentNotEmpty(name, "Name must not be empty.");
        this.assertArgumentLength(name, 255, "Name exceeded max length of 255.");

        this.name = name;
    }

    public void setLogoUrl(String logoUrl) {
        this.assertArgumentNotNull(logoUrl, "Logo URL must not be null.");
        this.assertArgumentNotEmpty(logoUrl, "Logo URL must not be empty.");
        this.assertArgumentLength(logoUrl, 255, "Logo URL exceeded max length of 255.");
        this.assertArgumentIsValidByRegex(logoUrl, "^https://(.*)", "Logo URL must start with https://.");

        this.logoUrl = logoUrl;
    }
}
