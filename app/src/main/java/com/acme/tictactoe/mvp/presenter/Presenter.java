package com.acme.tictactoe.mvp.presenter;


public interface Presenter {

    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();

}
