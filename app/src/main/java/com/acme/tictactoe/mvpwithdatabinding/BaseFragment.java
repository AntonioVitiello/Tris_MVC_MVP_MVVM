package com.acme.tictactoe.mvpwithdatabinding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vitiello Antonio on 24/03/2018.
 */

public abstract class BaseFragment  extends Fragment implements MVPContract.View {
    private static final String LOG_TAG = "BaseFragment";
    private static final String TAG_RETAINED_FRAGMENT = "FragmentRetainedFragment";
    private RetainedFragment mRetainedFragment;
    private MVPContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        // find the retained fragment on activity restarts
        FragmentManager fm = getFragmentManager();
        mRetainedFragment = (RetainedFragment) fm.findFragmentByTag(TAG_RETAINED_FRAGMENT);

        // create the fragment and data the first time
        if (mRetainedFragment == null) {
            mRetainedFragment = new RetainedFragment();
            fm.beginTransaction().add(mRetainedFragment, TAG_RETAINED_FRAGMENT).commit();
            setPresenter(mPresenter);
        }

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        // if this activity will not be recreated, ie. user is leaving it or the activity is otherwise finishing
        Log.d(LOG_TAG, "onPause: isRemoving() = " + isRemoving());
        if (isRemoving()) {
            // remove retained fragment object to perform its own cleanup
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().remove(mRetainedFragment).commit();
        }
    }

    public MVPContract.Presenter getPresenter() {
        return mRetainedFragment.getPresenter();
    }

    @Override
    public void setPresenter(MVPContract.Presenter presenter) {
        if(mRetainedFragment != null) {
            mRetainedFragment.setPresenter(presenter);
        } else {
            mPresenter = presenter;
        }
    }

}
