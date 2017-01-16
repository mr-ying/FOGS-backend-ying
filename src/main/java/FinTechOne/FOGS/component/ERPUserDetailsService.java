package FinTechOne.FOGS.component;

import FinTechOne.FOGS.domain.Entitlement;
import FinTechOne.FOGS.repository.EntitlementRepository;
import FinTechOne.FOGS.security.EntitlementUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ERPUserDetailsService implements UserDetailsService {

    private final EntitlementRepository repository;

    @Autowired
    public ERPUserDetailsService(EntitlementRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Entitlement user = this.repository.findByUserId(name);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("ROLE_" + user.getRole().toUpperCase());
        return new EntitlementUserDetails(user, AuthorityUtils.createAuthorityList(roles.toArray(new String[0])));
    }

}
