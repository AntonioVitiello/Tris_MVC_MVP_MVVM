package com.acme.tictactoe.mvvm;

/**
 * Created by Antonio on 13/02/2018.
 */

public interface MVVMContract {
    public interface ViewModel {
        void onCreate();
        void onPause();
        void onResume();
        void onDestroy();
    }

}
