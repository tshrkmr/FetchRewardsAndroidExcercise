package edu.tkumar.fetchrewardsandroidexcercise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private final List<GroupData> groupList = new ArrayList<>();
    private final List<Data> listIdOneItems = new ArrayList<>();
    private final List<Data> listIdTwoItems = new ArrayList<>();
    private final List<Data> listIdThreeItems = new ArrayList<>();
    private final List<Data> listIdFourItems = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.mainProgressBar);

        setUpSwipeRefreshLayout();

        checkDataConnection();
    }

    private void setUpSwipeRefreshLayout(){
        swipeRefreshLayout = findViewById(R.id.mainSwipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void checkDataConnection(){
        if(!checkNetworkConnection()){
            showErrorDialog("No Internet Connection!");
            return;
        }
        downloadData();
    }

    private void downloadData(){
        progressBar.setVisibility(View.VISIBLE);
        DataDownloaderRunnable dataDownloaderRunnable = new DataDownloaderRunnable(this);
        new Thread(dataDownloaderRunnable).start();
    }

    public void updateListIdOne(Data data){
        listIdOneItems.add(data);
        Collections.sort(listIdOneItems);
    }

    public void updateListIdTwo(Data data){
        listIdTwoItems.add(data);
        Collections.sort(listIdTwoItems);
    }

    public void updateListIdThree(Data data){
        listIdThreeItems.add(data);
        Collections.sort(listIdThreeItems);
    }

    public void updateListIdFour(Data data){
        listIdFourItems.add(data);
        Collections.sort(listIdFourItems);
    }

    public void updateGroupedData(){

        String groupNameFour = "4";
        groupList.add(new GroupData(groupNameFour, listIdFourItems));

        String groupNameTwo = "2";
        groupList.add(new GroupData(groupNameTwo, listIdOneItems));

        String groupNameOne = "1";
        groupList.add(new GroupData(groupNameOne, listIdTwoItems));

        String groupNameThree = "3";
        groupList.add(new GroupData(groupNameThree, listIdThreeItems));

        Collections.sort(groupList);
    }

    public void setUpRecyclerView(){
        progressBar.setVisibility(View.INVISIBLE);
        RecyclerView mainRecyclerView = findViewById(R.id.mainRecyclerView);
        MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(groupList);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public void showErrorDialog(String issue){
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Download Failed");
        builder.setMessage(String.format("%s \n Swipe Refresh Later", issue));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onRefresh() {
        checkDataConnection();
        swipeRefreshLayout.setRefreshing(false);
    }
}