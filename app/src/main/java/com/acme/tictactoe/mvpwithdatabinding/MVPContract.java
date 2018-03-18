package com.acme.tictactoe.mvpwithdatabinding;

import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;

/**
 * Created by Antonio on 12/02/2018.
 */

public interface MVPContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        void onResetSelected();

        void onClickCell(int row, int col);

        ObservableArrayMap<String, String> getCells();

        ObservableField<String> getWinner();

        ObservableField<String> getCurrentTurn();

        ObservableField<Boolean> getGameOver();
    }

}