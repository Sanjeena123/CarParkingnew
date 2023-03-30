package service;

import entity.Resident;
import entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CarParkingConfigurationConnection {
    public void addResidentAndVehicle(Resident resident,Vehicle vehicle){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session =sessionFactory.openSession();

        Transaction transaction=null;
        try {
            transaction = session.beginTransaction();
           // session.save(resident);
            vehicle.setResident(resident);
            session.save(vehicle);
            transaction.commit();
        } catch(Exception e) {
            System.err.println("Error Details ::" + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public boolean valiadateMobNumber(long mobileNumber) {
        String monNumberConverted = String.valueOf(mobileNumber);
        if (monNumberConverted != null
                && monNumberConverted.length() == 10) {
            return true;
        }
        return false;
    }
    public boolean valiadateRegesterNumber(int regNumber) {
        String monNumberConverted = String.valueOf(regNumber);
        if (monNumberConverted != null
                && monNumberConverted.length() == 8) {
            return true;
        }
        return false;
    }
}