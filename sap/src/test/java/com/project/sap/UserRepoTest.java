package com.project.sap;

        import static org.assertj.core.api.Assertions.assertThat;

        import com.project.sap.models.User;
        import com.project.sap.repositories.UserRepository;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
        import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
        import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
        import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("gosho@gmail.com");
        user.setPassword(encryptPassword("vancho"));
        user.setFirstName("Ivan");
        user.setLastName("Teri");
        user.setRole("Admin");
        user.setEnabled(true);
        User savedUser = repo.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }

    private String encryptPassword(String pass){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(pass);
        return encodedPassword;
    }

}