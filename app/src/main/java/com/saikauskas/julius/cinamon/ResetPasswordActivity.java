package com.saikauskas.julius.cinamon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mAuth = FirebaseAuth.getInstance(); //initializes firebas auth

        EditText etForgotYourPasswordEmail = findViewById(R.id.etForgotYourPasswordEmail);
        Button bttnSendPasswordReset = findViewById(R.id.bttnSendPasswordReset);
        ImageView bttnBackPasswordReset = findViewById(R.id.bttnBackPasswordReset);

        bttnBackPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slideright, R.anim.slideleft);
            }
        });

        bttnSendPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = etForgotYourPasswordEmail.getText().toString().trim(); //gets the user email

                //checks if the email field is not empty
                if (userEmail.isEmpty()){
                    Toast.makeText(ResetPasswordActivity.this, "Please enter your email adress first.", Toast.LENGTH_SHORT).show();
                }
                else {
                    //if the email field is not empty it will send a password reset to specified address
                    mAuth.sendPasswordResetEmail(userEmail)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //if the task is successful it will open up a dialog box prompting it
                                        openDialog();

                                    }
                                    else {
                                        //if task is not succesfull it will send a toast
                                        Toast.makeText(ResetPasswordActivity.this, "Oops! Something went wrong please try again.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });

    }

    //Creates new dialog in this activity Dialog.java
    public void openDialog(){
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }

}
