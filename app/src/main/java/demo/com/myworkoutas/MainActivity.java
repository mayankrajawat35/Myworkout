package demo.com.myworkoutas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText emailId;
    CharSequence emailText;
    String gender;
    SessionManager sessionManager;
    Intent intent;
    HashMap<String, String> userDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sessionManager = new SessionManager(this);
        if(sessionManager.isLoggedIn()){
            intent = new Intent(getApplicationContext(), ProfileActivity.class);
           userDetail = sessionManager.getUserDetails();
           emailText = userDetail.get(SessionManager.KEY_EMAIL);
           gender = userDetail.get(SessionManager.KEY_GENDER);

           intent.putExtra("emailId", emailText);
           intent.putExtra("gender", gender);
           startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomelayout);
         emailId = (EditText) findViewById(R.id.emailEditText);
         emailText = emailId.getText();
    }


    public void nextBtnClick(View v){

        final ProgressDialog progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Please Wait....");
        progressDoalog.setTitle("Checking");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(Util.isValidEmail(emailText)) {
                            if(Util.emailsEquals(Util.REGISTERED_MAIL, emailText.toString())) {
                                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                intent.putExtra("mailId", emailText);
                                startActivity(intent);
                            }
                            else {
                               // Toast.makeText(getApplicationContext(), " Not Registered -- " + emailText, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                                intent.putExtra("mailId", emailText);
                                startActivity(intent);
                            }

                        }
                        else
                            Toast.makeText(getApplicationContext(),"Invalid Email", Toast.LENGTH_LONG).show();
                        progressDoalog.dismiss();
                    }
                }, 2000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
