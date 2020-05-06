package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.Network.ApiInterface;
import com.example.myapplication.Network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText et_email;
    private Button btn_login;
    private List<GetDeatilsListModel> getDeatilsListModels;

    String name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_email = (EditText) findViewById(R.id.et_email);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(et_email.getText().toString())){
                    getUserDetailsList();
                }else {
                    Toast.makeText(getApplicationContext(),"Please enter your github name ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getUserDetailsList() {
        ApiInterface apiInterface = RetrofitClient.getApiService(ConstantsDemo.BASE_URL);
        final ProgressDialog mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.show();
        mProgressDialog.setCancelable(false);

        name = et_email.getText().toString();

        Call<List<GetDeatilsListModel>> userCall = apiInterface.listRepos(name);
        userCall.enqueue(new Callback<List<GetDeatilsListModel>>() {

            @Override
            public void onResponse(Call<List<GetDeatilsListModel>> call, Response<List<GetDeatilsListModel>> response) {
                if (response.isSuccessful()) {

                    getDeatilsListModels = new ArrayList<>();
                    getDeatilsListModels = response.body();

                    for (int i = 0 ; i < getDeatilsListModels.size();i++){
                        String name = getDeatilsListModels.get(i).getName();
                        Intent intent = new Intent(MainActivity.this,ShowDeatils.class);
                        intent.putExtra("email",name);
                        startActivity(intent);
                    }

                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();


                }else {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Please try again Errrrrr", Toast.LENGTH_LONG).show();
                }
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<GetDeatilsListModel>> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Network Connection Error..Please try again ..", Toast.LENGTH_LONG).show();
            }
        });

    }

}

