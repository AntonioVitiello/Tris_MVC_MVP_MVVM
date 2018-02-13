package com.acme.tictactoe.mvvm.model;

/**
 * Created by Antonio on 13/02/2018.
 */

public class Cell {

    private Player value;

    public Player getValue() {
        return value;
    }

    public void setValue(Player value) {
        this.value = value;
    }
}