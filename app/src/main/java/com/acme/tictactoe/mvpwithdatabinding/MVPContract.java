package com.acme.tictactoe.mvpwithdatabinding;

import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;

/**
 * Created by Antonio on 12/02/2018.
 */

public interface MVPContract {
    interface View {
        void setPresenter(Presenter presenter);
    }

    interface Presenter {
        void onResetSelected();

        void onClickCell(int row, int col);

        void start();

        ObservableArrayMap<String, String> getCells();

        ObservableField<String> getWinner();

        ObservableField<String> getCurrentTurn();

        ObservableField<Boolean> getGameOver();
    }

}