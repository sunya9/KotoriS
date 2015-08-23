package net.unsweets.kotoris;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by mugen_000 on 2015/08/23.
 */
public class PrefManager {
    private static final String TAG = PrefManager.class.getSimpleName();
    private static PrefManager mInst;
    private final SharedPreferences mPreferences;
    private final SharedPreferences.Editor mEdit;
    private final Context mContext;

    private PrefManager(Context ctx) {
        mContext = ctx;
        mPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        mEdit = mPreferences.edit();
    }

    public static PrefManager getInstance(Context ctx) {
        if (mInst == null) {
            mInst = new PrefManager(ctx);
        }
        return mInst;
    }

    public boolean existsAccount() {
        return mPreferences.getBoolean(mContext.getString(R.string.exist_account_key), false);
    }

    public boolean setExistsAccount(boolean b) {
        mEdit.putBoolean(mContext.getString(R.string.exist_account_key), b);
        return mEdit.commit();
    }

}
