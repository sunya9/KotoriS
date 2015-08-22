package net.unsweets.kotoris.dialogs;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;

/**
 * Created by mugen_000 on 2015/08/20.
 */
abstract public class BaseDialog extends DialogFragment{
    private static final String TAG = BaseDialog.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    abstract public void show();
    abstract public BaseDialog newInstance();
}
