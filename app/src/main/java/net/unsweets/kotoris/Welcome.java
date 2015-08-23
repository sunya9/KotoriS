package net.unsweets.kotoris;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button addAccountButton = (Button) findViewById(R.id.welcome_add_account_button);
        addAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Login.newIntent(getApplicationContext());
                startActivityForResult(intent, REQUEST_CODE.FIRST_TIME.ordinal());
            }
        });
    }

    ;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (REQUEST_CODE.values()[requestCode]) {
                case FIRST_TIME: {
                    PrefManager instance = PrefManager.getInstance(this);
                    instance.setExistsAccount(true);
                    setResult(RESULT_OK);
                    finish();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    enum REQUEST_CODE {
        FIRST_TIME
    }
}
