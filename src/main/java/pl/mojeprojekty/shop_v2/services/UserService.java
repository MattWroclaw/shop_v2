package pl.mojeprojekty.shop_v2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mojeprojekty.shop_v2.dto.UserDto;
import pl.mojeprojekty.shop_v2.entity.Role;
import pl.mojeprojekty.shop_v2.entity.User;
import pl.mojeprojekty.shop_v2.repositories.UserRepository;
import pl.mojeprojekty.shop_v2.utils.DtoToObjectConverters;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DtoToObjectConverters dtoToObjectConverters;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(long id){
        return   userRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("There is no user with id: "+id));

    }

    @Transactional
    public void createUser(UserDto userDto){
       User newUser = dtoToObjectConverters.userDtoToUserEntity(userDto);
       userRepository.save(newUser);
    }

    public void deleteUser(long id){
        User userToDelete = userRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("No user with id "+id));

        userRepository.delete(userToDelete);
    }

    public void editUser(long id, UserDto userDto){
        User newEditedUser = dtoToObjectConverters.userDtoToUserEntity(userDto);
        newEditedUser.setId(id);
        userRepository.save(newEditedUser);
    }
}
