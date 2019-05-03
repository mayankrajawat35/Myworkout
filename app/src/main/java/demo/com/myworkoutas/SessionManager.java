package demo.com.myworkoutas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "MyWorkOutPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_EMAIL = "email";

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String gender, String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_GENDER, gender);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_GENDER, pref.getString(KEY_GENDER, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }

    public void logoutUser(){
        Util.Log("Logout");
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();


        Intent i = new Intent(_context, MainActivity.class);
         i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
