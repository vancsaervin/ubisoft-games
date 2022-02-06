package com.unitbv.ubisoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    // declare objects
    private TextView gameTitleTextView = null;
    // private ImageView gameIconImageView = null;
    private TextView releaseDateTextView = null;
    private TextView longDescriptionTextView = null;
    private LinearLayout gallery = null;
    private Button webButton = null;

    private Game data = null;
    private Intent intent = null;
    private Bundle bundle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) { }
        setContentView(R.layout.activity_details);

        // wire objects with widgets
        gameTitleTextView = findViewById(R.id.gameTitle);
        // gameIconImageView = findViewById(R.id.gameIcon);
        gallery = findViewById(R.id.gallery);
        releaseDateTextView = findViewById(R.id.releaseDateTextView);
        longDescriptionTextView = findViewById(R.id.longDescriptionTextView);
        webButton = findViewById(R.id.webButton);

        // make data
        final Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();

        data = (Game) bundle.getSerializable("game");

        // populate widgets with data
        gameTitleTextView.setText(data.getName());

        /*String imgName = data.getImage();
        imgName = imgName.substring(0, imgName.indexOf("."));
        gameIconImageView.setImageResource(this.getResources().getIdentifier(imgName, "drawable", this.getPackageName()));*/

        LayoutInflater inflater = LayoutInflater.from(this);
        XMLData photo = null;

        for(int i=0; i<4; i++) {
            View view = inflater.inflate(R.layout.item, gallery, false);
            photo = new XMLData(view.getContext());

            ImageView imageView = view.findViewById(R.id.imageView2);

            final String[] images = photo.parseGallery(data);
            imageView.setImageResource(this.getResources().getIdentifier(images[i].substring(0, images[i].indexOf(".")), "drawable", this.getPackageName()));

            gallery.addView(view);

            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent fullScreenIntent = new Intent(DetailsActivity.this, FullScreenImageActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("game", data);
                    bundle.putSerializable("images", images);
                    bundle.putSerializable("index", finalI);
                    fullScreenIntent.putExtras(bundle);

                    startActivity(fullScreenIntent);
                }
            });
        }

        releaseDateTextView.setText("Release date: " + data.getReleaseDate());
        longDescriptionTextView.setText("Description:\n\n\t\t\t" + data.getShortDescription() + data.getLongDescription());

        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(DetailsActivity.this, WebActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("game", data);
                webIntent.putExtras(bundle);

                startActivity(webIntent);
            }
        });
    }
}