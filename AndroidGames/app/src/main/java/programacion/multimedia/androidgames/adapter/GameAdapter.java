package programacion.multimedia.androidgames.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import programacion.multimedia.androidgames.R;
import programacion.multimedia.androidgames.domain.Game;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameHolder> {

    private Context context;
    private List<Game> gameList;

    public GameAdapter(Context context, List<Game> gameList) {
        this.context = context;
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public GameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_item, parent, false);
        return new GameHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameHolder holder, int position) {
        holder.gameName.setText(gameList.get(position).getName());
        holder.gameDescription.setText(gameList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public class GameHolder extends RecyclerView.ViewHolder {

        private TextView gameName;
        private TextView gameDescription;

        public GameHolder(@NonNull View itemView) {
            super(itemView);

            gameName = itemView.findViewById(R.id.item_game_name);
            gameDescription = itemView.findViewById(R.id.item_game_description);
        }
    }
}
