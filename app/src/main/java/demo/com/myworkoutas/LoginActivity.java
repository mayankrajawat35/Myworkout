package demo.com.myworkoutas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    CharSequence emailtext;
    CharSequence pass;
    EditText et;
    EditText password;
    Intent intent;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        intent = getIntent();
        emailtext = intent.getCharSequenceExtra("mailId");
        EditText et = (EditText) findViewById(R.id.loginEmail);
        et.setText(emailtext);
    }

    public void backClicked(View v){
        intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void loginClicked(View v){

        sessionManager = new SessionManager(getApplicationContext());

        Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_LONG).show();
        et = (EditText) findViewById(R.id.loginEmail);
        emailtext = et.getText();
        password = (EditText) findViewById(R.id.passwordeditText);
        pass = password.getText();
        if(pass.toString().equals(Util.REGISTERED_PASSWORD) && (Util.isValidEmail(emailtext) && Util.emailsEquals(Util.REGISTERED_MAIL, emailtext.toString()))){
            Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_LONG).show();

            sessionManager.createLoginSession("male", emailtext.toString());
            intent = new Intent(getApplicationContext(), ProfileActivity.class);


            intent.putExtra("emailId", emailtext);
            intent.putExtra("gender", "male");
            startActivity(intent);

        }else
            Toast.makeText(getApplicationContext(), "not Valid", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
