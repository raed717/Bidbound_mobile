package com.example.bidbound.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bidbound.R;
import com.example.bidbound.entities.Project;

import java.util.List;

// ... other imports ...

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private List<Project> projects;
    private Context context;

    public interface ProjectAdapterListener {
        void onEditProject(Project project);
    }
    private ProjectAdapterListener listener;

    public ProjectAdapter(Context context, List<Project> projects, ProjectAdapterListener listener) {
        this.context = context;
        this.projects = projects;
        this.listener = listener;
    }

    public ProjectAdapter(Context context, List<Project> projects) {
        this.context = context;
        this.projects = projects;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.project_list_item, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project project = projects.get(position);
        holder.projectNameTextView.setText(project.getProjectName());
        holder.projectCategoryTextView.setText(project.getProjectCategory());
        holder.projectStartDateTextView.setText(project.getStartDate().toString());
        // Bind other views in holder as needed

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onEditProject(project);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView projectNameTextView;
        TextView projectCategoryTextView;
        TextView projectStartDateTextView;
        ImageButton editButton;

        ProjectViewHolder(View itemView) {
            super(itemView);
            projectNameTextView = itemView.findViewById(R.id.projectNameTextView);
            projectCategoryTextView = itemView.findViewById(R.id.projectCategoryTextView);
            projectStartDateTextView = itemView.findViewById(R.id.projectStartDateTextView);
            editButton = itemView.findViewById(R.id.editButton);
        }
    }
}
