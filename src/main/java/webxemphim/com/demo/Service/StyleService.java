package webxemphim.com.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webxemphim.com.demo.Model.Style;
import webxemphim.com.demo.Model.Type;
import webxemphim.com.demo.Repository.StyleRepository;
import webxemphim.com.demo.Repository.TypeRepository;

import java.util.List;

@Service
public class StyleService {

    @Autowired
    private StyleRepository styleRepository;

    public List<Style> findAll(){
        return styleRepository.findAll();
    }

    public Page<Style> findAllPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return styleRepository.findAll(pageable);
    }


    public Style SaveStyle(Style style){
        return styleRepository.save(style);
    }

    public Style findById(String id){
        return styleRepository.findById(id).get();
    }

    public Style UpdateStyle(Style type, String id){
        Style type1 = findById(id);
        type1.setName(type.getName());
        return styleRepository.save(type1);
    }

    public void delete(String id) {
        styleRepository.delete(findById(id));
    }

}
