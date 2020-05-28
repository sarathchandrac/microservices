package com.training.mobile.service;

import com.training.mobile.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class UserDaoService {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }
    public User saveUser(User user){
        int userId = users.indexOf(user);
        if (userId != -1) users.set(userId, user);
        else users.add(user);
        return user;
    }

    public User getUser(int userId){
        Optional<User> user = getUsers().stream().filter(u->u.getId()==userId).findFirst();
        if(!user.isPresent()){
            throw new RuntimeException("User not found with given user id : " + userId);
        }
        return user.get();
    }


    public int getUniqueId(){
        return 4;
        // .min(Comparator.comparing(Person::getAge))
        //return users.stream().max(Comparator.comparing(User::getId)).orElse(null).getId();
        //return users.stream().mapToInt(User::getId).findFirst().isPresent().getAsInt();
//                .max(Comparator.comparing(Integer::valueOf));
    }




    @PostConstruct
    public void loadUsers(){
        users.add(new User(1, "James", new Date()));
        users.add(new User(2, "Rhodes", new Date()));
        users.add(new User(3, "Cronje", new Date()));
        users.add(new User(4, "Kallis", new Date()));
    }
}
