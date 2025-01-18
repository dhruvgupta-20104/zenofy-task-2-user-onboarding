package Zenofy.user_onboarding.Repository;

import Zenofy.user_onboarding.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

    User findUserByResetToken(String resetToken);

}


