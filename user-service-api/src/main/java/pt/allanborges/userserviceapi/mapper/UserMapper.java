package pt.allanborges.userserviceapi.mapper;

import models.responses.UserResponse;
import org.mapstruct.Mapper;
import pt.allanborges.userserviceapi.entity.User;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface UserMapper {

    UserResponse fromEntity(final User entity);

}