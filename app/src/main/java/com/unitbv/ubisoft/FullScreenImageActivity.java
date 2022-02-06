package com.unitbv.ubisoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

public class FullScreenImageActivity extends AppCompatActivity {

    private ImageView image = null;
    private Game data = null;
    private String[] images = null;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) { }
        setContentView(R.layout.activity_full_screen_image);

        image = findViewById(R.id.imageView3);

        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();

        data = (Game) bundle.getSerializable("game");
        images = (String[]) bundle.getSerializable("images");
        index = (int) bundle.getSerializable("index");

        image.setImageResource(this.getResources().getIdentifier(images[index].substring(0, images[index].indexOf(".")), "drawable", this.getPackageName()));
    }
}