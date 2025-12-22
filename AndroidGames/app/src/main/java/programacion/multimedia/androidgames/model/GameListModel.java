package programacion.multimedia.androidgames.model;

import android.widget.Toast;

import java.util.List;

import programacion.multimedia.androidgames.api.GameApi;
import programacion.multimedia.androidgames.api.GameApiInterface;
import programacion.multimedia.androidgames.contract.GameListContract;
import programacion.multimedia.androidgames.domain.Game;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameListModel implements GameListContract.Model {

    @Override
    public void loadGames(OnLoadListener listener) {
        GameApiInterface gameApi = GameApi.buildInstance();
        Call<List<Game>> getGamesCall = gameApi.getGames();
        getGamesCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.code() == 200) {
                    List<Game> games = response.body();
                    listener.onLoadSuccess(games);
                } else if (response.code() == 500) {
                    listener.onLoadError("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                listener.onLoadError("No se ha podido conectar con el servidor");
            }
        });
    }
}
