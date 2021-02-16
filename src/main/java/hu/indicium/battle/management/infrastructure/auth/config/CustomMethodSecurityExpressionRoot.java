package hu.indicium.battle.management.infrastructure.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Slf4j
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    Object filterObject;

    Object returnObject;

    /**
     * Creates a new instance
     *
     * @param authentication the {@link Authentication} to use. Cannot be null.
     */
    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    public boolean hasPermission(String permission) {
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            if (grantedAuthority.getAuthority().equals("SCOPE_botss-management/" + permission)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTeamCaptain(String teamId) {
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("SCOPE_botss-management/manage-team/" + teamId));
    }

    public boolean isPartOfTeam(String teamId) {
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("SCOPE_botss-management/view-team/" + teamId));
    }

    public boolean hasPaid() {
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("SCOPE_botss-management/paid"));
    }

    public boolean userIdEquals(String userId) {
        return authentication.getName().equals(userId);
    }
}
