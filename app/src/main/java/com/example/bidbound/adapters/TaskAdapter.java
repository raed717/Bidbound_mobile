package com.example.bidbound.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bidbound.R;
import com.example.bidbound.entities.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    private List<Task> tasks;
    private Context context;

    public TaskAdapter(Context context, List<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_afficher_list_taches, parent, false);
        return new TaskAdapter.TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.taskNameTextView.setText(task.getTaskName());
        holder.taskDescriptionTextView.setText(task.getTaskDescription());
        holder.taskComplexiteTextView.setText(task.getComplexite());
        // Bind other views in holder as needed
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskNameTextView;
        TextView taskDescriptionTextView;
        TextView taskComplexiteTextView;
        // Other views

        TaskViewHolder(View itemView) {
            super(itemView);
            taskNameTextView = itemView.findViewById(R.id.taskNameTextView);
            taskDescriptionTextView = itemView.findViewById(R.id.taskDescriptionTextView);
            taskComplexiteTextView = itemView.findViewById(R.id.taskComplexiteTextView);
            // Initialize other views
        }
    }
}
