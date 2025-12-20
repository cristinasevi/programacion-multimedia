package programacion.multimedia.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import programacion.multimedia.todolist.R;
import programacion.multimedia.todolist.db.AppDatabase;
import programacion.multimedia.todolist.db.DatabaseUtil;
import programacion.multimedia.todolist.domain.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private Context context;
    private List<Task> taskList;

    public TaskAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todolist_item, parent, false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskHolder holder, int position) {
        holder.itemTaskName.setText(taskList.get(position).getName());
        holder.itemTaskDescription.setText(taskList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder {

        private TextView itemTaskName;
        private TextView itemTaskDescription;
        private Button deleteItemTaskButton;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);

            itemTaskName = itemView.findViewById(R.id.itemTaskName);
            itemTaskDescription = itemView.findViewById(R.id.itemTaskDescription);
            deleteItemTaskButton = itemView.findViewById(R.id.deleteItemTaskButton);

            deleteItemTaskButton.setOnClickListener(view -> {
                final AppDatabase db = DatabaseUtil.getDb(context);
                db.taskDao().deleteByName(itemTaskName.getText().toString());

                int position = getAdapterPosition();
                taskList.remove(position);
                notifyItemRemoved(position);
            });

            // FIXME Ver donde encajar eso para que funcione correctamente
//            int position = getAdapterPosition();
//            Task task = taskList.get(position);
//            if(task.isDone()) {
//                itemView.setBackgroundColor(itemView.getContext().getResources().getColor(
//                        android.R.color.holo_green_light, itemView.getContext().getTheme()
//                ));
//            } else {
//                itemView.setBackgroundColor(itemView.getContext().getResources().getColor(
//                        android.R.color.holo_red_light, itemView.getContext().getTheme()
//                ));
//            }
        }
    }
}
