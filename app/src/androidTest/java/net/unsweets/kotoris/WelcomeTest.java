package net.unsweets.kotoris;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

/**
 * Created by mugen_000 on 2015/08/21.
 */
public class WelcomeTest extends BaseActivityUnitTestCase<Welcome> {

    private Activity mActivity;

    public WelcomeTest() {
        super(Welcome.class);
    }


    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation()
                .getTargetContext(), Welcome.class);
        mActivity = startActivity(intent, null, null);
    }

    public void testLaunchLoginActivityAndFinishedSelf() {
        Button addAccountButton = (Button) mActivity.findViewById(R.id.welcome_add_account_button);
        addAccountButton.performClick();
        assertFalse("Welcome screen is not finished.",isFinishCalled());
        Intent loginIntent = getStartedActivityIntent();
        assertNotNull("Start Login Activity.", loginIntent);
    }
}