package com.acme.tictactoe.mvvm.viewmodel;

/**
 * Created by Antonio on 13/02/2018.
 */

import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;

import com.acme.tictactoe.mvvm.MVVMContract;
import com.acme.tictactoe.mvvm.model.Board;
import com.acme.tictactoe.mvvm.model.Player;

public class TicTacToeViewModel implements MVVMContract.ViewModel {

    private Board model;

    public final ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    public final ObservableField<String> winner = new ObservableField<>();

    public TicTacToeViewModel() {
        model = new Board();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void onResetSelected() {
        model.restart();
        winner.set(null);
        cells.clear();
    }

    /**
     * Button onClick event in mvvm_tictactoe.xml
     * @param row
     * @param col
     */
    public void onClickCell(int row, int col) {
        Player playerThatMoved = model.mark(row, col);
        cells.put("" + row + col, playerThatMoved == null ? null : playerThatMoved.toString());
        winner.set(model.getWinner() == null ? null : model.getWinner().toString());
    }

}
