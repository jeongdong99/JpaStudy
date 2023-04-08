package jpa_movie.start;

import jpa_movie.start.movie.Cinema;
import jpa_movie.start.system.AutoCreate;
import jpa_movie.start.system.Kiosk;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_movie");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Kiosk kiosk = new Kiosk();
        AutoCreate autoCreate = new AutoCreate(emf);

        try{
            tx.begin();
            autoCreate.run();
            em.flush();
            em.clear();
            tx.commit();
            kiosk.selectMenu(emf);

        }catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
