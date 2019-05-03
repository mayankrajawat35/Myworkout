package demo.com.myworkoutas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    String gender = "nothing";
    EditText emailId;
    EditText password;
    CharSequence email;
    String pass;
    AlertDialog alert;
    SessionManager sessionManager;
    Intent intent;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        emailId = (EditText) findViewById(R.id.registrationEmail);
        email = getIntent().getCharSequenceExtra("mailId");
        emailId.setText(email);
        Toast.makeText(getApplicationContext(), " Not Registered -- " + email, Toast.LENGTH_LONG).show();
    }

    public void signupClicked(View v){

        emailId = (EditText) findViewById(R.id.registrationEmail);
        password = (EditText) findViewById(R.id.registrationPassword);
        email = emailId.getText();
        pass = password.getText().toString();

        if(Util.isValidEmail(email)){
            if(Util.validatePassword(pass)){
                if(!gender.equals("nothing")){
                    sessionManager = new SessionManager(getApplicationContext());
                    sessionManager.createLoginSession(gender,email.toString());
                    intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.putExtra("emailId", email.toString());
                    intent.putExtra("gender", gender);
                    startActivity(intent);

                }else{
                    //Util.makeAlert(getApplicationContext(),"Please Select Gender");
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Select Gender!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                    //flag = false;
                }

            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Invalid Password!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                flag = false;
            }
        } else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Invalid EmailId!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            flag = false;
        }

    }


    public void cancleClicked(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.femaleRadioBtn:
                if (checked)
                    gender = "Female";
                    break;
            case R.id.maleRadioBtn:
                if (checked)
                    gender = "Male";
                    break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
