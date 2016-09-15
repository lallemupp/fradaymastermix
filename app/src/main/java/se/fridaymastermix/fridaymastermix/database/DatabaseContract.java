package se.mariaochlove.fridaymastermix.database;

import android.provider.BaseColumns;

/**
 * Created by love on 2015-07-15.
 */
public class DatabaseContract {

    public DatabaseContract() {

    }

    public static abstract class GroupEntry implements BaseColumns {
        public static final String TABLE_NAME = "group";
        public static final String COLUMN_NAME_GROUP_ID = "groupid";
        public static final String COLUMN_NAME_GROUP_NAME = "groupname";
        public static final String COLUMN_NAME_UPDATED = "updated";
    }
}
