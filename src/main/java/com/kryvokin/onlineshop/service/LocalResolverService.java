package com.kryvokin.onlineshop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Service
public class LocalResolverService {

    private LocaleResolver localeResolver;

    public LocalResolverService(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    public Locale resolveLocal(HttpServletRequest httpServletRequest){
        return localeResolver.resolveLocale(httpServletRequest);
    }
}
