package de.daniel_bachmann.ninepinsfun.model;


import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class NinepinsDatabase {

    private static SQLiteDatabase mDatabase = null;
    private static NinepinsDataHelper mDatahelper = null;

    /*Todo: Check if SQLiteOpenHelper already holds the database.
    This wouldn't be necessary then...*/

    public static void initialize(Context context){
        if(mDatabase == null) {
            mDatahelper = new NinepinsDataHelper(context);
            mDatabase = mDatahelper.getWritableDatabase();
        }
    }

    public static SQLiteDatabase getDatabase(){
        return mDatabase;
    }

}

/*
Parking some infos here, Todo delete this
Insert:
ContentValues values = new ContentValues();
values.put(FeedEntry.COLUMN_NAME_TITLE, title);
values.put(FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);

// Insert the new row, returning the primary key value of the new row
long newRowId = db.insert(FeedEntry.TABLE_NAME, null, values);

Select:
SQLiteDatabase db = mDbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
String[] projection = {
    FeedEntry._ID,
    FeedEntry.COLUMN_NAME_TITLE,
    FeedEntry.COLUMN_NAME_SUBTITLE
    };

// Filter results WHERE "title" = 'My Title'
String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
String sortOrder =
    FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

Cursor cursor = db.query(
    FeedEntry.TABLE_NAME,                     // The table to query
    projection,                               // The columns to return
    selection,                                // The columns for the WHERE clause
    selectionArgs,                            // The values for the WHERE clause
    null,                                     // don't group the rows
    null,                                     // don't filter by row groups
    sortOrder                                 // The sort order
    );

Update:

// New value for one column
ContentValues values = new ContentValues();
values.put(FeedEntry.COLUMN_NAME_TITLE, title);

// Which row to update, based on the title
String selection = FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
String[] selectionArgs = { "MyTitle" };

int count = db.update(
    FeedReaderDbHelper.FeedEntry.TABLE_NAME,
    values,
    selection,
    selectionArgs);

Delete:
// Define 'where' part of query.
String selection = FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
// Specify arguments in placeholder order.
String[] selectionArgs = { "MyTitle" };
// Issue SQL statement.
db.delete(FeedEntry.TABLE_NAME, selection, selectionArgs);


Reset autoincrement
delete from your_table;
delete from sqlite_sequence where name='your_table';

 */

