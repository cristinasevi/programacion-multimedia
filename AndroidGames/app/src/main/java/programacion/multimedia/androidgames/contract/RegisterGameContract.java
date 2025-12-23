package programacion.multimedia.androidgames.contract;

import java.time.LocalDate;

import programacion.multimedia.androidgames.domain.Game;

public interface RegisterGameContract {
    interface Model {
        interface OnRegisterListener {
            void onRegisterSuccess(Game game);
            void onRegisterError(String message);
        }
        void registerGame(Game game, OnRegisterListener listener);
    }

    interface View {
        void showMessage(String message);
        void showError(String message);
        void showValidationError(String message);
    }

    interface Presenter {
        void registerGame(String name, String description, String type, LocalDate releaseDate, float price, String category);
    }
}
