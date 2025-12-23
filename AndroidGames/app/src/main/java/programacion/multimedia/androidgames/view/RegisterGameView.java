package programacion.multimedia.androidgames.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;

import programacion.multimedia.androidgames.R;
import programacion.multimedia.androidgames.contract.RegisterGameContract;
import programacion.multimedia.androidgames.presenter.RegisterGamePresenter;
import programacion.multimedia.androidgames.util.DateUtil;

public class RegisterGameView extends AppCompatActivity implements RegisterGameContract.View {

    private RegisterGameContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_game);

        presenter = new RegisterGamePresenter(this);
    }

    public void registerGame(View view) {
        String name = ((EditText) findViewById(R.id.game_name)).getText().toString();
        String description = ((EditText) findViewById(R.id.game_description)).getText().toString();
        String type = ((EditText) findViewById(R.id.game_type)).getText().toString();
        LocalDate releaseDate = DateUtil.parseDate(((EditText) findViewById(R.id.game_release_date)).getText().toString());
        float price = Float.parseFloat(((EditText) findViewById(R.id.game_price)).getText().toString());
        String category = ((EditText) findViewById(R.id.game_category)).getText().toString();

        presenter.registerGame(name, description, type, releaseDate, price, category);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        Snackbar.make(this.getCurrentFocus(), message, Snackbar.LENGTH_LONG);
    }

    @Override
    public void showValidationError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}