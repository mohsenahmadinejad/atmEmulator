package com.mohsen.atmservice.controller;



import com.mohsen.atmservice.controller.dto.ReqAuthMethodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping()
public class AuthenticationController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Token token;

    @Value("${atm-emulator.bank-service.server}")
    private String bankServerUri;
   @Value("${atm-emulator.bank-service.server.preferred-authentication-method}")
    private String preferredAuthenticationMethodUri;

    @PostMapping("/signIn")
    public String generateToken(@RequestBody AuthRequest authDto) throws Exception {
        String jwtToken=restTemplate.postForObject(bankServerUri +"/signIn",authDto,String.class);
        token.setToken(jwtToken);
        return jwtToken;
    }

    @PutMapping("/preferred-authentication-method")
    public ResponseEntity setPreferredAuthenticationMethod(@RequestBody ReqAuthMethodDto reqAuthMethodDto) throws Exception {

        restTemplate.put(bankServerUri +preferredAuthenticationMethodUri,reqAuthMethodDto,ResponseEntity.class);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}