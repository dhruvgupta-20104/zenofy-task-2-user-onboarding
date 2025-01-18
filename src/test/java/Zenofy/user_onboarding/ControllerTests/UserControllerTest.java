//package Zenofy.user_onboarding.ControllerTests;
//
//import Zenofy.user_onboarding.Controller.UserController;
//import Zenofy.user_onboarding.Model.User;
//import Zenofy.user_onboarding.Model.UserDto;
//import Zenofy.user_onboarding.Service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//
//import java.util.Objects;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class UserControllerTest {
//
//    String testEmail = "test@test.com";
//    String testPassword = "testPassword";
//    String testUserName = "testName";
//
//    @InjectMocks
//    UserController userController = new UserController();
//
//    @Mock
//    UserService userService = new UserService();
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    @Test
//    void testSignUpSuccess() throws Exception {
//        String testUserDtoJsonRequest = "{" +
//                "\"name\":\"testName\"," +
//                "\"email\":\"test@test.com\"," +
//                "\"password\":\"testPassword\"" +
//                "}";
//
//        String salt = BCrypt.gensalt();
//        User testUser = new User();
//        testUser.setEmail(testEmail);
//        testUser.setPassword(BCrypt.hashpw(testPassword, salt));
//        testUser.setUserName(testUserName);
//
//        when(userService.signUpService(any(UserDto.class))).thenReturn(testUser);
//
//        UserDto deserializedUserDto = objectMapper.readValue(testUserDtoJsonRequest, UserDto.class);
//
//        assert deserializedUserDto.getEmail().equals(testEmail);
//        assert deserializedUserDto.getPassword().equals(testPassword);
//        assert deserializedUserDto.getName().equals(testUserName);
//        assert deserializedUserDto.getClass().equals(UserDto.class);
//
//        ResponseEntity<String> response = userController.signUp(deserializedUserDto);
//
//        assert response.getStatusCode() == HttpStatus.OK;
//        assert Objects.equals(response.getBody(), testUser.toString());
//    }
//}
