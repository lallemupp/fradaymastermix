package se.mariaochlove.fridaymastermix.home;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.mariaochlove.fridaymastermix.R;

public class WelcomeFragment extends Fragment {

    private static final String TAG = WelcomeFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Creating home fragment view");
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
