package com.example.JSON.controller;
import com.example.JSON.DTO.LoginrequestDTO;
import com.example.JSON.DTO.SignUpRequestDTO;
import com.example.JSON.Util.JwtUtils;
import com.example.JSON.common.APIResponse;
import com.example.JSON.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class  LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {

        APIResponse apiResponse = loginService.signUp(signUpRequestDTO);

        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginrequestDTO loginrequestDTO) {

        APIResponse apiResponse = loginService.login(loginrequestDTO);

        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @GetMapping("/privateApi")
//   public ResponseEntity<APIResponse> privateApi() {
    // public ResponseEntity<APIResponse> privateApi() throws Exception {
    public ResponseEntity<APIResponse> privateApi(@RequestHeader (value = "authorization", defaultValue = "notfound")String auth) throws Exception {
        APIResponse apiResponse = new APIResponse();

//        System.out.println(auth);
//        String authorization = " ";
//        jwtUtils.verify(authorization);
        jwtUtils.verify(auth);

        apiResponse.setData("this is private Api");
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

}

