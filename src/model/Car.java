package model;

import java.util.Objects;

public class Car {
    private final int id;
    private String model;
    private final int year;
    private double price;
    private boolean isBusy; // занята? значение по умолчанию false

    public Car(int id, String model, int year, double price) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{ " +
                "id=" + id +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", isBusy=" + isBusy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return id == car.id && year == car.year && Double.compare(price, car.price) == 0 && isBusy == car.isBusy && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, year, price, isBusy);
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
