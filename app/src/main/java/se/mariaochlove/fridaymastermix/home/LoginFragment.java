package se.mariaochlove.fridaymastermix.home;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import se.mariaochlove.fridaymastermix.R;
import se.mariaochlove.fridaymastermix.utils.UrlBuilder;

/**
 * Created by love on 2015-07-05.
 */
public class LoginFragment extends Fragment {

    private static final String TAG = LoginFragment.class.getSimpleName();

    private static final String SPOTIFY_ACCOUNT_URI =  "https://accounts.spotify.com/authorize";
    private static final String SPOTIFY_CLIENT_ID = "27ac21e8536c42c2adc6f39964e32606";
    private static final String SPOTIFY_CALLBACK_URL = "http://localhost:8000/auth/callback";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        WebView loginView = (WebView) getView().findViewById(R.id.spotify_login_webview);
        loginView.getSettings().setJavaScriptEnabled(true);

        UrlBuilder builder = new UrlBuilder(SPOTIFY_ACCOUNT_URI);
        builder.addQueryParam("client_id", SPOTIFY_CLIENT_ID);
        builder.addQueryParam("response_type", "code");
        builder.addQueryParam("state", "THIS_IS_THE_STATE_OF_THINGS!");
        builder.addQueryParam("redirect_uri", SPOTIFY_CALLBACK_URL);

        loginView.loadUrl(builder.toString());
    }
}
