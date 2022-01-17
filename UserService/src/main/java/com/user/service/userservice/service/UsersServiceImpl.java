package com.user.service.userservice.service;

import com.user.service.userservice.data.UserEntity;
import com.user.service.userservice.dto.UserDto;
import com.user.service.userservice.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    UsersRepository repository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        //ENCRYPT PASSWORD
        userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        repository.save(userEntity);

        UserDto returnValue = modelMapper.map(userEntity,UserDto.class);
        return returnValue;
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = repository.findByEmail(email);
        if (userEntity == null) throw  new UsernameNotFoundException(email);
        return new ModelMapper().map(userEntity,UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByEmail(username);
        if (userEntity == null) throw  new UsernameNotFoundException(username);
        return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),true,true,true,true,new ArrayList<>());
    }
}
