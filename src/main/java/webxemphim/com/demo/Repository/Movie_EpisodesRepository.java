package webxemphim.com.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webxemphim.com.demo.Model.Movie;
import webxemphim.com.demo.Model.Movie_Episodes;

public interface Movie_EpisodesRepository extends JpaRepository<Movie_Episodes, String> {
}
