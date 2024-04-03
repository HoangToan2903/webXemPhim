package webxemphim.com.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webxemphim.com.demo.Model.Director;

public interface DirectorRepository extends JpaRepository<Director, String> {
}
