package sg.edu.np.mad.prac3ver1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "users.db";
//    public static String USERS = "users";
//    public static String COLUMN_NAME = "name";
//    public static String COLUMN_DESC = "description";
//    public static String COLUMN_ID = "id";
//    public static String COLUMN_FOLLOWED = "followed";
    public static int DATABASE_VERSION = 1;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //to future me
        //you can straight up just string the whole sql command don't bother
        String create = "CREATE TABLE users (name TEXT, description TEXT, id INTEGER, followed INTEGER)";
        db.execSQL(create);

        for(int i=0; i<20; i++)
        {
            ContentValues values = new ContentValues();
            values.put("name", "Name" + new Random().nextInt());
            values.put("description","Description " + new Random().nextInt());
            values.put("followed", new Random().nextInt()%2 == 0);
            db.insert("users", null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public ArrayList<User> getUsers()
    {
        ArrayList<User> list = new ArrayList<User> ();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users", null);

        while(cursor.moveToNext())
        {
            User user = new User();
            user.setName(cursor.getString(0));
            user.setDescription(cursor.getString(1));
            user.setId(cursor.getInt(2));
            //some cursed shit i have no idea
            //? = if statement, if ==0, return false, else true
            user.setFollowed(cursor.getInt(3)==0?false:true);

            list.add(user);
        }

        cursor.close();
        db.close();
        return list;
    }

    public void updateUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("followed", user.isFollowed());
        int count = db.update("user", values, "id = ?", new String[]{ "" + user.getId() });

        db.close();
    }
}
