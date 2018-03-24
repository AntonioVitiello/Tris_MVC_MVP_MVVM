package com.acme.tictactoe.mvpwithdatabinding.presenter;

import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.acme.tictactoe.R;
import com.acme.tictactoe.mvpwithdatabinding.MVPContract;
import com.acme.tictactoe.mvpwithdatabinding.model.Board;
import com.acme.tictactoe.mvpwithdatabinding.model.Player;
import com.acme.tictactoe.mvpwithdatabinding.view.TicTacToeFragment;

public class PresenterTicTacToe implements MVPContract.Presenter {
    private static final String LOG_TAG = "PresenterTicTacToe";
    private static final String BUTTONS_FRAGMENT_TAG = "buttons_fragment";

    private final ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    private final ObservableField<String> winner = new ObservableField<>();
    private final ObservableField<String> currentTurn = new ObservableField<>();
    private final ObservableField<Boolean> gameOver = new ObservableField<>(false);

    private MVPContract.View mvpView;
    private Board model;


    public PresenterTicTacToe(MVPContract.View view) {
        this.mvpView = view;
        this.model = new Board();
    }

    @Override
    public void start() {
        Log.d("AAA", "start: gameOver = " + gameOver.get() + ", currentTurn = " + currentTurn.get() + ", winner = " + winner.get());

        if(!model.isGameOver()) {
            currentTurn.set(model.getCurrentTurn().toString());
        }
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
     *
     * @param row
     * @param col
     */
    public void onClickCell(int row, int col) {
        Player playerThatMoved = model.mark(row, col);

        if (playerThatMoved != null) {

            // Player(OX) moved: update the observable map with the current model state
            String cellsKey = Integer.toString(row) + Integer.toString(col); //example: cellsKey="00" or cellsKey="22"...
            String cellsValue = playerThatMoved == null ? null : playerThatMoved.toString();
            cells.put(cellsKey, cellsValue);

            if (model.getWinner() != null) {
                // Player(OX) Win this game!
                winner.set(model.getWinner().toString());
                currentTurn.set(null);
                gameOver.set(Boolean.TRUE);
            } else if (model.isGameOver()) {
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

    public ObservableArrayMap<String, String> getCells() {
        return cells;
    }

    public ObservableField<String> getWinner() {
        return winner;
    }

    public ObservableField<String> getCurrentTurn() {
        return currentTurn;
    }

    public ObservableField<Boolean> getGameOver() {
        return gameOver;
    }

    @Override
    public MVPContract.View nextFragment(FragmentManager fragmentManager) {
        TicTacToeFragment ticTacToeFragment = (TicTacToeFragment)fragmentManager.findFragmentByTag(BUTTONS_FRAGMENT_TAG);
        if(ticTacToeFragment == null){
            ticTacToeFragment = new TicTacToeFragment();
            ticTacToeFragment.setPresenter(this);
            fragmentManager.beginTransaction()
                    .add(R.id.buttons_fragment, ticTacToeFragment, BUTTONS_FRAGMENT_TAG)
                    .addToBackStack(BUTTONS_FRAGMENT_TAG)
                    .commit();
        }
        mvpView = ticTacToeFragment;
        return mvpView;
    }

}
