package webxemphim.com.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webxemphim.com.demo.Model.Nation;

public interface NationRepository extends JpaRepository<Nation, String> {
}
