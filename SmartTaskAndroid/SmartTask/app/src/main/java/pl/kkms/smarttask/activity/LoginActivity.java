package pl.kkms.smarttask.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kkms.smarttask.R;
import pl.kkms.smarttask.util.SharedPrefs;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//        getToken();

        validateUser();
    }

    private void getToken() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token: " + token);
    }

    private void validateUser() {
        // TODO wydzielic
        SharedPreferences preferences = getSharedPreferences(SharedPrefs.SMART_TASK_SHARED_PREFS, MODE_PRIVATE);
        String userCode = preferences.getString(SharedPrefs.USER_CODE, null);
        if (userCode != null) {
            Intent intent = new Intent(this, PinActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.init_qr_scan)
    public void onClickScanQR() {
        // TODO wywalic
        getToken();

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setBarcodeImageEnabled(true);
        integrator.setPrompt("");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Anulowano skanowanie", Toast.LENGTH_LONG).show();
            } else {
                String userCode = result.getContents();
                Toast.makeText(this, "Zeskanowano: " + userCode, Toast.LENGTH_LONG).show();

                // TODO wydzielic
                SharedPreferences.Editor preferences = getSharedPreferences(SharedPrefs.SMART_TASK_SHARED_PREFS, MODE_PRIVATE).edit();
                preferences.putString(SharedPrefs.USER_CODE, userCode);
                preferences.apply();

                Intent intent = new Intent(this, PinActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
