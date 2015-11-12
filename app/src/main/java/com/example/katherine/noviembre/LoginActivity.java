package com.example.katherine.noviembre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private Firebase ref;
    private LinearLayout cargando;
    private Button loginfacebook;
    private CallbackManager callbackManager;
    private RelativeLayout contenedor;

    private com.facebook.GraphRequest.Callback pictureCallback = new GraphRequest.Callback() {
        @Override
        public void onCompleted(GraphResponse response) {
            Log.v("FACEBOOK", response.getRawResponse());
        }
    };

    private FacebookCallback<LoginResult> registerCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            onFacebookAccessTokenChange(loginResult.getAccessToken());
        }

        @Override
        public void onCancel() {
            mostrarError();
        }

        @Override
        public void onError(FacebookException error) {
            mostrarError();
        }
    };
    private Firebase.AuthResultHandler firebaseLogin = new Firebase.AuthResultHandler() {
        @Override
        public void onAuthenticated(AuthData authData) {
            getFacebookPicture();
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            mostrarError();
        }
    };

    private void mostrarError() {
        contenedor.setVisibility(View.VISIBLE);
        cargando.setVisibility(View.GONE);
    }


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }
        setContentView(R.layout.activity_login);

        loginfacebook = (Button) findViewById(R.id.loginfacebook);
        cargando = (LinearLayout) findViewById(R.id.cargando);
        contenedor = (RelativeLayout) findViewById(R.id.contenedor);

        callbackManager = CallbackManager.Factory.create();
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://brilliant-fire-9109.firebaseio.com/");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void onFacebookAccessTokenChange(AccessToken token) {
        if (token != null) {
            ref.authWithOAuthToken("facebook", token.getToken(), firebaseLogin);
        } else {
            ref.unauth();
        }

    }

    public void getFacebookPicture() {
        Bundle parameters = new Bundle();
        parameters.putString("redirect", "false");
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/picture", parameters, HttpMethod.GET, pictureCallback).executeAsync();
    }

    public void loginFacebook(View view) {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_photos"));
        LoginManager.getInstance().registerCallback(callbackManager, registerCallback);
        Intent intent = new Intent(this, que_pasoActivity.class);
        startActivity(intent);


        contenedor.setVisibility(View.GONE);
        cargando.setVisibility(View.VISIBLE);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


}










