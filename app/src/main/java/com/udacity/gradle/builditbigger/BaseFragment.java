package com.udacity.gradle.builditbigger;

import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected InterstitialCallback mInterstitialCallback;
    protected String mResultString;

    //Callback after interstitial is dismissed
    public interface InterstitialCallback {
        void onPostInterstitial(String result);
    }

    protected abstract void postInter();

    public void showInterstitial(InterstitialCallback inter) {
        mInterstitialCallback = inter;
        mResultString = null;
        new EndpointsAsyncTask().execute(new EndpointsAsyncTask.PostEndpointTask() {
            @Override
            public void onPostExecute(String result) {
                mResultString = result;
                postInter();
            }
        });
    }
}
