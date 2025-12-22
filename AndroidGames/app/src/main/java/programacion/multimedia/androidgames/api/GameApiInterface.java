package programacion.multimedia.androidgames.api;

import java.util.List;

import programacion.multimedia.androidgames.domain.Game;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GameApiInterface {
    @GET("games")
    Call<List<Game>> getGames();

    @POST("games")
    Call<Game> registerGame(@Body Game game);

    @GET("games/{id}")
    Call<Game> getGame(@Path("id") long id);
}
