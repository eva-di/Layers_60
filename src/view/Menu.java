package view;

import model.Car;
import model.Role;
import model.User;
import service.MainService;
import utils.MyList;

import java.util.Scanner;

public class Menu {

    private final MainService service;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(MainService service) {
        this.service = service;
    }

    public void start() {
        showMenu();
    }

    private void showMenu() {
        while (true) {
            System.out.println("Добро пожаловать в меню: ");
            System.out.println("1. Меню автомобилей ");
            System.out.println("2. Меню пользователя ");
            System.out.println("3. Меню администратора ");
            System.out.println("0. Выход ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("До свидания!");
                // Завершение работы приложения
                System.exit(0);
            }
            showSubMenu(choice);

        }
    }

    private void showSubMenu(int choice) {
        switch (choice) {
            case 1:
                // Todo show car menu
                break;
            case 2:
                // Todo show user menu
                showUserMenu();
                break;
            case 3:
                User active = service.getActiveUser();
                if(active == null) {
                    System.out.println("Авторизуйтесь в системе");
                    waitRead();
                    break;
                }
                if (active.getRole() != Role.ADMIN) {
                    System.out.println("Доступ только ля администраторов");
                    waitRead();
                    break;
                }
                // Todo show admin menu
                System.out.println("Здесь будет метод показывающий админ меню");
                break;
            default:
                System.out.println("Сделайте корректный выбор");
                waitRead();
        }
    }

    private void showUserMenu() {
        while (true) {
            System.out.println("Меню пользователя: ");
            System.out.println("1. Login");
            System.out.println("2. Регистрация нового пользователя");
            System.out.println("3. Logout");
            System.out.println("0. Вернуться в предыдущее меню");

            System.out.println("\nВыберите номер пункта меню");
            int input = scanner.nextInt();
            scanner.nextLine();

            // Прерывваю текущий цикл и вываливаюсь в сабменю и возвращаюсь в Добро пожаловать
            if (input == 0) break;

            handleUserMenuInput(input);

        }
    }

        private void handleUserMenuInput(int input) {
            switch (input) {
                case 1:
                // Авторизация
                    /*
                    1. Запросить у пользователя email b пароль
                    2. Передать полученные данные СЕРВИСНЫЙ слой
                    3. Получить ответ от сервисного слоя - прошел и успешно логин
                    4. Сообщить результат пользователю
                     */
                    break;
                case 2:
                    // Регистрация
                    /*
                    1. Запросить у пользователя email b пароль
                    2. Передать полученные данные СЕРВИСНЫЙ слой
                    3. Получить ответ от сервисного слоя -
                    4. Сообщить результат пользователю
                     */
                    System.out.println("Регистрация нового пользователя");
                    System.out.println("Введите email: ");
                    String email = scanner.nextLine();

                    System.out.println("Введите пароль: ");
                    String password = scanner.nextLine();

                    User user = service.registerUser(email, password);

                    if (user == null) {
                        System.out.println("Регистрация провалена");
                    } else {
                        System.out.println("Вы успешно зарегистрировались в системе!");
                    }
                    waitRead();
                    break;
                case 3:
                    // logout
                    // Есть ли пользователь, который сейчас авторизован
                    if(service.getActiveUser() == null) {
                        System.out.println("Сейчас в системе нет авторизрванных пользователей");
                        waitRead();
                        break;
                    }
                    service.logout();
                    System.out.println("Вы вышли из системы");
                    waitRead();
                    break;
            }
    }

    private void waitRead() {
        System.out.println("\nДля продолжения нажмите Enter...");
        scanner.nextLine();
    }

    private void showCarList(MyList<Car> cars) {
        for (Car car : cars) {
            System.out.printf("%d. %s (%d г. выпуска). Цена: 2.%f\n", +
                    car.getId(), car.getModel(), car.getYear(), car.getPrice());
        }
    }


}
