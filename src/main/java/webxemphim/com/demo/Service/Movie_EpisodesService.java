package webxemphim.com.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webxemphim.com.demo.Model.Cast;
import webxemphim.com.demo.Model.Movie_Episodes;
import webxemphim.com.demo.Repository.CastRepository;
import webxemphim.com.demo.Repository.Movie_EpisodesRepository;

import java.util.List;

@Service
public class Movie_EpisodesService {
    @Autowired
    private Movie_EpisodesRepository movie_episodesRepository;

    public List<Movie_Episodes> findAll(){
        return movie_episodesRepository.findAll();
    }

    public Movie_Episodes SaveMovie_episodes(Movie_Episodes movie_episodes){
        return movie_episodesRepository.save(movie_episodes);
    }

    public Movie_Episodes findById(String id){
        return movie_episodesRepository.findById(id).get();
    }

    public Movie_Episodes UpdateMovie_episodes(Movie_Episodes movie_episodes, String id){
        Movie_Episodes movie_episodes1 = findById(id);
        movie_episodes1.setLink(movie_episodes.getLink());
        movie_episodes1.setEpisodes(movie_episodes.getEpisodes());
        return  movie_episodesRepository.save(movie_episodes1);
    }

    public void delete(String id) {
        movie_episodesRepository.delete(findById(id));
    }

}
