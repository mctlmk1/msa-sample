//package org.openpaas.paasta.portal.web.security;
//
///**
// * Created by mg on 2016-05-12.
// */
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
//
//    @Autowired
//    CustomUserDetailsService customUserDetailsService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = (String) authentication.getCredentials();
//
//        Collection<? extends GrantedAuthority> authorities = null;
//
//        UserDetails user = null;
//
//        try {
//
//            /*
//             * 사용자 정보를 가져와 세션에 저장하도록 로직이 추가되어야 한다.
//             */
//            UserDetails userDetails = customUserDetailsService.loginByUsernameAndPassword(username, password);
//
//            LOGGER.info("username : " + username + " / password : " + password);
//            LOGGER.info("username : " + user.getUsername() + " / password : " + user.getPassword());
//
//            // matches 를 이용하여 암호를 비교한다.
//            if (!password.equals(user.getPassword())) {
//                throw new BadCredentialsException("암호가 일치하지 않습니다.");
//            }
//
//            authorities = user.getAuthorities();
//        } catch (UsernameNotFoundException e) {
//            LOGGER.info(e.toString());
//            throw new UsernameNotFoundException(e.getMessage());
//        } catch (BadCredentialsException e) {
//            LOGGER.info(e.toString());
//            throw new BadCredentialsException(e.getMessage());
//        } catch (Exception e) {
//            LOGGER.info(e.toString());
//        }
//
//        return new UsernamePasswordAuthenticationToken(user, password, authorities);
//    }
//
//    @Override
//    public boolean supports(Class<?> arg0) {
//        return true;
//    }
//}