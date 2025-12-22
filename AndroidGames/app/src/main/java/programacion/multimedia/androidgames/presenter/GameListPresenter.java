package programacion.multimedia.androidgames.presenter;

import java.util.List;

import programacion.multimedia.androidgames.contract.GameListContract;
import programacion.multimedia.androidgames.domain.Game;
import programacion.multimedia.androidgames.model.GameListModel;

public class GameListPresenter implements GameListContract.Presenter, GameListContract.Model.OnLoadListener {

    private GameListContract.Model model;
    private GameListContract.View view;

    public GameListPresenter(GameListContract.View view) {
        this.model = new GameListModel();
        this.view = view;
    }

    @Override
    public void loadGames() {
        model.loadGames(this);
    }

    @Override
    public void onLoadSuccess(List<Game> games) {
        view.showGames(games);
        view.showMessage("Los juegos se han cargado con éxito");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }
}
