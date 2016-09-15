package se.mariaochlove.fridaymastermix.home;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import se.mariaochlove.fridaymastermix.R;


public class HomeActivity extends Activity implements SharedPreferencesManager.Callback {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private Handler mainThreadHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mainThreadHandler = new Handler();

        fragmentManager = getFragmentManager();

        replaceFragment(new SplashFragment(), false);

        Log.d(TAG, "Loading preferences");
        SharedPreferencesManager.getInstance().readAllPreferences(this, this);
        Log.d(TAG, "Shared preferences started, on create done. Will wait for callback");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_login) {
            replaceFragment(new LoginFragment(), true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(final Fragment fragment, final boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void done() {
        Log.d(TAG, "Done!");

        String refreshToken = SharedPreferencesManager.getInstance().getSharedPreferences(SharedPreferencesManager.SPOTIFY_REFRESH_TOKEN);
        //if (refreshToken == null) {
        //    replaceFragment(new WelcomeFragment(), false);
        //} else {
        replaceFragment(new HomeFragment(), false);
        //}
    }
}
