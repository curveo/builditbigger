package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment {

    private InterstitialCallback mCallback;
    private RelativeLayout mBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mBar = (RelativeLayout) getActivity().findViewById(R.id.progress_bar);
        Button btn = (Button)root.findViewById(R.id.joke_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).tellJoke();
            }
        });
        return root;
    }

    @Override
    public void showInterstitial(InterstitialCallback callBack) {
        super.showInterstitial(callBack);
        mBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void postInter() {
        mBar.setVisibility(View.GONE);
        mCallback.onPostInterstitial(mResultString);
    }
}