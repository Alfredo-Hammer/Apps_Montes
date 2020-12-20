package com.hammer67.tutorialrecylclerviewjava.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.hammer67.tutorialrecylclerviewjava.R;
import com.hammer67.tutorialrecylclerviewjava.adapters.ItemCardAdapter;
import com.hammer67.tutorialrecylclerviewjava.adapters.ItemGridAdapter;
import com.hammer67.tutorialrecylclerviewjava.adapters.ItemListAdapter;
import com.hammer67.tutorialrecylclerviewjava.models.Mountain;
import com.hammer67.tutorialrecylclerviewjava.models.MountainsData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Mountain> list;

    final String STATE_TITLE = "state_title";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";

    int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        list = new ArrayList<>();
        //list.addAll(MountainsData.getListData());
        //showRecyclerViewList();

        if (savedInstanceState == null) {
            setActionBarTitle("Modo lista");
            list.addAll(MountainsData.getListData());
            showRecyclerViewList();
            mode = R.id.action_list;
        } else {
            String stateTitle = savedInstanceState.getString(STATE_TITLE);
            ArrayList<Mountain> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            int stateMode = savedInstanceState.getInt(STATE_MODE);
            setActionBarTitle(stateTitle);
            list.addAll(stateList);
            setMode(stateMode);
        }
    }

    //Menu lateral
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void setMode(int itemId) {
        String title = null;
        switch (itemId) {
            case R.id.action_list:
                title = "Modo Lista";
                showRecyclerViewList();
                break;

            case R.id.action_grid:
                title = "Modo Grid";
                showRecyclerViewGrid();
                break;

            case R.id.action_cardview:
                title = "Modo CardView";
                showRecyclerCardView();
                break;
        }

        mode = itemId;
        setActionBarTitle(title);
    }

    private void showRecyclerCardView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemCardAdapter itemCardAdapter = new ItemCardAdapter(this);
        itemCardAdapter.setListMountain(list);
        recyclerView.setAdapter(itemCardAdapter);
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void showRecyclerViewGrid() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ItemGridAdapter itemGridAdapter = new ItemGridAdapter(this);
        itemGridAdapter.setListMountain(list);
        recyclerView.setAdapter(itemGridAdapter);
    }

    private void showRecyclerViewList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemListAdapter listAdapter = new ItemListAdapter(this);
        listAdapter.setListMountains(list);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
        outState.putParcelableArrayList(STATE_LIST, list);
        outState.putInt(STATE_MODE, mode);
    }
}