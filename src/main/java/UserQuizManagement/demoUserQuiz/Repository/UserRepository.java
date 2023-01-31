package UserQuizManagement.demoUserQuiz.Repository;

import UserQuizManagement.demoUserQuiz.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    public boolean existsByUserEmail(String userEmail);
    public Optional<Users> findByUserEmail(String userEmail);
    public Optional<Users> findByUserPassword(String userPassoword);

}
