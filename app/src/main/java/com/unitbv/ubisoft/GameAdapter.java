package com.unitbv.ubisoft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GameAdapter extends ArrayAdapter<Game> {
    Context context;
    int resource;
    Game[] gameList;

    public GameAdapter(@NonNull Context context, int resource, Game[] gameList) {
        super(context, resource, gameList);

        this.context = context;
        this.resource = resource;
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_item, null);

        ImageView gameIcon = view.findViewById(R.id.game_icon);
        TextView gameTitle = view.findViewById(R.id.game_title);
        TextView gameReleaseDate = view.findViewById(R.id.releaseDate);

        Game game = gameList[position];

        String imgName = game.getImage();
        imgName = imgName.substring(0, imgName.indexOf("."));
        gameIcon.setImageResource(context.getResources().getIdentifier(imgName, "drawable", context.getPackageName()));
        gameTitle.setText(game.getName());
        gameReleaseDate.setText(game.getReleaseDate());

        return view;
    }
}
