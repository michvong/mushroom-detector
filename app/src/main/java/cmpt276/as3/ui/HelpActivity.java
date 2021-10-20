package cmpt276.as3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import cmpt276.as3.R;

/*
    This activity represents the help menu of the game.
    It shows the author, game instructions, and credits for the drawable assets.
 */
public class HelpActivity extends AppCompatActivity {
    private ImageButton backButton;
    TextView courseLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((View view) -> openBackButton());

        courseLink = findViewById(R.id.aboutAuthorText);
        courseLink.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void openBackButton() {
        Intent intent = new Intent(HelpActivity.this, MainActivity.class);
        startActivity(intent);
    }
}