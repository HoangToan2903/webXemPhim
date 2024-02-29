package webxemphim.com.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webxemphim.com.demo.Model.Nation;
import webxemphim.com.demo.Model.Type;

public interface TypeRepository extends JpaRepository<Type, String> {
}
