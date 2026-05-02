package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Date;

public class ClientDemo {
    public static void main(String[] args) {
   
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

      
        Session session = factory.openSession();

        Transaction tx1 = session.beginTransaction();
        Library lib = new Library();
        lib.setName("Data Science Handbook");
        lib.setDescription("Reference book for ML concepts");
        lib.setDate(new Date());
        lib.setStatus("Available");
        session.save(lib);
        tx1.commit();
        System.out.println("Record Inserted with ID: " + lib.getId());

      
        Transaction tx2 = session.beginTransaction();
        Library libToDelete = session.get(Library.class, lib.getId());
        if (libToDelete != null) {
            session.delete(libToDelete);
            System.out.println("Record Deleted with ID: " + libToDelete.getId());
        }
        
        tx2.commit();

     
        session.close();
        factory.close();
    }
}
