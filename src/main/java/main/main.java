package main;
/*Write a java Program for Car Parking lot of Housing Apartment:

Background of car parking systems, in housing apartment there are 2 type of parking level one for 4 wheeler
 another for 2 wheeler.
Now we have to write a java program where residents will register their respective 4 wheeler and 2 wheeler for the parking.

Save the resident information to Database with parking information.
Resident details like, 'name', 'email', 'flatNumber', 'mobileNumber', 'vechicleInfo'. Under vehicleInfo,
 ‘type’ and ‘registrationNumber’.

Few Validation need to be,
     1. Phone number should be in the length of 10. If fails error message will be thrown as the
     "Mobile Number is invalid please enter again"
   2. Vehicle details like 4 wheeler or 2 wheeler with valid registration number
    (need to keep validation here, registration number should not be more than 8 character)
    . If fails error message will be thrown as the "Please provide valid registration number"

Create a hibernate xml application

create 2 table name "Resident" and "Vehicle". Resident table will be connected with Vehicle table in OneToOne relationship.*/


import entity.Resident;
import entity.Vehicle;
import service.CarParkingConfigurationConnection;
import service.Residentlimitation;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Resident resident=new Resident();
        Vehicle vehicle =new Vehicle();
        CarParkingConfigurationConnection carParkingConfigurationConnection =new CarParkingConfigurationConnection();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Welcome to Car Parking lot of Housing Apartment:");
        System.out.println("please enter your name:  ");
        String residentName= scanner.next();
        resident.setResidentName(residentName);

        System.out.println("please enter your email");
        String email= scanner.next();
        resident.setEmail(email);

        System.out.println("please enter your flat number::");
        int flatNumber= scanner.nextInt();

        System.out.println("please enter your mobile number: ");
        long mobileNumber = scanner.nextLong();
        resident.setMobileNumber(mobileNumber);
        int mobValidationCounter = 0;

        do {
            if (carParkingConfigurationConnection.valiadateMobNumber(mobileNumber)) {
                resident.setMobileNumber(mobileNumber);
                break;
            } else {
                System.out.println("Insufficient number of dail please  valid mobile number again....");

                mobValidationCounter++;
            }
        }
        while (mobValidationCounter < Residentlimitation.MAX_RE_TRY_COUNT);
        {
            if (mobValidationCounter == Residentlimitation.MAX_RE_TRY_COUNT) {
                System.out.println("you have reach maximum limit");
            }
        }
        System.out.println("please enter your vehicle type(4 wheeler or 2 wheeler)");
        String vehicleType =scanner.next();
        vehicle.setVehicleType(vehicleType);

        System.out.println("please enter registration Number of the vehicle::");
        int registrationNumber=scanner.nextInt();
        if (carParkingConfigurationConnection.valiadateRegesterNumber(registrationNumber)) {
            vehicle.setRegistrationNumber(registrationNumber);
        } else {
            System.out.println("Please provide valid registration number...");

            mobValidationCounter++;

        }
        carParkingConfigurationConnection.addResidentAndVehicle(resident,vehicle);
    }



}
