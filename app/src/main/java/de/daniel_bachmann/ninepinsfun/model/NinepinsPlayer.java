package de.daniel_bachmann.ninepinsfun.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by danie_000 on 11.09.2017.
 */

public class NinepinsPlayer {

    private Long mId = null;
    private String mName = null;
    private String mDescription = null;

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    //Todo: Check if this could be done nicer, I would like to use an interface for the next methods
    //which are the same across all not-relation-table models

    public NinepinsPlayer loadById(long id){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        //Seriously? How can this "SELECT * FROM players WHERE id = ?", become that:

        String[] projection = {
                NinepinsDataHelper.TABLE_PLAYERS_COLUMN_ID,
                NinepinsDataHelper.TABLE_PLAYERS_COLUMN_NAME,
                NinepinsDataHelper.TABLE_PLAYERS_COLUMN_DESCRIPTION,

        };

        // Filter results WHERE "title" = 'My Title'
        String selection = NinepinsDataHelper.TABLE_PLAYERS_COLUMN_ID + " = ?";
        String[] selectionArgs = { ""+id };

        Cursor cursor = db.query(
                NinepinsDataHelper.TABLE_PLAYERS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null

        );

        //This is NOT nice @Java!!!

        cursor.moveToNext();

        mId = cursor.getLong(
                cursor.getColumnIndexOrThrow(NinepinsDataHelper.TABLE_PLAYERS_COLUMN_ID));
        mName = cursor.getString(
                cursor.getColumnIndex(NinepinsDataHelper.TABLE_PLAYERS_COLUMN_NAME));

        mDescription = cursor.getString(
                cursor.getColumnIndex(NinepinsDataHelper.TABLE_PLAYERS_COLUMN_DESCRIPTION));

        cursor.close();

        return this;
    }

    public NinepinsPlayer saveToDatabase(){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        ContentValues values = new ContentValues();
        values.put(NinepinsDataHelper.TABLE_PLAYERS_COLUMN_NAME, mName);
        values.put(NinepinsDataHelper.TABLE_PLAYERS_COLUMN_DESCRIPTION, mDescription);

        if(mId==null){
            //this is a new dataset/row
            mId = db.insert(NinepinsDataHelper.TABLE_PLAYERS, null, values);
        }else{
            values.put(NinepinsDataHelper.TABLE_PLAYERS_COLUMN_ID, mId);

            String selection = NinepinsDataHelper.TABLE_PLAYERS_COLUMN_ID + " = ?";
            String[] selectionArgs = { "" + mId };

            int count = db.update(
                    NinepinsDataHelper.TABLE_PLAYERS,
                    values,
                    selection,
                    selectionArgs);
        }

        return this;
    }

    public NinepinsPlayer deleteFromDatabase(){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        String selection = NinepinsDataHelper.TABLE_PLAYERS_COLUMN_ID + " = ?";
        String[] selectionArgs = { "" +mId };

        db.delete(NinepinsDataHelper.TABLE_PLAYERS, selection, selectionArgs);

        return this;
    }

}
