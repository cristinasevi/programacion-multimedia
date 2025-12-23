package programacion.multimedia.androidgames.model;

import programacion.multimedia.androidgames.contract.RegisterGameContract;
import programacion.multimedia.androidgames.domain.Game;
import programacion.multimedia.androidgames.api.GameApi;
import programacion.multimedia.androidgames.api.GameApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterGameModel implements RegisterGameContract.Model {

    @Override
    public void registerGame(Game game, OnRegisterListener listener) {
        GameApiInterface api = GameApi.buildInstance();
        Call<Game> postGamesCall = api.registerGame(game);
        postGamesCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if (response.code() == 201) {
                    listener.onRegisterSuccess(response.body());
                } else if (response.code() == 400) {
                    // TODO Parsear el cuerpo de la respuesta para indicar los errores concretos
                    listener.onRegisterError("Errores de validación");
                }
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                listener.onRegisterError("No se ha podido conectar con el servidor");
            }
        });
    }
}
