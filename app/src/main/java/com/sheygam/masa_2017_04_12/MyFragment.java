package com.sheygam.masa_2017_04_12;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by gregorysheygam on 04/12/2017.
 */

public class MyFragment extends Fragment implements View.OnClickListener {
    private boolean isBlack = false;
    private EditText inputEmail, inputPassword;
    private Button loginBtn;
    private MyFragmentListener listener;

    public void isBlack(boolean isBlack){
        this.isBlack = isBlack;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (MyFragmentListener) activity;
        }catch (ClassCastException ex){
            throw new RuntimeException(activity.getClass().getName() + " Must implement MyFragmentListener!");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment,container,false);
        Random rnd = new Random();

        int r = rnd.nextInt(256);
        int g = rnd.nextInt(256);
        int b = rnd.nextInt(256);
        int color = isBlack ? Color.rgb(0,0,0) : Color.rgb(r,g,b);
        view.setBackgroundColor(color);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputEmail = view.findViewById(R.id.input_email);
        inputPassword = view.findViewById(R.id.input_password);
        loginBtn = view.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_btn){
            String email = inputEmail.getText().toString();
            String password = inputPassword.getText().toString();
            new LoginTask(email,password).execute();
        }
    }

    class LoginTask extends AsyncTask<Void,Void,Void>{

        private String email, password;

        public LoginTask(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getActivity(),"Login with " + email + " " + password,Toast.LENGTH_SHORT).show();
            listener.onDone();
        }
    }

    public interface MyFragmentListener{
        void onDone();
    }
}
