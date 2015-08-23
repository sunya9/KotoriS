package net.unsweets.kotoris.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by mugen_000 on 2015/08/23.
 */
@Table(name = "columns")
public class Timeline extends Model {
    private static final String TAG = Timeline.class.getSimpleName();
    @Column(name = "name")
    public String name;
    @Column(name = "user_id")
    public long userId;
    @Column(name = "timeline_type")
    public int type;

    public Timeline() {
        super();
    }
}
