package de.daniel_bachmann.ninepinsfun.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by danie_000 on 11.09.2017.
 */

public class NinepinsGamesPlayers {

    private Long mGame = null;
    private Long mPlayer = null;

    //Hmm, probably not too clever... But hey I'm kinda rushing this ;-)
    //And I believe this can be used nicely from the controller
    public NinepinsGamesPlayers setWithObjects(NinepinsGames game, NinepinsPlayer player){
        mGame = game.getmId();
        mPlayer = player.getmId();

        return this;
    }

    public NinepinsGamesPlayers saveToDatabase(){
        SQLiteDatabase db = NinepinsDatabase.getDatabase();

        ContentValues values = new ContentValues();
        values.put(NinepinsDataHelper.TABLE_GAMES_PLAYERS_COLUMN_GAME, mGame);
        values.put(NinepinsDataHelper.TABLE_GAMES_PLAYERS_COLUMN_PLAYER, mPlayer);

        db.insert(NinepinsDataHelper.TABLE_GAMES_PLAYERS, null, values);

        return this;
    }

    public NinepinsGamesPlayers deleteFromDatabase(){
        if(mGame != null && mPlayer != null){
            SQLiteDatabase db = NinepinsDatabase.getDatabase();

            String selection = NinepinsDataHelper.TABLE_GAMES_PLAYERS_COLUMN_GAME
                    + " = ? AND "
                    +NinepinsDataHelper.TABLE_GAMES_PLAYERS_COLUMN_PLAYER
                    + " = ?";
            String[] selectionArgs = { "" +mGame, ""+mPlayer };

            db.delete(NinepinsDataHelper.TABLE_GAMES_PLAYERS, selection, selectionArgs);
        }

        return this;
    }

}
