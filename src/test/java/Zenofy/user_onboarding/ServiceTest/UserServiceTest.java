//package Zenofy.user_onboarding.ServiceTest;
//
//import Zenofy.user_onboarding.Model.User;
//import Zenofy.user_onboarding.Model.UserDto;
//import Zenofy.user_onboarding.Service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//
//public class UserServiceTest {
//
//    String testEmail = "test@test.com";
//    String testPassword = "testPassword";
//    String testUserName = "testName";
//
//    UserService userService = new UserService();
//
//    @Test
//    public void testSignupServiceSuccess() throws Exception {
//        UserDto userDto = new UserDto();
//        userDto.setEmail(testEmail);
//        userDto.setPassword(testPassword);
//        userDto.setName(testUserName);
//
//        User user = userService.signUpService(userDto);
//        assert user != null;
//        assert user.getEmail().equals(testEmail);
//        assert user.getUserName().equals(testUserName);
//        assert BCrypt.checkpw(user.getPassword(), testPassword);
//        assert user.getResetToken() == null;
//    }
//}
