package webxemphim.com.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webxemphim.com.demo.Model.Cast;
import webxemphim.com.demo.Model.Director;
import webxemphim.com.demo.Repository.CastRepository;
import webxemphim.com.demo.Repository.DirectorRepository;

import java.util.List;

@Service
public class DirectorService {
    @Autowired
    private DirectorRepository directorRepository;

    public List<Director> findAll(){
        return directorRepository.findAll();
    }

    public Director SaveDirector(Director director){
        return directorRepository.save(director);
    }

    public Director findById(String id){
        return directorRepository.findById(id).get();
    }

    public  Director UpdateDirector(Director director, String id){
        Director director1 = findById(id);
        director1.setName(director.getName());
        return directorRepository.save(director1);
    }

    public void delete(String id) {
        directorRepository.delete(findById(id));
    }


}
