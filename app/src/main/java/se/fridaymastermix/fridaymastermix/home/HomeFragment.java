package se.mariaochlove.fridaymastermix.home;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

import se.mariaochlove.fridaymastermix.R;
import se.mariaochlove.fridaymastermix.database.DatabaseHelper;


/**
 * Fragment for the home view.
 *
 * @author Love Löfdahl
 */
public class HomeFragment extends ListFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    private Cursor groupCursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Creating home fragment view");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatabaseHelper helper = new DatabaseHelper(getActivity());
        groupCursor = helper.getGroupCursor();
        ListAdapter listAdpter = new SimpleCursorAdapter(getActivity(), R.layout.list_groups, groupCursor, null, null, CursorAdapter.NO_SELECTION);
        setListAdapter(listAdpter);
    }

    @Override
    public void onDestroy() {
        groupCursor.close();
    }
}
