package webxemphim.com.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webxemphim.com.demo.Model.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, String> {

    String movie = ("SELECT * FROM webxemphim.movie\n" +
            "ORDER BY start_date DESC\n" +
            "LIMIT 2;");
    @Query(value = movie, nativeQuery = true)
    List<Movie> movieNew();


    String moviebyHanhDong = ("SELECT DISTINCT m.*\n" +
            "FROM webxemphim.movie m\n" +
            "JOIN webxemphim.movie_type mt on mt.movie_id = m.id\n" +
            "JOIN webxemphim.type t on t.id = mt.type_id\n" +
            "where t.name like 'Hành Động'  and m.status like '1' \n" +
            "ORDER BY m.start_date DESC\n" +
            "LIMIT 10;");
    @Query(value = moviebyHanhDong, nativeQuery = true)
        List<Movie> moviebyHanhDong();

    String moviebyHai = ("SELECT DISTINCT m.*\n" +
            "FROM webxemphim.movie m\n" +
            "JOIN webxemphim.movie_type mt on mt.movie_id = m.id\n" +
            "JOIN webxemphim.type t on t.id = mt.type_id\n" +
            "where t.name like 'Hài'  and m.status like '1'\n" +
            "ORDER BY m.start_date DESC\n" +
            "LIMIT 10;");
    @Query(value = moviebyHai, nativeQuery = true)
    List<Movie> moviebyHai();


    String movieBo = ("select DISTINCT m.* \n" +
            "from webxemphim.movie m \n" +
            "JOIN webxemphim.movie_style ms on m.style_id = ms.id\n" +
            "where ms.name = 'Phim bộ'\n" +
            "order by m.start_date desc limit 10 ");
    @Query(value = movieBo, nativeQuery = true)
    List<Movie> movieBo();

}
