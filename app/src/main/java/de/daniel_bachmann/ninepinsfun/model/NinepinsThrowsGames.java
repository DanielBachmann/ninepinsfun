package de.daniel_bachmann.ninepinsfun.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class NinepinsThrowsGames {
    private Long mThrow = null;
    private Long mGame = null;
    private Long mCount = null;

    public NinepinsThrowsGames setWithObjects(NinepinsThrows iThrow, NinepinsGames game, long count){
        mThrow = iThrow.getmId();
        mGame = game.getmId();
        mCount = count;

        return this;
    }

    public NinepinsThrowsGames saveToDatabase(){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        ContentValues values = new ContentValues();
        values.put(NinepinsDataHelper.TABLE_THROWS_GAMES_COLUMN_THROW, mThrow);
        values.put(NinepinsDataHelper.TABLE_THROWS_GAMES_COLUMN_GAME, mGame);
        values.put(NinepinsDataHelper.TABLE_THROWS_GAMES_COLUMN_COUNT, mCount);

        db.insert(NinepinsDataHelper.TABLE_THROWS_GAMES, null, values);

        return this;
    }

    public NinepinsThrowsGames deleteFromDatabase(){
        if(mThrow != null && mGame != null){
            SQLiteDatabase db = NinepinsDatabase.getDatabase();

            String selection = NinepinsDataHelper.TABLE_THROWS_GAMES_COLUMN_THROW
                    + " = ? AND "
                    +NinepinsDataHelper.TABLE_THROWS_GAMES_COLUMN_GAME
                    + " = ?";
            String[] selectionArgs = { "" +mThrow, ""+mGame };

            db.delete(NinepinsDataHelper.TABLE_THROWS_GAMES, selection, selectionArgs);
        }

        return this;
    }
}
