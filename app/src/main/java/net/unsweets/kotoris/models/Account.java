package net.unsweets.kotoris.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

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

    @Column(name = "name")
    public String name;

    @Column(name = "icon")
    public Blob icon;

    @Column(name = "rank")
    public int rank;

    public List<Client> clients() {
        return getMany(Client.class, "user_id");
    }

    public Account() {
        super();
    }

    public Bitmap getBitmapIcon() {
        try {
            int length = (int) icon.length();
            byte[] bytes = icon.getBytes(1, length);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
