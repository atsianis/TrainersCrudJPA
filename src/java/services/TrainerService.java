
package services;

import dao.TrainerDao;
import entities.Trainer;
import java.util.List;

public class TrainerService {
    
    public List<Trainer> getTrainers(){
        TrainerDao tdao = new TrainerDao();
        return tdao.getTrainers();
    }
    
    public boolean addTrainer(Trainer t){
        TrainerDao tdao = new TrainerDao();
        return tdao.insertStudentJPA(t);
    }
    
    public Trainer getTrainerById(int id){
        TrainerDao tdao = new TrainerDao();
        return tdao.getTrainerById(id);
    }
    
    public boolean updateTrainer(Trainer t){
        TrainerDao tdao = new TrainerDao();
        return tdao.updateStudentJPA(t);
    }
    
    public boolean deleteTrainer(int id){
        TrainerDao tdao = new TrainerDao();
        return tdao.deleteTrainerJPA(id);
    }
}
