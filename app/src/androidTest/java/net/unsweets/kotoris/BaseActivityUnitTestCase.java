package net.unsweets.kotoris;

import android.app.Activity;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.test.ActivityUnitTestCase;

/**
 * Created by mugen_000 on 2015/08/22.
 */
public class BaseActivityUnitTestCase<E extends Activity> extends ActivityUnitTestCase {
    private static final String TAG = BaseActivityUnitTestCase.class.getSimpleName();

    public BaseActivityUnitTestCase(Class<E> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ContextThemeWrapper context = new ContextThemeWrapper(
                getInstrumentation().getTargetContext(), R.style.AppTheme);

        setActivityContext(context);

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }


}
