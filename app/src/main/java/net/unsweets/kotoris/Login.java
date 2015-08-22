package net.unsweets.kotoris;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.activeandroid.query.Select;

import net.unsweets.kotoris.dialogs.BaseDialog;
import net.unsweets.kotoris.dialogs.ProgressDialogFragment;
import net.unsweets.kotoris.fragments.BaseFragment;
import net.unsweets.kotoris.models.Account;
import net.unsweets.kotoris.models.Client;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class Login extends BaseActivity {
    private final static String TAG = Login.class.getSimpleName();

    public static Intent newIntent(Context ctx) {
        return newIntent(ctx, null, null);
    }


    public static Intent newIntent(Context ctx, String apiKey, String apiSecret) {
        Intent intent = new Intent(ctx, Login.class);
        if (apiKey != null && apiSecret != null) {
            intent.putExtra(LoginFragment.ARGUMENT_TAGS.API_KEY.name(), apiKey);
            intent.putExtra(LoginFragment.ARGUMENT_TAGS.API_SECRET.name(), apiSecret);
        }
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // get api key and secret
        Bundle arguments = getIntent().getExtras();
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction =
                    fragmentManager.beginTransaction();
            transaction.replace(R.id.login_webview_frame, LoginFragment.newInstance(arguments));


        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onSuccess() {
        setResult(RESULT_OK);
        finish();
    }


    public static class LoginFragment extends BaseFragment {
        private static final String TAG = LoginFragment.class.getSimpleName();
        private static final String OAUTH_VERIFIER = "oauth_verifier";
        private WebView mWebView;
        private Twitter mTwitter;
        private RequestToken mOAuthRequestToken;
        private String mApiKey;
        private String mApiSecret;
        private Login mActivity;

        public LoginFragment() {
        }

        public static LoginFragment newInstance(Bundle bundle) {
            LoginFragment fragment = new LoginFragment();
            fragment.setArguments(bundle);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_login, container, false);
        }

        @Override
        public void onAttach(Activity activity) {
            if (activity instanceof Login)
                mActivity = (Login) activity;
            super.onAttach(activity);
        }


        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Bundle arguments = getArguments();
            mApiKey = arguments.getString(ARGUMENT_TAGS.API_KEY.name(), null);
            mApiSecret = arguments.getString(ARGUMENT_TAGS.API_SECRET.name(), null);
            if (mApiKey != null && mApiSecret != null) {

            }
            View view = getView();

            mWebView = (WebView) view.findViewById(R.id.login_webview);
            mWebView.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url.startsWith(KotoriS.CALLBACK_URL)) {
                        return success(url);
                    } else {
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                }
            });
            generateURL();
        }

        private void generateURL() {
            String apiKey = mApiKey, apiSecret = mApiSecret;
            if (apiKey == null || apiSecret == null) {
                apiKey = KotoriS.API_KEY;
                apiSecret = KotoriS.API_SECRET;
            }
            new AsyncTask<String, Void, String>() {
                @Override
                protected String doInBackground(String... params) {
                    mTwitter = new TwitterFactory().getInstance();
                    mTwitter.setOAuthConsumer(params[0], params[1]);
                    try {
                        mOAuthRequestToken = mTwitter.getOAuthRequestToken();
                        return mOAuthRequestToken.getAuthorizationURL();
                    } catch (TwitterException e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(String url) {
                    if (url != null) {
                        mWebView.loadUrl(url);
                    }

                }
            }.execute(apiKey, apiSecret);
        }

        private boolean success(String url) {
            if (mTwitter == null || mOAuthRequestToken == null) {
                generateURL();
                return false;
            } else {
                final BaseDialog dialog = ProgressDialogFragment.newInstance(R.string.login_message);

                FragmentManager fragmentManager = getChildFragmentManager();
                dialog.show(fragmentManager, DIALOG_TAG.WAITING.name());

                //get token from url
                new AsyncTask<String, Void, Boolean>() {
                    @Override
                    protected Boolean doInBackground(String... url) {
                        String verifier = Util.getQueryMap(url[0]).get(OAUTH_VERIFIER);

                        try {
                            AccessToken token = mTwitter.getOAuthAccessToken(mOAuthRequestToken, verifier);
                            User user = mTwitter.verifyCredentials();
                            int count = new Select().from(Account.class).count();
                            Account account = new Account();
                            account.userId = user.getId();
                            account.screenName = user.getScreenName();
                            account.name = user.getName();
                            account.iconUrl = user.getProfileImageURL();
                            account.rank = count;

                            int clientRank = account.clients().size();
                            Client client = new Client();
                            client.userId = account.userId;
                            client.accessToken = token.getToken();
                            client.accessTokenSecret = token.getTokenSecret();
                            if (mApiKey == null && mApiSecret == null)
                                client.name = getString(R.string.app_name);
                            client.rank = clientRank;
                            client.save();
                            account.save();
                            return true;
                        } catch (TwitterException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }

                    @Override
                    protected void onPostExecute(Boolean success) {
                        if (success) {
                            dialog.onDismiss(dialog.getDialog());
                            if (mActivity != null)
                                mActivity.onSuccess();
                        } else {

                        }
                    }
                }.execute(url);
                return true;
            }

        }

        private enum DIALOG_TAG {
            WAITING
        }

        public enum ARGUMENT_TAGS {
            API_KEY, API_SECRET
        }


    }
}
