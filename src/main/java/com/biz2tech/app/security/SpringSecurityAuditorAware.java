package com.biz2tech.app.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.biz2tech.app.config.Constants;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        return SecurityUtils.getCurrentUserLogin().orElse(Constants.SYSTEM_ACCOUNT);
    }
}
