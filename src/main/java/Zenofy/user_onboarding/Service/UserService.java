package Zenofy.user_onboarding.Service;

import Zenofy.user_onboarding.Model.*;
import Zenofy.user_onboarding.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signUpService(UserDto userDto) throws Exception {
        if (userRepository.findUserByEmail( userDto.getEmail() ) != null){
            throw new Exception("This Email is already registered.");
        }

        User user = User.builder()
                        .email(userDto.getEmail())
                        .password(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()))
                        .userName(userDto.getName())
                        .build();
        userRepository.save(user);

        return user;
    }

    public String authenticateUserService(UserSignInDto userSignInDto) throws Exception {
        User user = userRepository.findUserByEmail(userSignInDto.getEmail());
        if (user == null){
            throw new Exception("No such email registered");
        }
        if(BCrypt.checkpw(userSignInDto.getPassword(), user.getPassword())){
            return user.getUserName();
        }
        else{
            return null;
        }
    }

    public String generateResetPasswordToken(UserResetPasswordRequestDto userResetPasswordRequestDto) throws Exception {
        User user = userRepository.findUserByEmail(userResetPasswordRequestDto.getEmail());
        if (user == null){
            throw new Exception("No such email registered");
        }
        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);
        userRepository.save(user);
        return resetToken;
    }

    public String changePassword(UserNewPasswordDto userNewPasswordDto) throws Exception {
        if(userNewPasswordDto.getResetPasswordToken() == null){
            throw new Exception("Reset password token is not valid");
        }
        User user = userRepository.findUserByResetToken(userNewPasswordDto.getResetPasswordToken());
        if (user == null){
            return null;
        }
        user.setResetToken(null);
        user.setPassword(BCrypt.hashpw(userNewPasswordDto.getNewPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        return user.getUserName();
    }
}
