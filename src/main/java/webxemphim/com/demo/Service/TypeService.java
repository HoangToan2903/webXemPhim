package webxemphim.com.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webxemphim.com.demo.Model.Nation;
import webxemphim.com.demo.Model.Type;
import webxemphim.com.demo.Repository.NationRepository;
import webxemphim.com.demo.Repository.TypeRepository;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public List<Type> findAll(){
        return typeRepository.findAll();
    }

    public Type SaveType(Type type){
        return typeRepository.save(type);
    }

    public Type findById(String id){
        return typeRepository.findById(id).get();
    }

    public Type UpdateType(Type type, String id){
        Type type1 = findById(id);
        type1.setName(type.getName());
        return typeRepository.save(type1);
    }

    public void delete(String id) {
        typeRepository.delete(findById(id));
    }


}
