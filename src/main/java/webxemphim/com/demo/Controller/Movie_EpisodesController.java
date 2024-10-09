package webxemphim.com.demo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webxemphim.com.demo.Model.Cast;
import webxemphim.com.demo.Model.Movie;
import webxemphim.com.demo.Model.Movie_Episodes;
import webxemphim.com.demo.Model.Nation;
import webxemphim.com.demo.Service.MovieService;
import webxemphim.com.demo.Service.Movie_EpisodesService;
import webxemphim.com.demo.Service.NationService;

import java.util.List;

@Controller
@RequestMapping("/movie_episodes")

public class Movie_EpisodesController {
    @Autowired
    private Movie_EpisodesService movie_episodesService;

    @Autowired
    private MovieService movieService;


//    @GetMapping("/findAll")
//    public String FindAll(Model model){
//        List<Movie_Episodes> movie_episodesServiceList = movie_episodesService.findAll();
//        model.addAttribute("movie_episodesServiceList",movie_episodesServiceList);
//        model.addAttribute("movie_episodes", new Movie_Episodes());
//        List<Movie> movieList = movieService.findAll();
//        model.addAttribute("movieList", movieList);
//        return "admin/ViewMovie_episodes";
//    }

    @PostMapping("/save")
    public String SaveMovie_Episodes(Model model,
                                     @RequestParam(name = "id") String id,
                                     @RequestParam(name = "episodes") Integer episodes,
                                     @RequestParam(name = "link") String link,
                                     @RequestParam(name = "movie") Movie movie,
                                     RedirectAttributes ra) {


        try {
            Movie_Episodes movie_episodes = Movie_Episodes.builder()
                    .id(id)
                    .episodes(episodes)
                    .link(link)
                    .movie(movie)
                    .build();

            if (movie_episodesService.SaveMovie_episodes(movie_episodes) instanceof Movie_Episodes) {
                ra.addFlashAttribute("successMessage", "Thêm thành công");
            } else {
                ra.addFlashAttribute("errorMessage", "Thêm thất bại");
            }
//            model.addAttribute("nation", new Nation());
            return "redirect:/movie/" + movie.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/movie/" + movie.getId();
        }
    }


//
//    @PostMapping("/update/{id}")
//    public String updatePromotion(@PathVariable(name = "id") String id, Nation nation, RedirectAttributes ra) {
//        nationService.UpdateNation(nation, id);
//        ra.addFlashAttribute("successMessage", "Sửa thành công!!!");
//
//        return "redirect:/nation/findAll";   // Redirect to the promotion list page after update
//    }
//
    @GetMapping("/delete/{id}")
    public String deleteNation(@PathVariable(name = "id") String id, RedirectAttributes ra,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Movie movie = (Movie) session.getAttribute("movie");
        try {
            movie_episodesService.delete(id);
            ra.addFlashAttribute("successMessage", "Xóa thành công!!!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Xóa thất bại!!!");
        }
        return "redirect:/movie/" + movie.getId();
    }
}
