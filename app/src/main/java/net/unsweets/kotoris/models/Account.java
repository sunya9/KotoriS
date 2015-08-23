package net.unsweets.kotoris.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.File;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by mugen_000 on 2015/08/17.
 */

@Table(name = "accounts", id = BaseColumns._ID)
public class Account extends Model {
    private static final String TAG = Account.class.getSimpleName();
    private static final String DISK_ICON_CACHE_DIR = "account_icons";
    @Column(name = "user_id", unique = true, notNull = true)
    public Long userId;

    @Column(name = "screen_name")
    public String screenName;

    @Column(name = "name")
    public String name;

    @Column(name = "icon_url")
    public String iconUrl;

    @Column(name = "rank", notNull = true)
    public int rank;

    public List<Client> clients() {
        return getMany(Client.class, "Account");
    }


    public Account() {
        super();
    }

    public static class AccountPreference{

    }
}
