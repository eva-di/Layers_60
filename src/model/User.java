package model;

import utils.MyArrayList;
import utils.MyList;

import java.util.Objects;

public class User {

    private String email;
    private String password;
    private final MyList<Car> userCars;

    private Role role;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = Role.USER;
        userCars = new MyArrayList<>();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "User {" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userCars: " + userCars.size() +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MyList<Car> getUserCars() {
        return userCars;
    }
}
