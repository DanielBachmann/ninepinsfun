package de.daniel_bachmann.ninepinsfun.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by danie_000 on 11.09.2017.
 */

public class NinepinsGames {

    private Long mId = null;
    private Long mVariation = null;
    private Long mBegin = null;
    private Long mEnd = null;
    private Long mPlace = null;

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public long getmVariation() {
        return mVariation;
    }

    public void setmVariation(long mVariation) {
        this.mVariation = mVariation;
    }

    public long getmBegin() {
        return mBegin;
    }

    public void setmBegin(long mBegin) {
        this.mBegin = mBegin;
    }

    public long getmEnd() {
        return mEnd;
    }

    public void setmEnd(long mEnd) {
        this.mEnd = mEnd;
    }

    public long getmPlace() {
        return mPlace;
    }

    public void setmPlace(long mPlace) {
        this.mPlace = mPlace;
    }

    public NinepinsGames loadById(long id){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        //Seriously? How can this "SELECT * FROM players WHERE id = ?", become that:

        String[] projection = {
                NinepinsDataHelper.TABLE_GAMES_COLUMN_ID,
                NinepinsDataHelper.TABLE_GAMES_COLUMN_VARIATION,
                NinepinsDataHelper.TABLE_GAMES_COLUMN_BEGIN,
                NinepinsDataHelper.TABLE_GAMES_COLUMN_END,
                NinepinsDataHelper.TABLE_GAMES_COLUMN_PLACE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = NinepinsDataHelper.TABLE_GAMES_COLUMN_ID + " = ?";
        String[] selectionArgs = { ""+id };

        Cursor cursor = db.query(
                NinepinsDataHelper.TABLE_GAMES,
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
                cursor.getColumnIndexOrThrow(NinepinsDataHelper.TABLE_GAMES_COLUMN_ID));

        mId = cursor.getLong(
                cursor.getColumnIndexOrThrow(NinepinsDataHelper.TABLE_GAMES_COLUMN_VARIATION));

        mId = cursor.getLong(
                cursor.getColumnIndexOrThrow(NinepinsDataHelper.TABLE_GAMES_COLUMN_BEGIN));

        mId = cursor.getLong(
                cursor.getColumnIndexOrThrow(NinepinsDataHelper.TABLE_GAMES_COLUMN_END));

        mId = cursor.getLong(
                cursor.getColumnIndexOrThrow(NinepinsDataHelper.TABLE_GAMES_COLUMN_PLACE));



        cursor.close();

        return this;
    }

    public NinepinsGames saveToDatabase(){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        ContentValues values = new ContentValues();
        values.put(NinepinsDataHelper.TABLE_GAMES_COLUMN_VARIATION, mVariation);
        values.put(NinepinsDataHelper.TABLE_GAMES_COLUMN_BEGIN, mBegin);
        values.put(NinepinsDataHelper.TABLE_GAMES_COLUMN_END, mEnd);
        values.put(NinepinsDataHelper.TABLE_GAMES_COLUMN_PLACE, mPlace);

        if(mId==null){
            //this is a new dataset/row
            mId = db.insert(NinepinsDataHelper.TABLE_GAMES, null, values);
        }else{
            values.put(NinepinsDataHelper.TABLE_GAMES_COLUMN_ID, mId);

            String selection = NinepinsDataHelper.TABLE_GAMES_COLUMN_ID + " = ?";
            String[] selectionArgs = { "" + mId };

            int count = db.update(
                    NinepinsDataHelper.TABLE_GAMES,
                    values,
                    selection,
                    selectionArgs);
        }

        return this;
    }

    public NinepinsGames deleteFromDatabase(){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        String selection = NinepinsDataHelper.TABLE_GAMES_COLUMN_ID + " = ?";
        String[] selectionArgs = { "" +mId };

        db.delete(NinepinsDataHelper.TABLE_GAMES, selection, selectionArgs);

        return this;
    }
}