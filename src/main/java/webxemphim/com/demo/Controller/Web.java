package webxemphim.com.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webxemphim.com.demo.Model.*;
import webxemphim.com.demo.Repository.MovieRepository;
import webxemphim.com.demo.Service.MovieService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/webphim")
public class Web {
    @Autowired
    private MovieService movieService;

    @GetMapping("/home")
    public String Home(Model model) {
        List<Movie> movieNew = movieService.movieNew();
        model.addAttribute("movieNew", movieNew);

        List<Movie> movieByHanhDong = movieService.movieByHanhDong();
        model.addAttribute("movieByHanhDong", movieByHanhDong);

        List<Movie> movieByHai = movieService.movieByHai();
        model.addAttribute("movieByHai", movieByHai);

        List<Movie> movieBo = movieService.movieBo();
        model.addAttribute("movieBo", movieBo);
        return "users/Home";
    }

    @GetMapping("/weble")
    public String FindAll() {

        return "users/WebLe";
    }
}
