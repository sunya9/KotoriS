package net.unsweets.kotoris.fragments;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by mugen_000 on 2015/08/18.
 */
public class CacheFragment extends Fragment{
    private static final String TAG = CacheFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    
}
