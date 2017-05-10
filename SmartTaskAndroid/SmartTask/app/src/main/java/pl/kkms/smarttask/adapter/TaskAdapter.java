package pl.kkms.smarttask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.kkms.smarttask.R;
import pl.kkms.smarttask.model.Task;
import pl.kkms.smarttask.viewholder.TaskHolder;

/**
 * Created by Damian on 2017-04-21.
 */

public class TaskAdapter extends RecyclerView.Adapter {

    private List<Task> taskList;
    private Context context;

    public TaskAdapter(List<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_task, parent, false);

        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TaskHolder taskHolder = (TaskHolder) holder;
        Task task = taskList.get(position);

        taskHolder.getName().setText(task.getName());
        taskHolder.getDescription().setText(task.getDescription());
        taskHolder.getBeginTime().setText(task.getBeginTime());
        taskHolder.getEndTime().setText(task.getEndTime());
        taskHolder.getPriority().setText(Integer.toString(task.getPriority()));
        taskHolder.getStatus().setText(task.getStatus());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
