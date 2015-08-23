package net.unsweets.kotoris.models;

import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import net.unsweets.kotoris.R;

/**
 * Created by mugen_000 on 2015/08/23.
 */
@Table(name = "columns")
public class Timeline extends Model {
    private static final String TAG = Timeline.class.getSimpleName();

    @Column(name = "name")
    public String name;
    @Column(name = "alias_name")
    public String alias;
    @Column(name = "user_id")
    public long userId;
    @Column(name = "timeline_type")
    public int type;


    public Timeline() {
        super();
    }

    public static void initialize(Context ctx, long userId) {
        ActiveAndroid.beginTransaction();
        try {
            for (TimelineTemplate tt : generateTimelineTemplate(userId)) {
                Timeline timeline = new Timeline();
                timeline.name = ctx.getString(tt.nameRes);
                timeline.alias = tt.nameAlias;
                timeline.userId = tt.userId;
                timeline.type = tt.timelineType;
                timeline.save();
            }
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    private static TimelineTemplate[] generateTimelineTemplate(long userId) {
        TimelineTemplate tt[] = new TimelineTemplate[4];
        tt[0] = new TimelineTemplate(R.string.timeline_home_string, userId, R.integer.timeline_home_integer);
        tt[1] = new TimelineTemplate(R.string.timeline_mentions_string, userId, R.integer.timeline_mentions_integer);
        tt[2] = new TimelineTemplate(R.string.timeline_messages_string, userId, R.integer.timeline_messages_integer);
        tt[3] = new TimelineTemplate(R.string.timeline_notifications_string, userId, R.integer.timeline_notifications_integer);
        return tt;
    }

    private static class TimelineTemplate {
        private int nameRes;
        private String nameAlias = "";
        private long userId;
        private int timelineType;

        public TimelineTemplate(int nameRes, long userId, int timelineType) {
            this.nameRes = nameRes;
            this.userId = userId;
            this.timelineType = timelineType;
        }
    }
}
