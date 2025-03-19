package repository;

import model.User;

public interface UserRepository {

    // CRUD

    User addUser(String email, String password);

    //READ
    boolean isEmailExist(String email);

    User getUserByEmail(String email);

    // UPDATE

    boolean updatePassword(String email, String newPassword);



}
