package dev.huachin.java.spring.api_concert_tickets.service.impl;

import dev.huachin.java.spring.api_concert_tickets.client.UserApiClient;
import dev.huachin.java.spring.api_concert_tickets.dto.response.UserResponseDto;
import dev.huachin.java.spring.api_concert_tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserApiClient userApiClient;

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userApiClient.getUsers()
            .stream()
            .map(user -> new UserResponseDto(
                user.id(),
                user.username(),
                user.email(),
                user.firstName(),
                user.lastName(),
                user.phone()
            ))
            .toList();
    }
}
