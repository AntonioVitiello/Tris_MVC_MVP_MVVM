package com.acme.tictactoe.mvvm.view;

/**
 * Created by Antonio on 13/02/2018.
 */

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.acme.tictactoe.R;
import com.acme.tictactoe.databinding.MvvmTictactoeBinding;
import com.acme.tictactoe.mvvm.viewmodel.TicTacToeViewModel;

public class TicTacToeActivity extends AppCompatActivity {

    //non usare l'interfaccia MVVMContract.ViewModel perchè mvvm_tictactoe.xml accede alle sue variabili direttamente
    //come ad esempio viewModel.cells,  a meno di non aggiungere TUTTI i getter e setter ovviamente
    private TicTacToeViewModel viewModel = new TicTacToeViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Android Data Binding
        MvvmTictactoeBinding binding = DataBindingUtil.setContentView(this, R.layout.mvvm_tictactoe);

        // Valorizing myViewModel data in mvvm_tictactoe.xml :
        // <data> <variable name="myViewModel" type="com.acme.tictactoe.mvvm.viewmodel.TicTacToeViewModel" /> ...
        binding.setMyViewModel(viewModel);

        viewModel.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mvvm_menu_tictactoe, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                viewModel.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}