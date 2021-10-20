package cmpt276.as3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cmpt276.as3.R;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private Button optionsButton;
    private Button helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        startButton = findViewById(R.id.startButton);
        openStartMenu();

        optionsButton = findViewById(R.id.optionsButton);
        openOptionsMenu();

        helpButton = findViewById(R.id.helpButton);
        openHelpMenu();

        animateButtonSlide(startButton);
        animateButtonSlide(optionsButton);
        animateButtonSlide(helpButton);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }


    public void animateButtonSlide(Button button) {
        button.setAlpha(0f);
        button.setTranslationY(50);
        button.animate().alpha(1f).translationXBy(-50).setDuration(1000);
    }

    public void openStartMenu() {
        startButton.setOnClickListener((View view) -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        });
    }

    public void openOptionsMenu() {
        optionsButton.setOnClickListener((View view) -> {
            Intent intent = new Intent(MainActivity.this, OptionsActivity.class);
            startActivity(intent);
        });
    }

    public void openHelpMenu() {
        helpButton.setOnClickListener((View view) -> {
            Intent intent = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(intent);
        });
    }
}