package net.unsweets.kotoris.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by mugen_000 on 2015/08/23.
 */

@Table(name = "timeline_type")
public class TimelineType extends Model {
    private static final String TAG = TimelineType.class.getSimpleName();
    @Column(name = "type")
    public int type;
    @Column(name = "name")
    public String name;

    public TimelineType() {
        super();
    }
}
