package programacion.multimedia.androidgames.presenter;

import java.time.LocalDate;

import programacion.multimedia.androidgames.contract.RegisterGameContract;
import programacion.multimedia.androidgames.domain.Game;
import programacion.multimedia.androidgames.model.RegisterGameModel;

public class RegisterGamePresenter implements RegisterGameContract.Presenter,
    RegisterGameContract.Model.OnRegisterListener {

    private RegisterGameContract.Model model;
    private RegisterGameContract.View view;

    public RegisterGamePresenter(RegisterGameContract.View view) {
        model = new RegisterGameModel();
        this.view = view;
    }

    @Override
    public void registerGame(String name, String description, String type, LocalDate releaseDate, float price, String category) {
        // TODO Validar correctamente
        if (name.isEmpty()) {
            view.showValidationError("El nombre es un campo obligatorio");
        }

        Game game = Game.builder()
                .name(name)
                .description(description)
                .type(type)
                .releaseDate(releaseDate)
                .price(price)
                .category(category)
                .build();

        model.registerGame(game, this);
    }

    @Override
    public void onRegisterSuccess(Game game) {
        view.showMessage("Se ha registrado el juego correctamente");
    }

    @Override
    public void onRegisterError(String message) {
        view.showError(message);
    }
}
