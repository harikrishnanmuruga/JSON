package com.example.JSON.service;

import com.example.JSON.DTO.LoginrequestDTO;
import com.example.JSON.DTO.SignUpRequestDTO;
import com.example.JSON.Util.JwtUtils;
import com.example.JSON.common.APIResponse;
import com.example.JSON.entity.User;
import com.example.JSON.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service

public class LoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;

    public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) {
        APIResponse apiResponse = new APIResponse();


        //validation

        //dto to entity
        User userEntity = new User();
        userEntity.setName(signUpRequestDTO.getName());
        userEntity.setEmailId(signUpRequestDTO.getEmailId());
        userEntity.setActive(Boolean.TRUE);
        userEntity.setGender(signUpRequestDTO.getGender());
        userEntity.setPhoneNumber(signUpRequestDTO.getPhoneNumber());
        userEntity.setPassword(signUpRequestDTO.getPassword());

        //store entity
        userEntity = userRepository.save(userEntity);

        // generate jwt
        String token = jwtUtils.generateJwt(userEntity);
        Map<String, Object> data = new HashMap<>();
        data.put("access token", token);
        apiResponse.setData(data);

        //apiResponse.setData(userEntity);
        // apiResponse.setData("its working");

        //return
        return apiResponse;
    }

    public APIResponse login(LoginrequestDTO loginrequestDTO) {

        APIResponse apiResponse = new APIResponse();

        // validation

        // verify user exist with given email and password
        User user = userRepository.findOneByEmailIdIgnoreCaseAndPassword(loginrequestDTO.getEmailId(), loginrequestDTO.getPassword());

        if (user == null) {
            apiResponse.setData("User login failed");
        }

        // generate jwt
        String token = jwtUtils.generateJwt(user);
        Map<String, Object> data = new HashMap<>();
        data.put("access token", token);
        apiResponse.setData(data);

        // return apiResponse;
        return apiResponse;
    }

}

