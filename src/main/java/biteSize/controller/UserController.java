package biteSize.controller;


import biteSize.entity.Task;
import biteSize.entity.User;
import biteSize.persistence.GenericDao;

import java.util.List;

/*
    A class to modify or retrieve the user's information
    via the use of DAOs
    @author IanDeLoach
 */
public class UserController {

    public User getUserFromEmail(String email) {

        GenericDao userDao = new GenericDao(User.class);
        List<User> foundUsers = userDao.getPropertyEqual("email", email);

        if (foundUsers.isEmpty()) {
            return null;
        }

        return foundUsers.get(0);

    }

    public User getUserFromId(int id) {

        GenericDao userDao = new GenericDao(User.class);
        User foundUser = (User)userDao.getById(id);

        return foundUser;

    }
}
