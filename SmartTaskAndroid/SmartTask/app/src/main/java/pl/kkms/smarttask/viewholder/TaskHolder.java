package pl.kkms.smarttask.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pl.kkms.smarttask.R;


/**
 * Created by Damian on 2017-04-21.
 */

public class TaskHolder extends RecyclerView.ViewHolder{

    private TextView name;
    private TextView description;
    private TextView beginTime;
    private TextView endTime;
    private TextView priority;
    private TextView status;


    public TaskHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.task_name);
        description = (TextView) itemView.findViewById(R.id.task_decription);
        beginTime = (TextView) itemView.findViewById(R.id.task_begin_time);
        endTime = (TextView) itemView.findViewById(R.id.task_end_time);
        priority = (TextView) itemView.findViewById(R.id.task_priority);
        status = (TextView) itemView.findViewById(R.id.task_status);
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getDescription() {
        return description;
    }

    public void setDescription(TextView description) {
        this.description = description;
    }

    public TextView getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(TextView beginTime) {
        this.beginTime = beginTime;
    }

    public TextView getEndTime() {
        return endTime;
    }

    public void setEndTime(TextView endTime) {
        this.endTime = endTime;
    }

    public TextView getPriority() {
        return priority;
    }

    public void setPriority(TextView priority) {
        this.priority = priority;
    }

    public TextView getStatus() {
        return status;
    }

    public void setStatus(TextView status) {
        this.status = status;
    }
}
