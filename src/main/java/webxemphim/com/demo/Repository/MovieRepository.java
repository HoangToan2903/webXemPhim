package webxemphim.com.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webxemphim.com.demo.Model.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {
}
