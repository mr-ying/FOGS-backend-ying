package FinTechOne.FOGS.security;

import FinTechOne.FOGS.domain.Entitlement;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class EntitlementUserDetails  implements UserDetails {

    private final Entitlement user;
    private final Collection<? extends GrantedAuthority> grantedAuthorities;

/// Constructors begin
    public EntitlementUserDetails(Entitlement user) {
        this(user, new ArrayList<GrantedAuthority>());
    }

    public EntitlementUserDetails(Entitlement user, Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.user = user;
        this.grantedAuthorities = grantedAuthorities;
    }
/// Constructors end

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    public Entitlement getUser() {
//        return user;
//    }
//    public Long getUser() {
//        return user.getId();
//    }
    public String getUser() {
        return user.getUserId();
    }
}
