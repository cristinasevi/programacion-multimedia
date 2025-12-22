package programacion.multimedia.androidgames.contract;

import java.util.List;

import programacion.multimedia.androidgames.domain.Game;

public interface GameListContract {
    interface Model {
        interface OnLoadListener {
            void onLoadSuccess(List<Game> games);
            void onLoadError(String message);
        }
        void loadGames(OnLoadListener listener);
    }

    interface View {
        void showGames(List<Game> games);
        void showMessage(String message);
        void showError(String message);
    }

    interface Presenter {
        void loadGames();
    }
}
