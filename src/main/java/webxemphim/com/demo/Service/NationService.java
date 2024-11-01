package webxemphim.com.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webxemphim.com.demo.Model.Nation;
import webxemphim.com.demo.Model.Style;
import webxemphim.com.demo.Repository.NationRepository;

import java.util.List;

@Service
public class NationService {

    @Autowired
    private NationRepository nationRepository;

    public List<Nation> findAll(){
        return nationRepository.findAll();
    }

    public Page<Nation> findAllPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return nationRepository.findAll(pageable);
    }

    public Nation SaveNation(Nation nation){
        return nationRepository.save(nation);
    }

    public Nation findById(String id){
        return nationRepository.findById(id).get();
    }

    public Nation UpdateNation(Nation nation, String id){
        Nation nation1 = findById(id);
        nation1.setName(nation.getName());
        return nationRepository.save(nation1);
    }

    public void delete(String id) {
        nationRepository.delete(findById(id));
    }


}
