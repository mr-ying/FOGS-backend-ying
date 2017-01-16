package FinTechOne.FOGS.auditing;

import FinTechOne.FOGS.security.EntitlementUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//public class ERPSecurityAuditorAware implements AuditorAware<Entitlement> {
//    public Entitlement getCurrentAuditor() {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return null;
//        }
//
//        return ((EntitlementUserDetails) authentication.getPrincipal()).getUser();
//    }
//}

//public class ERPSecurityAuditorAware implements AuditorAware<Long> {
//    public Long getCurrentAuditor() {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return 0L;
//        }
//
//        return ((EntitlementUserDetails) authentication.getPrincipal()).getUser();
//    }
//}

public class ERPSecurityAuditorAware implements AuditorAware<String> {
    public String getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return "";
        }

        return ((EntitlementUserDetails) authentication.getPrincipal()).getUser();
    }
}
