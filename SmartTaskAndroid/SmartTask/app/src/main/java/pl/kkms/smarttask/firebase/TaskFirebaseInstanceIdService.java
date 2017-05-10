package pl.kkms.smarttask.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Damian on 2017-05-05.
 */

public class TaskFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "IdService";

    @Override
    public void onTokenRefresh() {
        // GET UPDATED TOKEN
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "New token: " + refreshedToken);
    }
}
