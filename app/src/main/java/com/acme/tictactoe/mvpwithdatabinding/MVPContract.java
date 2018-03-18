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
        public void onResetSelected();
        public void onClickCell(int row, int col);

        public ObservableArrayMap<String, String> getCells();
        public ObservableField<String> getWinner();
        public ObservableField<String> getCurrentTurn();
        public ObservableField<Boolean> getGameOver();
    }

}