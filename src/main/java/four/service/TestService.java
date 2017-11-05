package four.service;

import four.repository.UserRepository;
import four.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestService {

    private UserRepository userRepository;

    private UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUserAndReturnIt(User user) {
        userRepository.save(user);
        user = userRepository.findOne(user.getId());
        return user;
    }
}
