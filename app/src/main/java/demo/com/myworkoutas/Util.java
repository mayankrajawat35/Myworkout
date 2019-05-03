package demo.com.myworkoutas;

import android.text.TextUtils;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    final static String REGISTERED_MAIL = "registered@email.com";
    final static String REGISTERED_PASSWORD = "password";


     final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


    static boolean emailsEquals(String email1,String email2) {
        Pattern address= Pattern.compile("([\\w\\.]+)@([\\w\\.]+\\.\\w+)");
        Matcher match1=address.matcher(email1);
        Matcher match2=address.matcher(email2);
        if(!match1.find() || !match2.find()) return false; //Not an e-mail address? Already false
        if(!match1.group(2).equalsIgnoreCase(match2.group(2))) return false; //Not same serve? Already false

        String mail1=match1.group(1).replace(".", "");
        String mail2=match2.group(1).replace(".", "");
        return mail1.equalsIgnoreCase(mail2);

    }

    static boolean validatePassword(String pass){
         if(pass.length() >= 6)
             return true;
         else return false;
    }

    final static void Log(String message){
        Log.i("MyWorkOut", message);
    }
}
