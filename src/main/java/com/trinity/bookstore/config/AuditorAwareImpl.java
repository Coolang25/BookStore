package com.trinity.bookstore.config;

import com.trinity.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.ofNullable(email);
    }
}
