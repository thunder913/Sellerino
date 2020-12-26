package com.project.sap;

        import static org.assertj.core.api.Assertions.assertThat;

        import com.project.sap.models.Dto.UserDto;
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
public class UserDtoRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setEmail("gosho@gmail.com");
        userDto.setPassword(encryptPassword("vancho"));
        userDto.setFirstName("Ivan");
        userDto.setLastName("Teri");
        userDto.setRole("Admin");
        userDto.setEnabled(true);
        //UserDto savedUserDto = repo.save(userDto);
        //UserDto existUserDto = entityManager.find(UserDto.class, savedUserDto.getId());

        //assertThat(userDto.getEmail()).isEqualTo(existUserDto.getEmail());

    }

    private String encryptPassword(String pass){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(pass);
        return encodedPassword;
    }

}