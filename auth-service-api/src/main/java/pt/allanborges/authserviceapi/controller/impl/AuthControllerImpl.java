package pt.allanborges.authserviceapi.controller.impl;

import models.requests.AuthenticateRequest;
import models.responses.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pt.allanborges.authserviceapi.controller.AuthController;

@RestController
public class AuthControllerImpl implements AuthController {

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticateRequest request) {
        return ResponseEntity.ok().body(AuthenticationResponse.builder()
                        .type("Bearer")
                        .token("token")
                .build());
    }

}