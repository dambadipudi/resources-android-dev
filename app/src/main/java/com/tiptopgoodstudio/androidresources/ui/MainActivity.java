package com.tiptopgoodstudio.androidresources.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.tiptopgoodstudio.androidresources.R;
import com.tiptopgoodstudio.androidresources.db.entity.Resource;
import com.tiptopgoodstudio.androidresources.db.entity.Resources;
import com.tiptopgoodstudio.androidresources.db.entity.Topic;
import com.tiptopgoodstudio.androidresources.ui.adapters.SitckyNoteAdapter;

import java.util.ArrayList;
import java.util.List;


/*
* Updated on 4/20/2018 by Olga Agafonova
* Added a Grid Layout that displays a RecyclerView (which displays the sticky notes)
*
* Moved the firebase code from MainActivity to the Repository by Divya on 4/20/2018
* */

public class MainActivity extends AppCompatActivity implements SitckyNoteAdapter.ResourceClickListener {

    private RecyclerView mRecyclerView;
    private SitckyNoteAdapter mSitckyNoteAdapter;
    private ProgressBar mLoadingIndicator;
    private TextView mErrorMessage;
    private List<Topic> topicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_stickynotes);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mErrorMessage = (TextView) findViewById(R.id.main_error_message_display);

        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mSitckyNoteAdapter = new SitckyNoteAdapter(this);
        mRecyclerView.setAdapter(mSitckyNoteAdapter);

        topicList = new ArrayList<Topic>();

        mSitckyNoteAdapter.setTopicData(topicList); // TODO - Replace this with data from ViewModel

    }

    /**
     * This method will be called when the sticky note is clicked
     * This method creates an Explicit Intent to ResourceListActivity
     * and sends the topic as an Intent extra
     *
     */

    public void onStickyClicked(String topic){
        Intent intent = new Intent(this, ResourceListActivity.class);

        intent.putExtra(Intent.EXTRA_TEXT, topic);

        startActivity(intent);

    }

    @Override
    public void onTopicItemClick(String data) {

        onStickyClicked(data);

    }

}
