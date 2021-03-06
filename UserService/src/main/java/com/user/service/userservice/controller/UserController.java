package com.user.service.userservice.controller;

import com.user.service.userservice.dto.UserDto;
import com.user.service.userservice.model.CreateUserRequestModel;
import com.user.service.userservice.model.CreateUserResponseModel;
import com.user.service.userservice.service.UsersService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersService usersService;
    private final Environment environment;

    @Autowired
    public UserController(UsersService usersService, Environment environment) {
        this.usersService = usersService;
        this.environment = environment;
    }


    @GetMapping("/check")
    public String checkAccount(){
        return "Working user on port " + environment.getProperty("local.server.port") + " with toker " + environment.getProperty("token.secret");
    }

    /*consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}*/
    @PostMapping()
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetail){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(userDetail, UserDto.class);
        UserDto createdUser = usersService.createUser(userDto);
        CreateUserResponseModel responseModel = modelMapper.map(createdUser,CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }
}
