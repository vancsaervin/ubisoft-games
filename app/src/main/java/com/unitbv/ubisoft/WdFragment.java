package com.unitbv.ubisoft;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class WdFragment extends Fragment {

    // declare objects
    private ListView list = null;
    private GameAdapter adapter = null;
    private XMLData data = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wd, container, false);
        // prepare objects
        data = new XMLData(view.getContext());
        list = (ListView) view.findViewById(R.id.listView);

        Game[] wdGames = data.getFranchiseGame("2");

        adapter = new GameAdapter(
                Objects.requireNonNull(getContext()),
                R.layout.list_item,
                wdGames
        );

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // make an intent and a bundle
                Intent intent = new Intent(getActivity(), GameActivity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("game", data.getGameFromFranchise("2", position));
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        return view;
    }
}
