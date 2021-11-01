package com.saikauskas.julius.cinamon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //sets firebase auth listener to check if the user is logged in or not
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {

                    //if the useris logged in it sends him to the main activity, else, doesn't
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        };
        mAuth = FirebaseAuth.getInstance(); //initializes mAuth to firebaseAuth

        final EditText etEnterEmail = findViewById(R.id.etEnterEmail);
        final EditText etEnterPassword = findViewById(R.id.etEnterPassword);

        Button bttnSignUp = findViewById(R.id.bttnSignUp);

        ImageView bttnBackArrowSignUp= findViewById(R.id.bttnBackArrowSignUp);

        bttnBackArrowSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, ChooseLoginSignUp.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slideright, R.anim.slideleft);
            }
        });

        bttnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEnterEmail.getText().toString().trim();
                final String password = etEnterPassword.getText().toString().trim();

                //checks if the fields are not empty
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    //if not it will create a user with email and password
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplication(), "Trouble Signing up, please try again.", Toast.LENGTH_SHORT).show();
                            } else {
                                //String userId = mAuth.getCurrentUser().getUid();
                            }
                        }
                    });
                }

            }
        });



    }
    //self explanatory
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
