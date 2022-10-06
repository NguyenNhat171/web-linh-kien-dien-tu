package com.example.electronicshop.service;

import com.example.electronicshop.communication.request.LoginRequest;
import com.example.electronicshop.communication.response.LoginResponese;
import com.example.electronicshop.map.UserMap;
import com.example.electronicshop.models.ResponseObject;
import com.example.electronicshop.security.jwt.JwtUtils;
import com.example.electronicshop.security.userdetail.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {
    private AuthenticationManager authenticationManager;
    private final UserMap userMapper;
    private final JwtUtils jwtUtils;
  /*  public ResponseEntity<ResponseObject> login(LoginRequest req) {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
            String accesstoken = jwtUtils.generateTokenFromUserId(user.getUser());
            LoginResponese res = userMapper.toLoginRes(user.getUser());
            res.setAccessToken(access_token);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("true", "Log in successfully ", res)
            );

        } catch (BadCredentialsException ex) {
//            ex.printStackTrace();
            throw new BadCredentialsException(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<ResponseObject> register(RegisterReq req) {
        if (userRepository.existsByEmail(req.getEmail()))
            throw new AppException(HttpStatus.CONFLICT.value(), "Email already exists");
        req.setPassword(passwordEncoder.encode(req.getPassword()));
        User user = userMapper.toUser(req);
        if (user != null) {
            try {
                sendVerifyMail(user);
            } catch (Exception e) {
                throw new AppException(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject(true, "Register successfully ", "")
        );
    }*/
}
