package programacion.multimedia.androidgames.domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game {
    private long id;
    private String name;
    private String description;
    private String type;
    private LocalDate releaseDate;
    private float price;
    private String category;
}
