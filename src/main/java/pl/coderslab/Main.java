package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;


public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = new User();
       user.setUserName("Kowalski");
      user.setEmail("kowalski@gmail.com");
       user.setPassword("DobreHaslo!");

        user.setUserName("Skowron");
        user.setEmail("skowron@gmail.com");
        user.setPassword("SuperHaslo!");

        userDao.create(user);
        userDao.read(1);
        userDao.update(userDao.read(1));
        userDao.delete(2);
    }
}