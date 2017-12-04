package com.sheygam.masa_2017_04_12;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyFragment.MyFragmentListener {

    private Button addBtn, replaceBtn, removeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn = findViewById(R.id.add_btn);
        replaceBtn = findViewById(R.id.replace_btn);
        removeBtn = findViewById(R.id.remove_btn);

        addBtn.setOnClickListener(this);
        replaceBtn.setOnClickListener(this);
        removeBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_btn:
                MyFragment fragment = new MyFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.fragment_container,fragment,"ADD_FRAGMENT");
                transaction.commit();
                break;
            case R.id.replace_btn:
                MyFragment fragment1 = new MyFragment();
                fragment1.isBlack(true);
                FragmentManager manager1 = getFragmentManager();
                FragmentTransaction transaction1 = manager1.beginTransaction();
                transaction1.replace(R.id.fragment_container,fragment1,"BLACK");
                transaction1.commit();
                break;
            case R.id.remove_btn:
                MyFragment removed = (MyFragment) getFragmentManager().findFragmentByTag("BLACK");
                if(removed!= null){
                    getFragmentManager()
                            .beginTransaction()
                            .remove(removed)
                            .commit();
                }
                break;
        }
    }

    @Override
    public void onDone() {
        MyFragment fragment = (MyFragment) getFragmentManager().findFragmentByTag("ADD_FRAGMENT");
        getFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commit();
    }
}
