package webxemphim.com.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webxemphim.com.demo.Model.Cast;
import webxemphim.com.demo.Model.Director;
import webxemphim.com.demo.Model.Nation;
import webxemphim.com.demo.Repository.CastRepository;
import webxemphim.com.demo.Repository.NationRepository;

import java.util.List;

@Service
public class CastService {
    @Autowired
    private CastRepository castRepository;

    public List<Cast> findAll(){
        return castRepository.findAll();
    }

    public Cast SaveCast(Cast cast){
        return castRepository.save(cast);
    }

    public Cast findById(String id){
        return castRepository.findById(id).get();
    }

    public Cast UpdateCast(Cast cast, String id){
        Cast cast1 = findById(id);
        cast1.setName(cast.getName());
        return  castRepository.save(cast1);
    }

    public void delete(String id) {
        castRepository.delete(findById(id));
    }


}
