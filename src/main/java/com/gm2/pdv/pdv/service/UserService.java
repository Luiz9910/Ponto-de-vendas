package com.gm2.pdv.pdv.service;

import com.gm2.pdv.pdv.dto.UserDTO;
import com.gm2.pdv.pdv.exceptions.NotFoundUserException;
import com.gm2.pdv.pdv.model.User;
import com.gm2.pdv.pdv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUser() {
        return userRepository.findAll().stream().map(user -> {
            return new UserDTO(user.getId(), user.getName(), user.isEnabled());
        }).collect(Collectors.toList());
    }

    public UserDTO findById(long id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NotFoundUserException("Usuário não encontrado");
        }

        User user = optional.get();
        return new UserDTO(user.getId(), user.getName(), user.isEnabled());
    }

    public UserDTO save(User user) {
        userRepository.save(user);
        return new UserDTO(user.getId(), user.getName(), user.isEnabled());
    }

    public UserDTO update(User user) {
        Optional<User> userToEdit = userRepository.findById(user.getId());
        if (!userToEdit.isPresent()) {
            throw new NotFoundUserException("Usuário não encontrado");
        }

        userRepository.save(user);
        return new UserDTO(user.getId(), user.getName(), user.isEnabled());
    }


    public void deleteById(long id) {
        Optional<User> userToEdit = userRepository.findById(id);
        if (!userToEdit.isPresent()) {
            throw new NotFoundUserException("Usuário não encontrado");
        }

        userRepository.deleteById(id);
    }
}
