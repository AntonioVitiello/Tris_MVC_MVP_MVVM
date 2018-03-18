package com.acme.tictactoe.mvpwithdatabinding;

/**
 * Created by Antonio on 18/03/2018.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

}
