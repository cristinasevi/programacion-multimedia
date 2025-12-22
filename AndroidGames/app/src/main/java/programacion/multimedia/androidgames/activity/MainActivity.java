package programacion.multimedia.androidgames.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import programacion.multimedia.androidgames.R;
import programacion.multimedia.androidgames.adapter.GameAdapter;
import programacion.multimedia.androidgames.api.GameApi;
import programacion.multimedia.androidgames.api.GameApiInterface;
import programacion.multimedia.androidgames.domain.Game;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GameAdapter gameAdapter;
    private List<Game> gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        refreshList();
    }

    private void refreshList() {
        gameList.clear();

        GameApiInterface gameApi = GameApi.buildInstance();
        Call<List<Game>> getGamesCall = gameApi.getGames();
        getGamesCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.code() == 200) {
                    gameList.addAll(response.body());
                    gameAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No se ha podido conectar con el servidor", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}