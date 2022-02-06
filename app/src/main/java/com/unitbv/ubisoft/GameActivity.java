package com.unitbv.ubisoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    // declare objects
    private TextView gameTitleTextView = null;
    private ImageView gameIconImageView = null;
    private TextView releaseDateTextView = null;
    private TextView shortDescriptionTextView = null;
    private Button showMoreButton = null;

    private Game data = null;
    private Intent intent = null;
    private Bundle bundle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) { }
        setContentView(R.layout.activity_game);

        // wire objects with widgets
        gameTitleTextView = findViewById(R.id.gameTitle);
        gameIconImageView = findViewById(R.id.gameIcon);
        releaseDateTextView = findViewById(R.id.releaseDateTextView);
        shortDescriptionTextView = findViewById(R.id.shortDescriptionTextView);
        showMoreButton = findViewById(R.id.showMore);

        // make data
        final Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();

        data = (Game) bundle.getSerializable("game");

        // populate widgets with data
        gameTitleTextView.setText(data.getName());

        String imgName = data.getImage();
        imgName = imgName.substring(0, imgName.indexOf("."));
        gameIconImageView.setImageResource(this.getResources().getIdentifier(imgName, "drawable", this.getPackageName()));

        releaseDateTextView.setText("Release date: " + data.getReleaseDate());
        shortDescriptionTextView.setText(data.getShortDescription());

        showMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(GameActivity.this, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("game", data);
                detailIntent.putExtras(bundle);

                startActivity(detailIntent);
            }
        });
    }
}