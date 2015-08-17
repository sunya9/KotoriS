package net.unsweets.kotoris.model;

import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by mugen_000 on 2015/08/17.
 */

@Table(name = "clients", id = BaseColumns._ID)
public class Client extends Model {
    private static final String TAG = Client.class.getSimpleName();

    @Column(name = "api_key")
    public String apiKey;

    @Column(name = "api_secret")
    public String apiSecret;

    @Column(name = "access_token", notNull = true)
    public String accessToken;

    @Column(name = "access_token_secret", notNull = true)
    public String accessTokenSecret;

    @Column(name = "name")
    public String name;

    public Client(){
        super();
    }
}
