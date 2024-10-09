package webxemphim.com.demo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "trailer")
    private String trailer;

    @Column(name = "status")
    private Integer status;

    @Column(name = "start_date")
    private LocalDateTime start_date;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "link", length = 1000)
    private String link;

    @Column(name = "namphathanh", length = 1000)
    private Integer namphathanh;

    @Column(name = "episodes")
    private Integer episodes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "nation_id")
    private Nation nation;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "style_id")
    private Style style;

    @ManyToMany
    @JoinTable(
            name = "movie_director",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    private List<Director> directors = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "movie_cast",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "cast_id"))
    private List<Cast> casts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "movie_type",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private List<Type> types = new ArrayList<>();

}
