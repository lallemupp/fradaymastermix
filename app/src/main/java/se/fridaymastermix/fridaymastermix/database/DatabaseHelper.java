package se.mariaochlove.fridaymastermix.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static se.mariaochlove.fridaymastermix.database.DatabaseContract.GroupEntry;

/**
 * Created by love on 2015-07-15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String CREATE_DB_SQL =
            "CREATE TABLE " + GroupEntry.TABLE_NAME + " (" +
                    GroupEntry._ID + " INTEGER PRIMARY KEY, " +
                    GroupEntry.COLUMN_NAME_GROUP_ID + " TEXT," +
                    GroupEntry.COLUMN_NAME_GROUP_NAME + "TEXT" +
                    GroupEntry.COLUMN_NAME_UPDATED + "INTEGER)";

    private static final String DROP_DB_SQL =
            "DROP TABLE IF EXIST " + GroupEntry.TABLE_NAME;

    private static final String DATABASE_NAME = "FridayMasterMix.db";

    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_DB_SQL);
        db.execSQL(CREATE_DB_SQL);
    }

    public Cursor getGroupCursor() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {GroupEntry.COLUMN_NAME_GROUP_ID, GroupEntry.COLUMN_NAME_GROUP_NAME};
        String sortOrder = GroupEntry.COLUMN_NAME_UPDATED + "DESC";
        return db.query(
                GroupEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);
    }
}
