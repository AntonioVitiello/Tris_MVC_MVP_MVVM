package com.acme.tictactoe.mvp.presenter;

import com.acme.tictactoe.mvp.MVPContract;
import com.acme.tictactoe.mvp.model.Board;
import com.acme.tictactoe.mvp.model.Player;

public class TicTacToePresenter implements MVPContract.Presenter {

    private MVPContract.View view;
    private Board model;

    public TicTacToePresenter(MVPContract.View view) {
        this.view = view;
        this.model = new Board();
        view.showPlayerTurn(model.getCurrentTurn().toString());
    }

    @Override
    public void playerMove(int row, int col) {
        Player playerThatMoved = model.mark(row, col);

        if(playerThatMoved != null) {
            view.setButtonText(row, col, playerThatMoved.toString());

            if (model.getWinner() != null) {
                view.showWinner(playerThatMoved.toString());
            } else {
                if(model.isGameOver()){
                    view.showGameOver();
                } else {
                    view.showPlayerTurn(model.getCurrentTurn().toString());
                }
            }
        }
    }

    @Override
    public void onResetSelected() {
        view.clearButtons();
        view.showPlayerTurn(model.getCurrentTurn().toString());
        model.restart();
    }

}
