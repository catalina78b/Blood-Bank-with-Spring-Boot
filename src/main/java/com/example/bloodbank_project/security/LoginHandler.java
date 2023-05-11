package com.example.bloodbank_project.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
@Component
public class LoginHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String redirectUrl = null;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            String role = grantedAuthority.getAuthority();
            if (role.equals("ROLE_ADMIN")) {
                redirectUrl = "/admin/home";
                break;
            } else if (role.equals("ROLE_DONOR")) {
                redirectUrl = "/donor/home";
                break;
            } else if (role.equals("ROLE_DOCTOR")) {
                redirectUrl = "/doctor/home";
                break;
            }
        }

        if (redirectUrl != null) {
            response.setStatus(HttpStatus.OK.value());
            objectMapper.writeValue(response.getWriter(), redirectUrl);
        } else {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }
}
