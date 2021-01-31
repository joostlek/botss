package hu.indicium.battle.management.domain.association;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class AssociationId implements Serializable {
    @Column(name = "slug")
    private String slug;

    public static AssociationId fromSlug(String slug) {
        return new AssociationId(slug);
    }

    private AssociationId(String slug) {
        this.slug = slug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssociationId that = (AssociationId) o;
        return Objects.equals(slug, that.slug);
    }
}
