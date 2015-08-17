package net.unsweets.kotoris.model;

import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by mugen_000 on 2015/08/17.
 */

@Table(name = "accounts", id = BaseColumns._ID)
public class Account extends Model {
    private static final String TAG = Account.class.getSimpleName();

    @Column(name = "user_id")
    public String userId;

    @Column(name = "screen_name")
    public String screenName;

    @Column(name = "handle_name")
    public String handleName;

    @Column(name = "rank")
    public int rank;

    public Account(){
        super();
    }
}
