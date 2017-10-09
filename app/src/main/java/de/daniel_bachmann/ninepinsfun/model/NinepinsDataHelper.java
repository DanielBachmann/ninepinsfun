package de.daniel_bachmann.ninepinsfun.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class NinepinsDataHelper extends SQLiteOpenHelper {
    private  static final String LOG_TAG = NinepinsDataHelper.class.getSimpleName();

    public static final String DB_NAME = "ninepins.db";
    public static final int DB_VERSION = 1;

    //Now the tables, I'm not so sure what to think of this design, but it seems to be best practice
    //I do it a little bit different nonetheless, by defining constants for all tables, but building
    //the CREATE's in a helper method saving them to an array

    //table game_variation
    public static final String TABLE_GAME_VARIATION = "game_variation";

    public static final String TABLE_GAME_VARIATION_COLUMN_ID = "id";//INTEGER, primary
    public static final String TABLE_GAME_VARIATION_COLUMN_NAME = "name"; //TEXT
    public static final String TABLE_GAME_VARIATION_COLUMN_DESCRIPTION = "description";//TEXT

    //table places
    public static final String TABLE_PLACES = "places";

    public static final String TABLE_PLACES_COLUMN_ID = "id";//INTEGER, primary, autoincrement
    public static final String TABLE_PLACES_COLUMN_NAME = "name";//TEXT
    public static final String TABLE_PLACES_COLUMN_DESCRIPTION = "description";//TEXT

    //table games
    public static final String TABLE_GAMES = "games";

    public static final String TABLE_GAMES_COLUMN_ID = "id";//INTEGER, primary, autoincrement
    public static final String TABLE_GAMES_COLUMN_VARIATION = "variation";//INTEGER, foreign key
    public static final String TABLE_GAMES_COLUMN_BEGIN = "begin";//INTEGER (timestamp)
    public static final String TABLE_GAMES_COLUMN_END = "end";//INTEGER (timestamp)
    public static final String TABLE_GAMES_COLUMN_PLACE = "place";//INTEGER, foreign key

    //table games_players - relation table (no primary key)
    public static final String TABLE_GAMES_PLAYERS = "games_players";

    public static final String TABLE_GAMES_PLAYERS_COLUMN_GAME = "game";//INTEGER, foreign key
    public static final String TABLE_GAMES_PLAYERS_COLUMN_PLAYER = "player";//INTEGER, foreign key

    //table throws_games - relation table (no primary key)
    public static final String TABLE_THROWS_GAMES = "throws_games";

    public static final String TABLE_THROWS_GAMES_COLUMN_THROW = "throw";//INTEGER, foreign key
    public static final String TABLE_THROWS_GAMES_COLUMN_GAME = "game";//INTEGER, foreign key
    public static final String TABLE_THROWS_GAMES_COLUMN_COUNT = "count";//INTEGER (count'th throw in game)

    //table players
    public static final String TABLE_PLAYERS = "players";

    public static final String TABLE_PLAYERS_COLUMN_ID = "id";//INTEGER, primary key, autoincrement
    public static final String TABLE_PLAYERS_COLUMN_NAME = "name";//TEXT
    public static final String TABLE_PLAYERS_COLUMN_DESCRIPTION = "description";//TEXT

    //table throws
    public static final String TABLE_THROWS = "throws";

    public static final String TABLE_THROWS_COLUMN_ID = "id";//INTEGER, primary key, autoincrement
    public static final String TABLE_THROWS_COLUMN_PLAYER = "player";//INTEGER, foreign key
    public static final String TABLE_THROWS_COLUMN_POINTS = "points";//INTEGER

    //table throws_highnumbers - The game variations (may) need extended data
    //this is for the variation "Hohe Hausnummern"/ "High Numbers"
    public static final String TABLE_THROWS_HIGHNUMBERS = "throws_highnumbers";

    public static final String TABLE_THROWS_HIGHNUMBERS_COLUMN_THROW = "throw";//INTEGER, foreign key
    public static final String TABLE_THROWS_HIGHNUMBERS_COLUMN_ROUND = "round";//INTEGER
    public static final String TABLE_THROWS_HIGHNUMBERS_COLUMN_POSITION = "position";//INTEGER


    public NinepinsDataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        ArrayList<String> tableCreates = getTableCreates();

        for(String tableCreate : tableCreates){
            sqLiteDatabase.execSQL(tableCreate);
            Log.d(LOG_TAG, tableCreate);
        }

        //set game variations
        ArrayList<String> gameVariations = getGameVariations();

        for(String gameVariation : gameVariations){
            sqLiteDatabase.execSQL(gameVariation);
            Log.d(LOG_TAG, gameVariation);
        }

        //create a place
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PLACES +" VALUES (null, 'Sozialzentrum', null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private ArrayList<String> getTableCreates(){
        ArrayList<String> tableCreates = new ArrayList<String>();

        tableCreates.add(
                "CREATE TABLE " + TABLE_GAME_VARIATION +
                "(" + TABLE_GAME_VARIATION_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                TABLE_GAME_VARIATION_COLUMN_NAME + " TEXT NOT NULL, " +
                TABLE_GAME_VARIATION_COLUMN_DESCRIPTION + " TEXT);"
        );

        tableCreates.add(
                "CREATE TABLE " + TABLE_PLACES +
                "(" + TABLE_PLACES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_PLACES_COLUMN_NAME + " TEXT NOT NULL, " +
                TABLE_PLACES_COLUMN_DESCRIPTION + " TEXT);"
        );

        tableCreates.add(
                "CREATE TABLE " + TABLE_GAMES +
                "(" + TABLE_GAMES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_GAMES_COLUMN_VARIATION + " INTEGER NOT NULL, " +
                TABLE_GAMES_COLUMN_BEGIN + " INTEGER NOT NULL, " +
                TABLE_GAMES_COLUMN_END + " INTEGER, " + //end can be null, marking an unfinished game
                TABLE_GAMES_COLUMN_PLACE + " INTEGER);"
        );

        tableCreates.add(
                "CREATE TABLE " + TABLE_GAMES_PLAYERS +
                "(" + TABLE_GAMES_PLAYERS_COLUMN_GAME + " INTEGER NOT NULL, " +
                TABLE_GAMES_PLAYERS_COLUMN_PLAYER + " INTEGER NOT NULL);"
        );

        tableCreates.add(
                "CREATE TABLE " + TABLE_THROWS_GAMES +
                "(" + TABLE_THROWS_GAMES_COLUMN_THROW + " INTEGER NOT NULL, " +
                TABLE_THROWS_GAMES_COLUMN_GAME + " INTEGER NOT NULL, " +
                TABLE_THROWS_GAMES_COLUMN_COUNT + " INTEGER NOT NULL);"
        );

        tableCreates.add(
                "CREATE TABLE " + TABLE_PLAYERS +
                "(" + TABLE_PLAYERS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_PLAYERS_COLUMN_NAME + " TEXT NOT NULL, " +
                TABLE_PLAYERS_COLUMN_DESCRIPTION + " TEXT);"
        );

        tableCreates.add(
                "CREATE TABLE " + TABLE_THROWS +
                "(" + TABLE_THROWS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_THROWS_COLUMN_PLAYER + " INTEGER NOT NULL, " +
                TABLE_THROWS_COLUMN_POINTS + " INTEGER NOT NULL);"
        );

        tableCreates.add(
                "CREATE TABLE " + TABLE_THROWS_HIGHNUMBERS +
                "(" + TABLE_THROWS_HIGHNUMBERS_COLUMN_THROW + " INTEGER NOT NULL, " +
                        TABLE_THROWS_HIGHNUMBERS_COLUMN_ROUND + " INTEGER NOT NULL, " +
                        TABLE_THROWS_HIGHNUMBERS_COLUMN_POSITION + " INTEGER NOT NULL);"
        );

        return tableCreates;
    }

    private ArrayList<String> getGameVariations(){
        ArrayList<String> gameVariations = new ArrayList<String>();

        gameVariations.add(
                "INSERT INTO "+TABLE_GAME_VARIATION+ " VALUES (null, 'highnumbers', null);"
        );

        return gameVariations;
    }
}
