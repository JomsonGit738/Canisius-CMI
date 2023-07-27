package com.brocodz.devamathacmi.fuctions.prayerrequests;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.brocodz.devamathacmi.R;
import java.text.DateFormat;
import java.util.Calendar;

public class PrayerRequests extends AppCompatActivity {
    EditText Name,Email,Message;
    String date;
    Button send;
    public String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_requests);
        Name = findViewById(R.id.rename);
        Email = findViewById(R.id.reemail);
        Message = findViewById(R.id.reContent);
        send = findViewById(R.id.rebut);

        //fetch current date to the app
        Calendar calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
        date = currentDate;


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String message = Message.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Name.setError("Type Your Name, Can't be Empty");
                    Name.requestFocus();
                } else if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Email.setError("Valid email is not found...");
                    Email.requestFocus();
                } else if (TextUtils.isEmpty(message)) {
                    Message.setError("Type Message, Can't be Empty");
                    Message.requestFocus();
                } else {
                    Intent i_email = new Intent(Intent.ACTION_SEND);
                    i_email.setType("message/rfc822");
                    i_email.putExtra(Intent.EXTRA_EMAIL, new String[]{"sgfrcanisiuscmi@gmail.com"});
                    i_email.putExtra(Intent.EXTRA_SUBJECT, "Prayer Request from : "+ name);
                    i_email.putExtra(Intent.EXTRA_TEXT, " Date : " + currentDate+"\n"+" Email : " + email+"\n"+" Request : "+message);
                    try {
                        startActivity(Intent.createChooser(i_email, "Send Mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(PrayerRequests.this, "We found No Email account in your Gadget!", Toast.LENGTH_LONG).show();
                    }
                }
                Name.setText("");
                Email.setText("");
                Message.setText("");
            }
        });
    }


}