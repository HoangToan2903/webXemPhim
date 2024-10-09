package webxemphim.com.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webxemphim.com.demo.Model.Director;
import webxemphim.com.demo.Model.Movie;
import webxemphim.com.demo.Repository.MovieRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie SaveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie findById(String id) {
        return movieRepository.findById(id).get();
    }

    public Movie updateMovie(String id, Movie movie) {
        Movie movieNew = findById(id);
//        LocalDateTime currentDateTime = LocalDateTime.now();
        movieNew.setName(movie.getName());
        movieNew.setImage(movie.getImage());
        movieNew.setDuration(movie.getDuration());
        movieNew.setTrailer(movie.getTrailer());
        movieNew.setStart_date(movie.getStart_date());
        movieNew.setLink(movie.getLink());
        movieNew.setDirectors(movie.getDirectors());
        movieNew.setCasts(movie.getCasts());
        movieNew.setStatus(movie.getStatus());
        movieNew.setNamphathanh(movie.getNamphathanh());
        movieNew.setLink(movie.getLink());
        movieNew.setTypes(movie.getTypes());
        movieNew.setEpisodes(movie.getEpisodes());
        movieNew.setDescription(movie.getDescription());
        movieNew.setNation(movie.getNation());
        movieNew.setStyle(movie.getStyle());
        return movieRepository.save(movieNew);
    }

    public void delete(String id) {
        movieRepository.delete(findById(id));
    }


    public List<Movie> movieNew() {
        return movieRepository.movieNew();
    }

    public List<Movie> movieByHanhDong() {
        return movieRepository.moviebyHanhDong();
    }

    public List<Movie> movieByHai() {
        return movieRepository.moviebyHai();
    }

    public List<Movie> movieBo() {
        return movieRepository.movieBo();
    }

}
