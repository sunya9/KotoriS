package net.unsweets.kotoris.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by mugen_000 on 2015/08/22.
 */
public class BaseFragment extends Fragment{
    private static final String TAG = BaseFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
