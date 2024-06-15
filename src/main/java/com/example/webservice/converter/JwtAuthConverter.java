package com.example.webservice.converter;





import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    @Value("${jwt.auth.converter.resource-id}")
    private String resourceId;
    @Value("${jwt.auth.converter.principle-attribute}")
    private String principleAttribute;

    public JwtAuthConverter() {
    }

    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> authorities = (Collection)Stream.concat(this.jwtGrantedAuthoritiesConverter.convert(jwt).stream(), this.extractResourceRoles(jwt).stream()).collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt, authorities, this.getPrincipleClaimName(jwt));
    }

    private String getPrincipleClaimName(Jwt jwt) {
        String claimName = "sub";
        if (this.principleAttribute != null) {
            claimName = this.principleAttribute;
        }

        return (String)jwt.getClaim(claimName);
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        if (jwt.getClaim("resource_access") == null) {
            return Set.of();
        } else {
            Map<String, Object> resourceAccess = (Map)jwt.getClaim("resource_access");
            if (resourceAccess.get(this.resourceId) == null) {
                return Set.of();
            } else {
                Map<String, Object> resource = (Map)resourceAccess.get(this.resourceId);
                Collection<String> resourceRoles = (Collection)resource.get("roles");
                return (Collection)resourceRoles.stream().map((role) -> {
                    return new SimpleGrantedAuthority("ROLE_" + role);
                }).collect(Collectors.toSet());
            }
        }
    }
}

