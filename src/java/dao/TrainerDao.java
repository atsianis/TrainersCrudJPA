
package dao;

import entities.Trainer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TrainerDao {
    private final String URL = "jdbc:mysql://localhost:3306/trainers_crud_jpa?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "uaIngSOm0f";
    private Connection conn;
    
    private final String getTrainers = "select * from trainer;";
    
    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (Exception ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;

    }
    
    private void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Trainer> getTrainers() {
        List<Trainer> list = new ArrayList();

        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getTrainers);
            //Step 4:
            while (rs.next()) {
                Trainer t = new Trainer (rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(t);
            }
            //Step 5:close connections
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(Trainer.class.getName()).log(Level.SEVERE, null, ex);
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
        em.close();
        emf.close();
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
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("TestWebAppPU");
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
