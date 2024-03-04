package pt.allanborges.authserviceapi.secutiry.dtos;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import models.requests.AuthenticateRequest;
import models.responses.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import pt.allanborges.authserviceapi.utils.JWTUtils;

@Log4j2
@RequiredArgsConstructor
public class JWTAuthenticationImpl {

    private final JWTUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(final AuthenticateRequest request) {
        try {
            log.info("Authentication user: {}", request.email());
            final var authResult = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
            return buildAuthenticationResponse((UserDetailsDTO) authResult.getPrincipal());
        } catch (BadCredentialsException ex) {
            log.error("Error on authenticate user: {}", request.email());
            throw new BadCredentialsException("Email or password invalid");
        }
    }

    protected AuthenticationResponse buildAuthenticationResponse(final UserDetailsDTO userDetailsDTO) {
        log.info("Successful authenticated user: {}", userDetailsDTO.getUsername());
        final var token = jwtUtils.generateToken(userDetailsDTO);
        return AuthenticationResponse.builder()
                .type("JWT")
                .token("Bearer " + token)
                .build();
    }

}