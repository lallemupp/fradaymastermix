package se.mariaochlove.fridaymastermix.home;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.mariaochlove.fridaymastermix.R;

/**
 * Created by love on 2015-07-07.
 */
public class SplashFragment extends Fragment {

    private static final String TAG = SplashFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Creating spash fragment view");
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }
}
