package com.example.Blog.Service;

import com.example.Blog.Entity.Role;
import com.example.Blog.Entity.User;
import com.example.Blog.Enum.ResponseStatus;
import com.example.Blog.Enum.RoleEnum;
import com.example.Blog.Enum.Status;
import com.example.Blog.Exception.DataResult;
import com.example.Blog.Exception.DataResultList;
import com.example.Blog.Exception.ResponseResult;
import com.example.Blog.Model.*;
import com.example.Blog.Repository.RoleRepository;
import com.example.Blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public DataResultList<UserData> getAllByRole(String role){

        DataResultList<UserData> userDataResultList = new DataResultList<>();

        userDataResultList.setResponseStatus(ResponseStatus.SUCCESS);
        userDataResultList.setStatus(ResponseStatus.SUCCESS.getStatusCode());

        try{
            Optional<List<User>> users = userRepository.findAllByRole(role);
            if(!users.isPresent()){
                userDataResultList.setResponseStatus(ResponseStatus.NO_DATA);
                userDataResultList.setStatus(ResponseStatus.NO_DATA.getStatusCode());
                return userDataResultList;
            }
            userDataResultList.setData(getAll(users.get()));

        } catch (Exception e){
            userDataResultList.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            userDataResultList.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
        }
        return userDataResultList;
    }

    public List<User> getAllUsers()  {
        return userRepository.findAll();
    }

    public List<UserData> getAll(List<User> users)  {

        List<UserData> userDataList = new ArrayList<>();


        for ( User user: users){

            UserData  userData = new UserData(user.getId(), user.getName(), user.getSurname(), user.getRole().getName(), user.getStatus());

            userDataList.add(userData);
        }
        
        return userDataList;
    }

    public DataResult<UserData> getById(Long userId){

        DataResult dataResult = new DataResult();

        dataResult.setResponseStatus(ResponseStatus.NOT_FOUND);
        dataResult.setStatus(ResponseStatus.NOT_FOUND.getStatusCode());

        Optional<User> optionalUser =userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            return dataResult;
        }
        try {
            User user = optionalUser.get();

            UserData  userData = new UserData(user.getId(), user.getName(), user.getSurname(), user.getRole().getName(), user.getStatus());

            dataResult.setResponseStatus(ResponseStatus.OK);
            dataResult.setStatus(ResponseStatus.OK.getStatusCode());
            dataResult.setData(userData);
        }catch (Exception e){
            dataResult.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            dataResult.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
        }

        return dataResult;

    }


    public ResponseResult registerUser(RegisterUser registerUser){


        ResponseResult result = new ResponseResult();

        result.setResponseStatus(ResponseStatus.CONFLICT);
        result.setStatus(ResponseStatus.CONFLICT.getStatusCode());

        Optional<User> optionalUser = userRepository.findByEmail(registerUser.getEmail());

        if (optionalUser.isPresent()) {
            result.setMessage("This email exists!");
            return result;
        }

        try {
            Optional<Role> optionalRole = roleRepository.findById(RoleEnum.USER.getRoleId());
            Role role = optionalRole.get();

            User user = registerUser.getUser();
            user.setRole(role);
            user.setStatus(Status.ACTIVE);
            user.setPassword(registerUser.getPassword());

            userRepository.save(user);


            result.setResponseStatus(ResponseStatus.SUCCESS);
            result.setStatus(ResponseStatus.SUCCESS.getStatusCode());
            result.setMessage(ResponseStatus.SUCCESS.getMsg());
        }catch (Exception e){
            result.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            result.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            result.setMessage(ResponseStatus.INTERNAL_SERVER_ERROR.getMsg());
            System.err.println(e.getMessage());
        }


        return result;
    }

    public ResponseResult createUser(CreateUser createUser){


        ResponseResult result = new ResponseResult();

        result.setResponseStatus(ResponseStatus.CONFLICT);
        result.setStatus(ResponseStatus.CONFLICT.getStatusCode());

        Optional<User> optionalUser = userRepository.findByEmail(createUser.getEmail());

        if (optionalUser.isPresent()) {
            result.setMessage("This email exists!");
            return result;
        }
        Optional<Role> optionalRole = roleRepository.findById(createUser.getRoleId());
        Role role = optionalRole.get();
        try {


            User user = createUser.getUser();
            user.setRole(role);
            user.setStatus(Status.ACTIVE);
            user.setCreatedAt(LocalDateTime.now());
            user.setPassword(createUser.getPassword());

            userRepository.save(user);

            result.setResponseStatus(ResponseStatus.SUCCESS);
            result.setStatus(ResponseStatus.SUCCESS.getStatusCode());
            result.setMessage(ResponseStatus.SUCCESS.getMsg());
        }catch (Exception e){
            result.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            result.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            result.setMessage(ResponseStatus.INTERNAL_SERVER_ERROR.getMsg());
            System.err.println(e.getMessage());
        }

        return result;
    }

    public List<User> getUsersByStatus(Status status){
        return userRepository.getByStatus(status);
    }

    public ResponseResult deleteById(Long id){

        ResponseResult result = new ResponseResult();

        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            result.setMessage("This user does not exist!!!");

            return result;
        }

        try {
            User user = optionalUser.get();
            user.setStatus(Status.DELETED);
            user.setDeletetAt(LocalDateTime.now());

            userRepository.save(user);

            result.setResponseStatus(ResponseStatus.SUCCESS);
            result.setStatus(ResponseStatus.SUCCESS.getStatusCode());
            result.setMessage(ResponseStatus.SUCCESS.getMsg());
        }catch (Exception e){
            result.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            result.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            result.setMessage(ResponseStatus.INTERNAL_SERVER_ERROR.getMsg());
            System.err.println(e.getMessage());
        }


        return result;
    }

    public ResponseResult updateProfile(UpdateUser updateUser, Long id) {
        ResponseResult result = new ResponseResult();

        result.setResponseStatus(ResponseStatus.CONFLICT);

        try {

            User user =  userRepository.getOne(id);
            user.setName(updateUser.getName());
            user.setSurname(updateUser.getSurname());
            user.setEmail(updateUser.getEmail());


            userRepository.save(user);

            result.setResponseStatus(ResponseStatus.SUCCESS);
            result.setStatus(ResponseStatus.SUCCESS.getStatusCode());
            result.setMessage(ResponseStatus.SUCCESS.getMsg());

        } catch (Exception e) {
            result.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            result.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            result.setMessage(e.getLocalizedMessage());
        }

        return result;
    }

    public ResponseResult updatePassword(UpdatePassword updatePassword){
        ResponseResult result = new ResponseResult();

        result.setResponseStatus(ResponseStatus.CONFLICT);

        Optional<User> optionalUser = userRepository.findById(updatePassword.getId());

        try {
            User user = optionalUser.get();

            user.setPassword(updatePassword.getPassword());

            userRepository.save(user);

            result.setResponseStatus(ResponseStatus.SUCCESS);
            result.setStatus(ResponseStatus.SUCCESS.getStatusCode());
            result.setMessage(ResponseStatus.SUCCESS.getMsg());
        }catch (Exception e) {
            result.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            result.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            result.setMessage(e.getLocalizedMessage());
        }

        return result;
    }



//    public void delete(User user){
//        userRepository.save(user);
//    }

}
