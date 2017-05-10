package pl.kkms.smarttask.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kkms.smarttask.MainActivity;
import pl.kkms.smarttask.R;
import pl.kkms.smarttask.util.SharedPrefs;

public class PinActivity extends AppCompatActivity {

    @BindView(R.id.pin_prompt_text)
    TextView pinPromtTextView;
    @BindView(R.id.pin_number)
    TextView pinNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        ButterKnife.bind(this);

        if (checkUserHasPin()) {
            pinPromtTextView.setText("Podaj pin");
        } else {
            pinPromtTextView.setText("Nadaj osobity kod pin");
        }
    }

    @OnClick(R.id.confirm_pin)
    public void onClickConfirmPin(){
        String pin = pinNumberTextView.getText().toString();
        if (checkUserHasPin()) {
            String pinFromSharedPrefs = getSharedPreferences(SharedPrefs.SMART_TASK_SHARED_PREFS, MODE_PRIVATE).getString(SharedPrefs.PIN_CODE, null);
            if (pin.equals(pinFromSharedPrefs)) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                pinPromtTextView.setText("Podano nieprawidłowy pin");
            }
        } else {
            SharedPreferences.Editor preferences = getSharedPreferences(SharedPrefs.SMART_TASK_SHARED_PREFS, MODE_PRIVATE).edit();
            preferences.putString(SharedPrefs.PIN_CODE, pin);
            preferences.apply();

            // TODO WYWALIC I POZMIENIAC
            pinPromtTextView.setText("Podaj pin");
            Toast.makeText(this, "Nadano kod pin", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.reset_pin)
    public void onClickResetPin() {
        SharedPreferences preferences = getSharedPreferences(SharedPrefs.SMART_TASK_SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean checkUserHasPin(){
        SharedPreferences preferences = getSharedPreferences(SharedPrefs.SMART_TASK_SHARED_PREFS, MODE_PRIVATE);
        String pin = preferences.getString(SharedPrefs.PIN_CODE, null);
        if (pin == null) {
            return false;
        } else return true;
    }
}

