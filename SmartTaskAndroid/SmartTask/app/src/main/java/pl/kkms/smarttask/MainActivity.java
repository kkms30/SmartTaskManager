package pl.kkms.smarttask;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kkms.smarttask.adapter.TaskAdapter;
import pl.kkms.smarttask.eventbus.UserEvent;
import pl.kkms.smarttask.model.Task;
import pl.kkms.smarttask.model.User;
import pl.kkms.smarttask.retrofit.WebserviceUtil;
import pl.kkms.smarttask.util.SharedPrefs;

public class MainActivity extends AppCompatActivity {

    private User user;

    @BindView(R.id.tasks_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.user_code)
    TextView userCodeTextView;
    @BindView(R.id.status_change)
    EditText statusChangeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SharedPreferences preferences = getSharedPreferences(SharedPrefs.SMART_TASK_SHARED_PREFS, MODE_PRIVATE);
        String userCode = preferences.getString(SharedPrefs.USER_CODE, null);
        WebserviceUtil.callForUserByUserCode(userCode);
    }

    @Subscribe
    public void onUserGetFinished(UserEvent userEvent) {
        initUI();
    }

    private void initUI() {
//        List<Task> taskList = new ArrayList<>();
//        User user = new User();
//        user.setUserCode("783054");
//
//        Task task = new Task();
//        task.setName("Test");
//        task.setDescription("Test");
//        task.setBeginTime("Test");
//        task.setEndTime("Test");
//        task.setPriority(1);
//        task.setStatus("Test");
//
//        taskList.add(task);
//        user.setTasks(taskList);

        user = UserHandler.getInstance().getUser();

        userCodeTextView.setText(user.getName() + "   " + user.getUserCode());

        TaskAdapter taskAdapter = new TaskAdapter(user.getTasks(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);
    }

    @OnClick(R.id.confirm_status_change)
    public void onCLickChangeStatus() {
        String text = statusChangeEditText.getText().toString();

        for (Task task : user.getTasks()) {
            WebserviceUtil.callForChangeTaskStatus(task.getIdTask(), text);
        }
    }

    @OnClick(R.id.refresh)
    public void onClickRefresh() {
        WebserviceUtil.callForUserByUserCode("783054");
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
