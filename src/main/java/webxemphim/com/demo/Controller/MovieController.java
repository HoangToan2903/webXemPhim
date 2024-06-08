package webxemphim.com.demo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webxemphim.com.demo.Model.*;
import webxemphim.com.demo.Service.*;
import webxemphim.com.demo.Util.UploadImage;

import java.util.List;

@Controller
@RequestMapping("/movie")

public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private NationService nationService;

    @Autowired
    private CastService castService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private StyleService styleService;

    @Autowired
    private UploadImage uploadImage;

    @GetMapping("/findAll")
    public String FindAll(Model model) {
        List<Movie> movieList = movieService.findAll();
        model.addAttribute("movieList", movieList);

        List<Nation> nationList = nationService.findAll();
        model.addAttribute("nationList", nationList);

        List<Cast> castList = castService.findAll();
        model.addAttribute("castList", castList);

        List<Type> typeList = typeService.findAll();
        model.addAttribute("typeList", typeList);

        List<Director> directorList = directorService.findAll();
        model.addAttribute("directorList", directorList);

        List<Style> styleList = styleService.findAll();
        model.addAttribute("styleList", styleList);

        model.addAttribute("movie", new Movie());

        return "admin/ViewMovie";
    }

    @PostMapping("/save")
    public String SaveNation(Model model,
                             @RequestParam(name="id") String id,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "duration") Integer duration,
                             @RequestParam(name = "trailer") String trailer,
                             @RequestParam(name = "status") Integer status,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "link") String link,
                             @RequestParam(name = "namphathanh") Integer namphathanh,
                             @RequestParam(name = "image") MultipartFile multipartFile,
                             @RequestParam(name = "cast") List<Cast> cast,
                             @RequestParam(name = "type") List<Type> type,
                             @RequestParam(name = "director") List<Director> director,
                             @RequestParam(name = "nation") Nation nation,
                             @RequestParam(name = "style") Style style,
                             @RequestParam(name = "episodes", required = false, defaultValue = "1") Integer episodes,
                             RedirectAttributes ra) {
        uploadImage.handerUpLoadFile(multipartFile);
        try {
            Movie movie = Movie.builder()
                    .duration(duration)
                    .name(name)
                    .description(description)
                    .trailer(trailer)
                    .link(link)
                    .image(multipartFile.getOriginalFilename())
                    .nation(nation)
                    .style(style)
                    .episodes(episodes)
                    .directors(director)
                    .types(type)
                    .status(status)
                    .casts(cast)
                    .namphathanh(namphathanh)
                    .build();

            if (movieService.SaveMovie(movie) instanceof Movie) {
                model.addAttribute("successMessage", "Thêm thành công!");
            } else {
                model.addAttribute("errorMessage", "Thêm thất bại");
            }
            model.addAttribute("movie", new Movie());
            return "redirect:/movie/findAll";
        } catch (Exception e) {
            e.printStackTrace();
            return "admin/ViewMovie";
        }
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "[Xóa dữ liệu room]")
    public String deleteNation(@PathVariable(name = "id") String id, RedirectAttributes ra) {
        try {
            movieService.delete(id);
            ra.addFlashAttribute("successMessage", "Xóa thành công!!!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Xóa thất bại!!!");
        }
        return "redirect:/movie/findAll";   // Redirect to the promotion list page after update
    }

    @PostMapping("/update/{id}")
    public String updatePromotion(@PathVariable(name = "id") String id, Movie movie, RedirectAttributes ra) {
        movieService.updateMovie(id, movie);
        ra.addFlashAttribute("successMessage", "Sửa thành công!!!");

        return "redirect:/movie/findAll";   // Redirect to the promotion list page after update
    }

}
