package Zenofy.user_onboarding.Model;

import lombok.Data;

@Data
public class UserNewPasswordDto {
    private String newPassword;
    private String resetPasswordToken;
}
