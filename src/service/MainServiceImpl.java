package service;

import model.Car;
import model.User;
import repository.CarRepository;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import utils.MyList;
import utils.UserValidation;

public class MainServiceImpl implements MainService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    private User activeUser;

    public MainServiceImpl(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String email, String password) {

        /*
        1. Провалидировать входящие данные (email, password). если не пройдена - возвращаю null
        2. Проверить уникальность email (что пользователя с таким email еще нет)
        3. Создаю нового пользователя и сохраняю его в хранилище данных
        4. Возвращаю созданного пользователя в слой view
         */

        if (!UserValidation.isEmailValid(email)) {
            System.out.println("Email не прошел проверку!");
        return null;
        }

        if (!UserValidation.isPasswordValid(password)) {
            System.out.println("Password не прошел проверку!");
            return null;
        }
        if (userRepository.isEmailExist(email)) {
            System.out.println("Пользователь с таким email  уже зарегистрирован!");
            return null;
        }

        User user = userRepository.addUser(email, password);
        return user;
    }

    @Override
    public boolean loginUser(String email, String password) {
        /*
        Получить из хранилища пользователя с таким email
        Если такого нет - закончить метод, вернуть false

        Проверить совпадает ли пароль у пользователя из БД с паролем который пришел в метод

        Если совпадают, пользователь залогинился
         */

        User user = userRepository.getUserByEmail(email);

        if(user == null) return false;

        if (user.getPassword().equals(password)) {
            activeUser = user;
            return true;
        }

        return false;
    }

    @Override
    public void logout() {
        activeUser = null;

    }

    @Override
    public boolean takeCar(int carId) {
        return false;
    }

    @Override
    public Car addCar(String model, int year, double price) {
        return null;
    }

    @Override
    public MyList<Car> getAllCars() {
        return null;
    }

    @Override
    public MyList<Car> getCarByModel(String model) {
        return null;
    }

    @Override
    public MyList<Car> getFreeCars() {
        return null;
    }

    @Override
    public void deleteCar(int carId) {

    }

    public User getActiveUser() {
        return activeUser;
    }
}
