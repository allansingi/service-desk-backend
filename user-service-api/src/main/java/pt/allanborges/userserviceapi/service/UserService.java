package pt.allanborges.userserviceapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.allanborges.userserviceapi.entity.User;
import pt.allanborges.userserviceapi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(final String id) {
        return userRepository.findById(id).orElse(null);
    }

}