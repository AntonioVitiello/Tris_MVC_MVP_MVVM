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
    public final ObservableField<String> currentTurn = new ObservableField<>();
    public final ObservableField<Boolean> gameOver = new ObservableField<>();


    public TicTacToeViewModel() {
        model = new Board();
    }

    @Override
    public void onCreate() {
        currentTurn.set(model.getCurrentTurn().toString());
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

    /**
     * RESET event raised by OptionsMenu
     */
    public void onResetSelected() {
        model.restart();
        winner.set(null);
        currentTurn.set(model.getCurrentTurn().toString());
        cells.clear();
    }

    /**
     * Button onClick event in mvvm_tictactoe.xml
     * @param row
     * @param col
     */
    public void onClickCell(int row, int col) {
        Player playerThatMoved = model.mark(row, col);

        if(playerThatMoved != null) {

            // Player(OX) moved: update the observable map with the current model state
            String cellsKey = Integer.toString(row) + Integer.toString(col); //example: cellsKey="00" or cellsKey="22"...
            String cellsValue = playerThatMoved == null ? null : playerThatMoved.toString();
            cells.put(cellsKey, cellsValue);

            if (model.getWinner() != null) {
                // Player(OX) Win this game!
                winner.set(model.getWinner().toString());
                currentTurn.set(null);
            } else if(model.isGameOver()){
                    // GAME OVER, Press RESET to start a new game: update the observable fields with the current model state
                    currentTurn.set(null);
                    gameOver.set(Boolean.TRUE);
                } else {
                    // Player(OX) Now is your turn: update the observable fields with the current model state
                    gameOver.set(Boolean.FALSE);
                    winner.set(null);
                    currentTurn.set(model.getCurrentTurn().toString());
                }
            }
    }

}
