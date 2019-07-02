
package dao;

import entities.Trainer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class TrainerDao {
    
    public List<Trainer> getTrainersJPA() {
        List<Trainer> list = new ArrayList();
        
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("TrainersCrudJpaPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            Query query = em.createQuery("Select t from Trainer t");
            list=query.getResultList();
        }catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
            emf.close();
        }
        return list;      
    }
    
    public Trainer getTrainerById(int id){
        Trainer t = null;
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("TrainersCrudJpaPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            t = em.find(entities.Trainer.class,id);
        }catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
            emf.close();
        }
        return t;
    }
    
    public boolean insertStudentJPA(Trainer t) {
        boolean completed = false;
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("TrainersCrudJpaPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            // transaction
            em.persist(t);
            em.getTransaction().commit();
            completed = true;
        }catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
            emf.close();
        }
        return completed;
    }
    
    public boolean updateStudentJPA(Trainer t) {
        boolean completed = false;
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("TrainersCrudJpaPU");
        EntityManager em = emf.createEntityManager();
        
        Trainer fromDBtrainer = em.find(entities.Trainer.class, t.getId());
        fromDBtrainer.setFname(t.getFname());
        fromDBtrainer.setLname(t.getLname());
        em.getTransaction().begin();
        try{
            // transaction
            em.persist(fromDBtrainer);
            em.getTransaction().commit();
            completed = true;
        }catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
            emf.close();
        }
        return completed;
    }
    
    public boolean deleteTrainerJPA(int i){
        boolean completed = false;
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("TestWebAppPU");
        EntityManager em = emf.createEntityManager();
        
        Trainer fromDBtrainer = em.find(entities.Trainer.class, getTrainerById(i));
        em.getTransaction().begin();
        try{
            em.remove(fromDBtrainer);
            completed = true;
        }catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
       finally {
            em.close();
            emf.close();
        }
        return completed;
    
    }
    
}
