package de.daniel_bachmann.ninepinsfun.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by danie_000 on 11.09.2017.
 */

public class NinepinsThrowsHighnumbers {
    private Long mThrow = null;
    private Long mRound = null;
    private Long mPosition = null;

    //ToDo: Well, this differs from the other relation tables... Not nice...
    public NinepinsThrowsHighnumbers setWithValues(long iThrow, long round, long position){
        mThrow = iThrow;
        mRound = round;
        mPosition = position;

        return this;
    }

    public NinepinsThrowsHighnumbers saveToDatabase(){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        ContentValues values = new ContentValues();
        values.put(NinepinsDataHelper.TABLE_THROWS_HIGHNUMBERS_COLUMN_THROW, mThrow);
        values.put(NinepinsDataHelper.TABLE_THROWS_HIGHNUMBERS_COLUMN_ROUND, mRound);
        values.put(NinepinsDataHelper.TABLE_THROWS_HIGHNUMBERS_COLUMN_POSITION, mPosition);

        db.insert(NinepinsDataHelper.TABLE_THROWS_HIGHNUMBERS, null, values);

        return this;
    }

    public NinepinsThrowsHighnumbers deleteFromDatabase(){
        if(mThrow != null && mRound != null && mPosition != null){
            SQLiteDatabase db = NinepinsDatabase.getDatabase();

            String selection = NinepinsDataHelper.TABLE_THROWS_HIGHNUMBERS_COLUMN_THROW
                    + " = ? AND "
                    +NinepinsDataHelper.TABLE_THROWS_HIGHNUMBERS_COLUMN_ROUND
                    + " = ? AND "
                    +NinepinsDataHelper.TABLE_THROWS_HIGHNUMBERS_COLUMN_POSITION
                    + " = ?";
            String[] selectionArgs = { "" +mThrow, ""+mRound , ""+mPosition};

            db.delete(NinepinsDataHelper.TABLE_THROWS_HIGHNUMBERS, selection, selectionArgs);
        }

        return this;
    }
}
