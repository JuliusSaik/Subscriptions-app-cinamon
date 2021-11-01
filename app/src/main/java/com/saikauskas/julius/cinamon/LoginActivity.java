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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    private static final String TAG = "LoginActivity"; //Tag is set to LoginActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Checks if the user is logged in or not
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {

                    //sends user to main activity if he is
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        };
        mAuth = FirebaseAuth.getInstance(); //initializes firebase auth

        ImageView bttnBackArrowLogin = findViewById(R.id.bttnBackArrowLogin);
        final TextView tvOopsWrongPassword = findViewById(R.id.tvOopsWrongPassword);
        TextView tvForgotYourPassword = findViewById(R.id.tvForgotYourPassword);

        final EditText etEnterEmail = findViewById(R.id.etEnterEmail);
        final EditText etEnterPassword = findViewById(R.id.etEnterPassword);

        Button bttnLogin = findViewById(R.id.bttnLogin);

        tvOopsWrongPassword.setVisibility(View.GONE); //At the start of app sets the worng password field to GONE

        tvForgotYourPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });



        bttnBackArrowLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ChooseLoginSignUp.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slideright, R.anim.slideleft);
            }
        });

        bttnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEnterEmail.getText().toString().trim(); //gets the typed in email of user
                final String password = etEnterPassword.getText().toString().trim(); //gets the typed in password of user

                //checks if the fields are empty or not
                if(email.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //if the fields are not empty it simply logs in to the app
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //if the password or gmail is incorrect sets the tv visibility to VISIBLE
                            if (!task.isSuccessful()) {
                                tvOopsWrongPassword.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
            }
        });

        tvOopsWrongPassword.setVisibility(View.INVISIBLE);

    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}
