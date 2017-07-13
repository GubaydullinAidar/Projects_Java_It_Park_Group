package ru.itpark.converters;

import ru.itpark.dto.UserDto;
import ru.itpark.models.User;

public class Converter {
    private static UserDto convert(User model) {
        return new UserDto(model.getUserId(), model.getFirstName(), model.getLastName(), model.getEmail(), model.getPhone());
    }

}
