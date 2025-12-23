package programacion.multimedia.androidgames.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import programacion.multimedia.androidgames.R;
import programacion.multimedia.androidgames.adapter.GameAdapter;
import programacion.multimedia.androidgames.contract.GameListContract;
import programacion.multimedia.androidgames.domain.Game;

import programacion.multimedia.androidgames.presenter.GameListPresenter;

public class GameListView extends AppCompatActivity implements GameListContract.View {

    private GameAdapter gameAdapter;
    private List<Game> gameList;
    private GameListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new GameListPresenter(this);

        gameList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.game_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        gameAdapter = new GameAdapter(this, gameList);
        recyclerView.setAdapter(gameAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadGames();
    }

    @Override
    public void showGames(List<Game> games) {
        gameList.clear();
        gameList.addAll(games);
        gameAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_register_game) {
            Intent intent = new Intent(this, RegisterGameView.class);
            startActivity(intent);

            return true;
        }

        return false;
    }
}