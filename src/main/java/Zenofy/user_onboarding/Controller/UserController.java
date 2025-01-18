package Zenofy.user_onboarding.Controller;

import Zenofy.user_onboarding.Model.*;
import Zenofy.user_onboarding.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
        try {
            User user = userService.signUpService(userDto);
            return new ResponseEntity<>("Sign up successful " + user.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Sign up failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody UserSignInDto userSignInDto) {
        try {
            String userName = userService.authenticateUserService(userSignInDto);
            if(userName != null) {
                return new ResponseEntity<>("Sign in successful. Hello " + userName, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Sign in failed.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Sign in failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reset-password-request")
    public ResponseEntity<String> resetPassword(@RequestBody UserResetPasswordRequestDto userResetPasswordRequestDto) {
        try {
            String resetPasswordToken = userService.generateResetPasswordToken(userResetPasswordRequestDto);
            return new ResponseEntity<>("Do not share this with anyone. Your reset password token is " + resetPasswordToken, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/new-password")
    public ResponseEntity<String> newPassword(@RequestBody UserNewPasswordDto userNewPasswordDto) {
        try{
            String userName = userService.changePassword(userNewPasswordDto);
            if(userName != null) {
                return new ResponseEntity<>("Hey " + userName + ", Your password changed successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Reset Password Token validation failed.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
