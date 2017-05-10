package pl.kkms.smarttask.retrofit;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.net.HttpURLConnection;

import pl.kkms.smarttask.UserHandler;
import pl.kkms.smarttask.database.dao.UserDao;
import pl.kkms.smarttask.eventbus.UserEvent;
import pl.kkms.smarttask.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Damian on 2017-04-20.
 */

public class WebserviceUtil {
    private static final String TAG = "WABSERVICE_UTIL";

    private static SmartTaskService smartTaskService = ServiceGenerator.getSmartTaskService();

    //  USERS

    public static void callForUserByUserCode(final String userCode) {

        Call<User> call = smartTaskService.getUserByUserCode(userCode);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "USER CALL RESPONSE CODE : " + response.code());
                if(response.code() == HttpURLConnection.HTTP_OK) {
                    long id = new UserDao().insertObject(response.body());

                    UserHandler userHandler = UserHandler.getInstance();
                    userHandler.setUser(response.body());

                    EventBus.getDefault().post(new UserEvent());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "USER RESPONSE FAILURE : " + t.toString());
            }
        });
    }

    public static void callForChangeTaskStatus(int idTask, String status){
        Call<Void> call = smartTaskService.changeTaskStatus(idTask, status);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "TASK STATUS CALL RESPONSE CODE : " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "TASK STATUS CALL FAILURE : " + t.toString());
            }
        });
    }
}
