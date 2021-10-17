package cmpt276.as3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cmpt276.as3.R;

public class WelcomeActivity extends AppCompatActivity {
    private static final int DELAY_TIME_IN_MILLISECONDS = 4000;
    private Button continueButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener((View view) -> openMainActivity());

        new Handler().postDelayed(() -> {
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            openMainActivity();
        }, DELAY_TIME_IN_MILLISECONDS);
    }

    public void openMainActivity() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
