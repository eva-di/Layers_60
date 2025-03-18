import repository.CarRepository;
import repository.CarRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.MainService;
import service.MainServiceImpl;
import view.Menu;

public class CarApplication {

    public static void main(String[] args) {

        CarRepository carRepository = new CarRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        MainService service = new MainServiceImpl(carRepository, userRepository);
        Menu menu = new Menu(service);

        menu.start();
    }
}
