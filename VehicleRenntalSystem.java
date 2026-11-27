
import java.util.Scanner;

public class VehicleRenntalSystem {

    static Vehicle getVehicle(int vehicleType) {
        if (vehicleType < 1 || vehicleType > 3) {
            throw new IllegalArgumentException("Invalid Vehicle Type");
        }

        if (vehicleType == 1) {
            return new Car();
        } else if (vehicleType == 2) {
            return new Bike();
        }
        return new Truck();
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            int vehicleType = sc.nextInt();

            Vehicle vehicle = getVehicle(vehicleType);

            int rentalDays = sc.nextInt();

            System.out.println("Vehicle Type: " + vehicle.getVehicleType());
            System.out.printf("Rental Charge: %.2f%n", vehicle.calculateRentalCharge(rentalDays));

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}

abstract class Vehicle {

    private double dailyRate;

    public Vehicle(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void validateRentalDays(int rentalDays) {
        if (rentalDays > 30 || rentalDays < 1) {
            throw new IllegalArgumentException("Invalid Rental Days");
        }
    }

    public abstract double calculateRentalCharge(int rentalDays);

    public abstract String getVehicleType();
}

class Car extends Vehicle {

    public Car() {
        super(1500);
    }

    @Override
    public double calculateRentalCharge(int rentalDays) {

        validateRentalDays(rentalDays);
        return rentalDays * getDailyRate();
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

}

class Bike extends Vehicle {

    public Bike() {
        super(600);
    }

    @Override
    public double calculateRentalCharge(int rentalDays) {
        validateRentalDays(rentalDays);
        return rentalDays * getDailyRate();
    }

    @Override
    public String getVehicleType() {
        return "Bike";
    }

}

class Truck extends Vehicle {

    public Truck() {
        super(3000);
    }

    @Override
    public double calculateRentalCharge(int rentalDays) {
        validateRentalDays(rentalDays);
        return rentalDays * getDailyRate();
    }

    @Override
    public String getVehicleType() {
        return "Truck";
    }

}
