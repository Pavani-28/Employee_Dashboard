package com.pavaniprojects.june_edition.security;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

//who calls it-AuthController
//what it does - recieve un&password ->ask spring to authenticate->generate jwt(if success)->return jwt
//why is this basically? u need to ask spring to authenticate, how? through authenticationManager
//private because only authService can use this : final- authenticationManager=xyzc; shouldn't happen
//after constructor, your obj looks like below
/* AuthenticationService Object
----------------------------------
authenticationManager
↓
AuthenticationManager Object
----------------------------------*/


@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    //this is called constructor injection, who calls this(not us, springboot)
    //spring sees AuthenticationManager authenticationManager, and thinks do we have one
    // in security config we wrote @bean authmanager..so will be available in spring container->calls constructor
    public AuthenticationService(AuthenticationManager authenticationManager){

        this.authenticationManager=authenticationManager; //its saved instance variable

    }


    public void authenticate(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username,password);
        //authenticate() method is called vis authmanager ,, it accepts onlt Authentication objs, not strings
        Authentication result = authenticationManager.authenticate(authentication);//execution goes to securityconfig(spring security)
        //but which authenticate() is called?where is implementation of AuthenticationManager interface?
        //spring injects implementation called ProviderManager
        //though we write authenticationManager.authenticate(authentication) , executed is ProviderManager.authenticate(authentication);
        //execution jumps to loadUserByusername(String UN);
        System.out.println(result);
    }
}
