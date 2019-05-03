package demo.com.myworkoutas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    Intent intent;
    String emaiId;
    String gender;
    TextView emailText;
    TextView genderText;
    ImageView profileImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        intent = getIntent();
        emaiId = intent.getStringExtra("emailId");
        gender = intent.getStringExtra("gender");

        emailText = (TextView) findViewById(R.id.profileemailId);
        genderText = (TextView) findViewById(R.id.profilegenderLbl);
        profileImage = (ImageView) findViewById(R.id.profileImage);
        Util.Log(emaiId);
        emailText.setText(emaiId);
        genderText.setText(gender);

        if(gender.equals("Male"))
            profileImage.setImageResource(R.drawable.male);
        else
            profileImage.setImageResource(R.drawable.female);

    }

    public void logout(View v){
        new SessionManager(getApplicationContext()).logoutUser();;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
