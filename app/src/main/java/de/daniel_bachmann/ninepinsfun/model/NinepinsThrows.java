package de.daniel_bachmann.ninepinsfun.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by danie_000 on 11.09.2017.
 */

public class NinepinsThrows {

    private Long mId = null;
    private Long mPlayer = null;
    private Long mPoints = null;

    public long getmId() {
        return mId;
    }

    public NinepinsThrows setmId(long mId) {
        this.mId = mId;
        return this;
    }

    public long getmPlayer() {
        return mPlayer;
    }

    public NinepinsThrows setmPlayer(long mPlayer) {
        this.mPlayer = mPlayer;
        return this;
    }

    public long getmPoints() {
        return mPoints;
    }

    public NinepinsThrows setmPoints(long mPoints) {
        this.mPoints = mPoints;
        return this;
    }

    public NinepinsThrows loadById(long id){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        //Seriously? How can this "SELECT * FROM players WHERE id = ?", become that:

        String[] projection = {
                NinepinsDataHelper.TABLE_THROWS_COLUMN_ID,
                NinepinsDataHelper.TABLE_THROWS_COLUMN_PLAYER,
                NinepinsDataHelper.TABLE_THROWS_COLUMN_POINTS
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = NinepinsDataHelper.TABLE_THROWS_COLUMN_ID + " = ?";
        String[] selectionArgs = { ""+id };

        Cursor cursor = db.query(
                NinepinsDataHelper.TABLE_THROWS,
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
                cursor.getColumnIndexOrThrow(NinepinsDataHelper.TABLE_THROWS_COLUMN_ID));

        mPlayer = cursor.getLong(
                cursor.getColumnIndex(NinepinsDataHelper.TABLE_THROWS_COLUMN_PLAYER));

        mPoints = cursor.getLong(
                cursor.getColumnIndex(NinepinsDataHelper.TABLE_THROWS_COLUMN_POINTS));

        cursor.close();

        return this;
    }

    public NinepinsThrows saveToDatabase(){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        ContentValues values = new ContentValues();
        values.put(NinepinsDataHelper.TABLE_THROWS_COLUMN_PLAYER, mPlayer);
        values.put(NinepinsDataHelper.TABLE_THROWS_COLUMN_POINTS, mPoints);

        if(mId==null){
            //this is a new dataset/row
            mId = db.insert(NinepinsDataHelper.TABLE_THROWS, null, values);
        }else{
            values.put(NinepinsDataHelper.TABLE_THROWS_COLUMN_ID, mId);

            String selection = NinepinsDataHelper.TABLE_THROWS_COLUMN_ID + " = ?";
            String[] selectionArgs = { "" + mId };

            int count = db.update(
                    NinepinsDataHelper.TABLE_THROWS,
                    values,
                    selection,
                    selectionArgs);
        }

        return this;
    }

    public NinepinsThrows deleteFromDatabase(){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        String selection = NinepinsDataHelper.TABLE_THROWS_COLUMN_ID + " = ?";
        String[] selectionArgs = { "" +mId };

        db.delete(NinepinsDataHelper.TABLE_THROWS, selection, selectionArgs);

        return this;
    }
}
