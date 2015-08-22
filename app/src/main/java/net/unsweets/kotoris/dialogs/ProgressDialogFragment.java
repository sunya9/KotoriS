package net.unsweets.kotoris.dialogs;

import android.os.Bundle;

/**
 * Created by mugen_000 on 2015/08/22.
 */
public class ProgressDialogFragment extends BaseDialog {
    private static final String TAG = ProgressDialogFragment.class.getSimpleName();


    private ProgressDialogFragment() {

    }

    @Override
    public void show() {

    }

    private enum key{
        mesId;
    }

    static public BaseDialog newInstance(int mesId) {
        ProgressDialogFragment fragment = new ProgressDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(key.mesId.name(), mesId);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments != null){

        }

        new ProgressDialogFragment
    }
}
