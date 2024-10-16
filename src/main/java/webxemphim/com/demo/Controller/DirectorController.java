package webxemphim.com.demo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webxemphim.com.demo.Model.Cast;
import webxemphim.com.demo.Model.Director;
import webxemphim.com.demo.Model.Style;
import webxemphim.com.demo.Service.CastService;
import webxemphim.com.demo.Service.DirectorService;
import webxemphim.com.demo.Util.UploadImage;

import java.util.List;

@Controller
@RequestMapping("/director")
public class DirectorController {
    @Autowired
    private DirectorService directorService;

    @Autowired
    private UploadImage uploadImage;

    @GetMapping("/findAll")
    public String FindAll(Model model,  @PageableDefault(size = 5) Pageable pageable) {
//        List<Type> typeList = typeService.findAll();
        Page<Director> directorList = directorService.findAllPage(pageable.getPageNumber(), pageable.getPageSize());
        model.addAttribute("directorList", directorList);
        model.addAttribute("director", new Director());

        return "admin/ViewDirector";
    }

    @PostMapping("/save")
    public String SaveNation(Model model,
                             @RequestParam(name="id") String id,
                             @RequestParam(name = "name") String name,

                             RedirectAttributes ra){
        try {
            Director director = Director.builder()
                    .id(id)
                    .name(name)
                    .build();

            if (directorService.SaveDirector(director) instanceof Director) {
                ra.addFlashAttribute("successMessage", "Thêm thành công");
            } else {
                ra.addFlashAttribute("errorMessage", "Thêm thất bại");
            }
//            model.addAttribute("nation", new Nation());
            return "redirect:/director/findAll";
        } catch (Exception e) {
            e.printStackTrace();
            return "admin/ViewDirector";
        }
    }

    @PostMapping("/update/{id}")
    public String updatePromotion(@PathVariable(name = "id") String id, Director director, RedirectAttributes ra) {
        directorService.UpdateDirector(director, id);
        ra.addFlashAttribute("successMessage", "Sửa thành công!!!");

        return "redirect:/director/findAll";   // Redirect to the promotion list page after update
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "[Xóa dữ liệu room]")
    public String deleteNation(@PathVariable(name = "id") String id, RedirectAttributes ra) {
        try {
            directorService.delete(id);
            ra.addFlashAttribute("successMessage", "Xóa thành công!!!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Xóa thất bại!!!");
        }
        return "redirect:/director/findAll";   // Redirect to the promotion list page after update
    }
//    @GetMapping("/update/{id}")
//    public String detailMovie(@PathVariable(name = "id") String id, Model model) {
//        List<Director> movieDirecttor = directorService.findDireactorByMovieId(id);
//        System.out.println(movieDirecttor);
//        List<MovieType> movieTypes = movieTypeService.findMovieTyprbyMovieId(id);
//        List<Language> languageSelector = languageService.findNameByMovieId(id);
//        List<Performer> perfprmerSelect = performerService.findPerformerByMovieId(id);
//        List<Rated> ratedId = ratedService.fillAll();
//        List<Director> directorId = directorService.fillAll();
//        List<Language> languageId = languageService.fillAll();
//        List<MovieType> movieTypeId = movieTypeService.fillAll();
//        List<Performer> performerId = performerService.fillAll();
//
//        model.addAttribute("ratedId", ratedId);
//        model.addAttribute("ratedId", ratedId);
//        model.addAttribute("languages", languageId);
//        model.addAttribute("movieTypes", movieTypeId);
//        model.addAttribute("directors", directorId);
//        model.addAttribute("directorsSelect", movieDirecttor);
//        model.addAttribute("performerSelect", perfprmerSelect);
//        model.addAttribute("performers", performerId);
//        model.addAttribute("languageSelect", languageSelector);
////        model.addAttribute("movie", new Movie());
//        model.addAttribute("movie", service.findById(id));
//        return "admin/form-add-movie";
//    }

}
