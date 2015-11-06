package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public final class FreeMainActivityFragment extends BaseFragment {

    private InterstitialAd mInterstitialAd;
    private boolean mInterShowing;

    public FreeMainActivityFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        prepareInterstitial();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Button btn = (Button)root.findViewById(R.id.joke_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).tellJoke();
            }
        });


        View viewFlavor = root.findViewById(R.id.adView);
        if(viewFlavor instanceof AdView) {
            AdView mAdView = (AdView) viewFlavor;
            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);
        }
        return root;
    }
    
    private void prepareInterstitial() {
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mInterShowing = false;
                requestNewInterstitial();
                //If the mResultString is null its still being fetched in which case allow the
                //postInter to handle the callBack.
                if(mResultString != null)
                    mInterstitialCallback.onPostInterstitial(mResultString);
            }
        });

        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void showInterstitial(InterstitialCallback callBack) {
        super.showInterstitial(callBack);
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterShowing = true;
        } else {
            requestNewInterstitial();
            mInterShowing = false;
        }
    }

    @Override
    protected void postInter() {
        //If inter isn't showing its been dismissed so fire callback.
        //If its still showing we need to let the ad callback handle the onPostInterstitial
        if(!mInterShowing)
            mInterstitialCallback.onPostInterstitial(mResultString);
    }
}
