package net.unsweets.kotoris.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by mugen_000 on 2015/08/23.
 */

@Table(name = "search")
public class Search extends Model {
    private static final String TAG = Search.class.getSimpleName();
    @Column(name = "keyword")
    public String keyword;

    public Search() {
        super();
    }
}
