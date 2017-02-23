package com.example.xxd.sunset;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by 肖显东 on 2017/2/10.
 */

public class SunsetFragment extends Fragment
{
    private View mSceneView;
    private View mSunView;
    private View mSkyView;
    private int mBlueSkyColor;
    private int mSunsetSkyColor;
    private int mNightSkyColor;
    public static SunsetFragment newInstance()
    {
        return new SunsetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sunset,container,false);
        mSceneView = view;
        mSkyView = view.findViewById(R.id.sky);
        mSunView = view.findViewById(R.id.sun);
        Resources resources = getResources();
        mBlueSkyColor = resources.getColor(R.color.blue_sky);
        mSunsetSkyColor = resources.getColor(R.color.sunset_sky);
        mNightSkyColor = resources.getColor(R.color.night_sky);
        mSceneView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startAnimation();
            }
        });
        return view;
    }
    public void startAnimation()
    {
        float sunYStart = mSunView.getTop();
        float sunYEnd = mSkyView.getHeight();
//        sun
        ObjectAnimator sunsetAnimator = ObjectAnimator
                .ofFloat(mSunView,"y",sunYStart,sunYEnd)
                .setDuration(6000);
        sunsetAnimator.setInterpolator(new AccelerateInterpolator());
//        sky
        ObjectAnimator skyAnimator = ObjectAnimator
                .ofInt(mSkyView,"backgroundColor",mBlueSkyColor,mSunsetSkyColor)
                .setDuration(6000);
        skyAnimator.setEvaluator(new ArgbEvaluator());
//        sunsetAnimator.start();
//        skyAnimator.start();
        ObjectAnimator nightAnimator = ObjectAnimator
                .ofInt(mSkyView,"backgroundColor",mSunsetSkyColor,mNightSkyColor);
        nightAnimator.setEvaluator(new ArgbEvaluator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(sunsetAnimator).with(skyAnimator).before(nightAnimator);
        animatorSet.start();
    }
}
