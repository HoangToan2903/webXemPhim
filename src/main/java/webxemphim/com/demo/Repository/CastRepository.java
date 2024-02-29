package webxemphim.com.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webxemphim.com.demo.Model.Cast;

public interface CastRepository extends JpaRepository<Cast, String> {
}
