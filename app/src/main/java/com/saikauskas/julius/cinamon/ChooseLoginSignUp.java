package com.saikauskas.julius.cinamon;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class ChooseLoginSignUp extends AppCompatActivity  {

    static final int GOOGLE_SIGNIN = 123;
    FirebaseAuth mAuth;
    Button bttnGoogleSignIn;
    ProgressBar progressBar;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_sign_up);


        Button bttnLoginChooseReg = findViewById(R.id.bttnLoginChooseReg);
        Button bttnSignUpChooseReg = findViewById(R.id.bttnSignUpChooseReg);
        bttnGoogleSignIn = findViewById(R.id.bttnGoogleSignIn);
        progressBar = findViewById(R.id.progressbar_circular);

        mAuth = FirebaseAuth.getInstance(); //initializes firebase auth

        //sets the googe sign in option to the builder and requests id token from the web and also the email, so it pops up the dialog box
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions); //initializes the google sign in options

        bttnGoogleSignIn.setOnClickListener(v -> SignInGoogle());


        //checks if user is currently logged in or not
        if (mAuth.getCurrentUser() != null) {
            FirebaseUser user = mAuth.getCurrentUser();
            updateUI(user);
        }




        bttnLoginChooseReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLoginSignUp.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bttnSignUpChooseReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLoginSignUp.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    //Calls the finction on activity result
    void SignInGoogle(){
        progressBar.setVisibility(View.VISIBLE); //sets the visibility of progress bar to visible
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGNIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if the request code is the same sets task to pop up window and sign in
        if (requestCode == GOOGLE_SIGNIN) {
            Task<GoogleSignInAccount> task = GoogleSignIn
                    .getSignedInAccountFromIntent(data);
            progressBar.setVisibility(View.INVISIBLE);
            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) firebaseAuthWithGoogle(account);
                    //make a firebase request


            }catch (ApiException e){
                e.printStackTrace();
            }

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId()); //gets id of user
        progressBar.setVisibility(View.INVISIBLE);

        AuthCredential credential = GoogleAuthProvider
                .getCredential(account.getIdToken(), null); //gets credential of user
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task ->{
                    if (task.isSuccessful()){
                        progressBar.setVisibility(View.INVISIBLE); //if the login is successful it will send the user to the main activity
                        Log.d("TAG", "Sign In Success");

                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    }else {
                        Log.w("TAG", "Sign In Failure", task.getException());

                        //if the user is not logged in due to errors or internet problems he will be promted:
                        Toast.makeText(this, "Unable to sign in with google, please try again.", Toast.LENGTH_LONG).show();
                        updateUI(null);
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {

        //gets different user information for firebase auth
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            String photo = String.valueOf(user.getPhotoUrl());
            progressBar.setVisibility(View.INVISIBLE);

            Intent intent = new Intent(ChooseLoginSignUp.this, MainActivity.class);
            startActivity(intent);

        }else {
            progressBar.setVisibility(View.GONE);

            //if the user is null and not logged in he will be sent to the chooseLoginSignUp class
            Intent intent = new Intent(ChooseLoginSignUp.this, SplashScreenActivity.class);
            startActivity(intent);

        }

    }
    //Log out function thats in the settings activity //not needed
    public void LogOut() {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut() .addOnCompleteListener(this,
                task -> updateUI(null));
    }
}
