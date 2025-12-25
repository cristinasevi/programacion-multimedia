package programacion.multimedia.androidgames.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

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

        SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean userSatelliteView = userPrefs.getBoolean("map_satellite_view_item", false);
        Toast.makeText(this, " " + userSatelliteView, Toast.LENGTH_LONG).show();
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

    public void selectImage(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryActivityResultLauncher.launch(galleryIntent);
    }

    ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Uri image_uri = result.getData().getData();
                    ImageView gameImage = findViewById(R.id.game_image);
                    gameImage.setImageURI(image_uri);
                }
            }
    );

    @Override
    public void showMessage(String message) {
        Snackbar.make(this.getCurrentFocus(), message, Snackbar.LENGTH_LONG).show();
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