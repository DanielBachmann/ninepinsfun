package de.daniel_bachmann.ninepinsfun.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by danie_000 on 11.09.2017.
 */

public class NinepinsPlaces {
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

    public NinepinsPlaces loadById(long id){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        //Seriously? How can this "SELECT * FROM players WHERE id = ?", become that:

        String[] projection = {
                NinepinsDataHelper.TABLE_PLACES_COLUMN_ID,
                NinepinsDataHelper.TABLE_PLACES_COLUMN_NAME,
                NinepinsDataHelper.TABLE_PLACES_COLUMN_DESCRIPTION,

        };

        String selection = NinepinsDataHelper.TABLE_PLACES_COLUMN_ID + " = ?";
        String[] selectionArgs = { ""+id };

        Cursor cursor = db.query(
                NinepinsDataHelper.TABLE_PLACES,
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
                cursor.getColumnIndexOrThrow(NinepinsDataHelper.TABLE_PLACES_COLUMN_ID));
        mName = cursor.getString(
                cursor.getColumnIndex(NinepinsDataHelper.TABLE_PLACES_COLUMN_NAME));

        mDescription = cursor.getString(
                cursor.getColumnIndex(NinepinsDataHelper.TABLE_PLACES_COLUMN_DESCRIPTION));

        cursor.close();

        return this;
    }

    public NinepinsPlaces saveToDatabase(){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        ContentValues values = new ContentValues();
        values.put(NinepinsDataHelper.TABLE_PLACES_COLUMN_NAME, mName);
        values.put(NinepinsDataHelper.TABLE_PLACES_COLUMN_DESCRIPTION, mDescription);

        if(mId==null){
            //this is a new dataset/row
            mId = db.insert(NinepinsDataHelper.TABLE_PLACES, null, values);
        }else{
            values.put(NinepinsDataHelper.TABLE_PLACES_COLUMN_ID, mId);

            String selection = NinepinsDataHelper.TABLE_PLACES_COLUMN_ID + " = ?";
            String[] selectionArgs = { "" + mId };

            int count = db.update(
                    NinepinsDataHelper.TABLE_PLACES,
                    values,
                    selection,
                    selectionArgs);
        }

        return this;
    }

    public NinepinsPlaces deleteFromDatabase(){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        String selection = NinepinsDataHelper.TABLE_PLACES_COLUMN_ID + " = ?";
        String[] selectionArgs = { "" +mId };

        db.delete(NinepinsDataHelper.TABLE_PLACES, selection, selectionArgs);

        return this;
    }
}
