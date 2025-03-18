package repository;


import model.Car;
import utils.MyList;

/*
Набор CRUD операций:
Create - создание (добавление новых данных) - новые объекты сущностей в DATA
Read - чтение (получение данных из хранилища
Update - обновление 0 измененИе существующих данных
Delete - удаление объекта

 */
public interface CarRepository {

    // CREATE - add

    Car addCar(String model, int year, double price);

    // READ - Метод получения машинок

    // Получить список всех сущностей (всех машин)
    MyList<Car> getAllCars();

    // Получение сущности по id
    Car getById(int id);

    // Получить список только свободных машин
    // получить список машин по модели
    MyList<Car> getCarsByModel(String model);

    // UPDATE
    // Сохранить обновленный объект
    void saveCar(Car car);

    // DELETE
    void deleteById(int id);







}
