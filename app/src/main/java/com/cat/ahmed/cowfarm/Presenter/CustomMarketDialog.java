package com.cat.ahmed.cowfarm.Presenter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cat.ahmed.cowfarm.Model.ApiInterface.MarketApi;
import com.cat.ahmed.cowfarm.Model.ResultModel.ResultModelBuyItem;
import com.cat.ahmed.cowfarm.R;
import com.cat.ahmed.cowfarm.Retrofit.ApiConnection;
import com.cat.ahmed.cowfarm.View.HomeActivty;


import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.cat.ahmed.cowfarm.View.HomeActivty.count;
import static com.cat.ahmed.cowfarm.View.HomeActivty.item;

public class CustomMarketDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    String userId;
    public Dialog d;
    public Button btn_buy_animal, btn_sell_animal , btn_buy_medicine , btn_sell_medicine , btn_buy_food , btn_sell_food , btn_close;
    public EditText count_animal , count_medicine ,count_food;


    Handler handler;
    ProgressDialog progress;
    ResultModelBuyItem resultModelBuyItem;


    public CustomMarketDialog(Activity a , String userId) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.userId = userId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_market);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        btn_buy_animal = (Button) findViewById(R.id.btn_buy_animal);
        btn_sell_animal = (Button) findViewById(R.id.btn_sell_animal);
        btn_buy_medicine = (Button) findViewById(R.id.btn_buy_medicine);
        btn_sell_medicine = (Button) findViewById(R.id.btn_sell_medicine);
        btn_buy_food = (Button) findViewById(R.id.btn_buy_food);
        btn_sell_food = (Button) findViewById(R.id.btn_sell_food);
        btn_close= (Button) findViewById(R.id.btn_close);
        count_animal =  findViewById(R.id.count_animal);
        count_medicine =  findViewById(R.id.count_medicine);
        count_food =  findViewById(R.id.count_food);

        btn_buy_animal.setOnClickListener(this);
        btn_sell_animal.setOnClickListener(this);
        btn_buy_medicine.setOnClickListener(this);
        btn_sell_medicine.setOnClickListener(this);
        btn_buy_food.setOnClickListener(this);
        btn_sell_food.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_buy_animal:
                item = "animals";
                count = count_animal.getText().toString().trim();
                buyitem(item , count);

                break;
            case R.id.btn_sell_animal:
                item = "animals";
                count = count_animal.getText().toString().trim();
                sellItem(item , count);
                break;
            case R.id.btn_buy_medicine:
                item = "medicine";
                count = count_medicine.getText().toString().trim();
                buyitem(item , count);
                break;
            case R.id.btn_sell_medicine:
                item = "medicine";
                count = count_medicine.getText().toString().trim();
                sellItem(item , count);

                break;
            case R.id.btn_buy_food:
                item = "food";
                count = count_food.getText().toString().trim();
                buyitem(item , count);

                break;
            case R.id.btn_sell_food:
                item = "food";
                count = count_food.getText().toString().trim();
                sellItem(item , count);

                break;
            case R.id.btn_close:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    private void sellItem(final String item, final String count) {


        progress = new ProgressDialog(c);
        progress.setTitle(R.string.pleaseWait);
        progress.setMessage(c.getString(R.string.loading));
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.dismiss();
                super.handleMessage(msg);
            }

        };

        progress.show();
        new Thread() {
            public void run() {
                //Retrofit
                ApiConnection connection = new ApiConnection();
                Retrofit retrofit = connection.connectWith();

                final HashMap<String, String> data = new HashMap<>();

                data.put("user_id", userId);
                data.put("item", item);
                data.put("quantity", count);

                final MarketApi marketApi = retrofit.create(MarketApi.class);

                final Call<ResultModelBuyItem> getInterestConnection = marketApi.sell_item(data);

                getInterestConnection.enqueue(new Callback<ResultModelBuyItem>() {
                    @Override
                    public void onResponse(Call<ResultModelBuyItem> call, Response<ResultModelBuyItem> response) {
                        try {

                            if (!response.isSuccessful()) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                       Toast.makeText(c , jObjError.getString("data"), Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                         Toast.makeText( c , e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            } else {

                                 Toast.makeText(c , "successfully", Toast.LENGTH_LONG).show();
                                HomeActivty.resultModelBuyItem = response.body();
                            }

                            progress.dismiss();

                        } // try
                        catch (Exception e) {
                            Log.i("QP", "exception : " + e.toString());
                            progress.dismiss();
                        } // catch
                    } // onResponse

                    @Override
                    public void onFailure(Call<ResultModelBuyItem> call, Throwable t) {
                        Log.i("QP", "error : " + t.toString());
                        progress.dismiss();
                    } // on Failure
                });
                // Retrofit
            }
        }.start();

    }



    private void buyitem(final String item, final String count) {


        progress = new ProgressDialog(c);
        progress.setTitle(R.string.pleaseWait);
        progress.setMessage(c.getString(R.string.loading));
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.dismiss();
                super.handleMessage(msg);
            }

        };
        progress.show();
        new Thread() {
            public void run() {
                //Retrofit
                ApiConnection connection = new ApiConnection();
                Retrofit retrofit = connection.connectWith();

                final HashMap<String, String> data = new HashMap<>();

                data.put("user_id", userId);
                data.put("item", item);
                data.put("quantity", count);

                final MarketApi marketApi = retrofit.create(MarketApi.class);

                final Call<ResultModelBuyItem> getInterestConnection = marketApi.buy_item(data);

                getInterestConnection.enqueue(new Callback<ResultModelBuyItem>() {
                    @Override
                    public void onResponse(Call<ResultModelBuyItem> call, Response<ResultModelBuyItem> response) {
                        try {

                            if (!response.isSuccessful()) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    Toast.makeText(c, jObjError.getString("data"), Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    Toast.makeText( c , e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            } else {

                                Toast.makeText(c , "successfully", Toast.LENGTH_LONG).show();
                                HomeActivty.resultModelBuyItem = response.body();
                                HomeActivty.updateUI();
                            }

                            progress.dismiss();

                        } // try
                        catch (Exception e) {
                            Log.i("QP", "exception : " + e.toString());
                            progress.dismiss();
                        } // catch
                    } // onResponse

                    @Override
                    public void onFailure(Call<ResultModelBuyItem> call, Throwable t) {
                        Log.i("QP", "error : " + t.toString());
                        progress.dismiss();
                    } // on Failure
                });
                // Retrofit
            }
        }.start();

    }


}