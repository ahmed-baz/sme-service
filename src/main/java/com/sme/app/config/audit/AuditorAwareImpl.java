package com.sme.app.config.audit;

import com.sme.app.interceptor.UserContext;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Long id = UserContext.getUser() != null ? UserContext.getUser().getId() : 0;
        return Optional.of(id);
    }
}
