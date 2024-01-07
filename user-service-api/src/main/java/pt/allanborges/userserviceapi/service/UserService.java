package pt.allanborges.userserviceapi.service;

import lombok.RequiredArgsConstructor;
import models.exceptions.ResourceNotFoundException;
import models.responses.UserResponse;
import org.springframework.stereotype.Service;
import pt.allanborges.userserviceapi.mapper.UserMapper;
import pt.allanborges.userserviceapi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse findById(final String id) {
        return userMapper.fromEntity(
                userRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Object not found. Id: " + id + ", Type: " + UserResponse.class.getSimpleName()
                        ))
        );
    }

}