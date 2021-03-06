package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.androjoker.AndrojokerActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.callbacks.JokeTaskCallback;
import com.udacity.gradle.builditbigger.tasks.JokeFetchTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener, JokeTaskCallback {

    public static final String TAG = MainActivityFragment.class.getSimpleName();

    @BindView(R.id.btn)
    Button button;
    @BindView(R.id.progress)
    ProgressBar progressBar;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        AdView mAdView = ButterKnife.findById(root, R.id.adView);
        if (!BuildConfig.PAID) {
            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);
        } else {
            mAdView.setVisibility(View.GONE);
        }
        return root;
    }

    @OnClick({R.id.btn})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                progressBar.setVisibility(View.VISIBLE);
                new JokeFetchTask(this).execute();
                break;
            default:
                Log.d(TAG, "click not implented");
        }
    }


    @Override
    public void showJoke(String joke) {
        Intent intent = new Intent(getActivity(), AndrojokerActivity.class);
        intent.putExtra("joke", joke);
        startActivity(intent);
        progressBar.setVisibility(View.GONE);
    }
}
