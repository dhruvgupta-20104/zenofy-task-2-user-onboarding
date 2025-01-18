package Zenofy.user_onboarding.ControllerTests;

import Zenofy.user_onboarding.Controller.UserController;
import Zenofy.user_onboarding.Model.User;
import Zenofy.user_onboarding.Model.UserDto;
import Zenofy.user_onboarding.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.internal.matchers.text.ValuePrinter.print;

public class UserControllerTest {

    String testEmail = "test@test.com";
    String testPassword = "testPassword";
    String testUserName = "testName";

    UserController userController = new UserController();

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSignUpSuccess() throws Exception {
        String testUserDtoJson = "{" +
                "\"name\":\"testName\"," +
                "\"email\":\"test@test.com\"," +
                "\"password\":\"testPassword\"" +
                "}";

        String salt = BCrypt.gensalt();
        User testUser = new User();
        testUser.setEmail(testEmail);
        testUser.setPassword(BCrypt.hashpw(testPassword, salt));
        testUser.setUserName(testUserName);

        UserService userService = mock(UserService.class);
        when(userService.signUpService(any( UserDto.class ))).thenReturn(testUser);

        UserDto deserializedUserDto = objectMapper.readValue(testUserDtoJson, UserDto.class);

        ResponseEntity<String> response = userController.signUp(deserializedUserDto);
        assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
    }
}
