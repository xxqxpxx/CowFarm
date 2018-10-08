package com.cat.ahmed.cowfarm.View;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cat.ahmed.cowfarm.Model.ApiInterface.VillageApi;
import com.cat.ahmed.cowfarm.Model.ResultModel.ResultModelBuyItem;
import com.cat.ahmed.cowfarm.Model.ResultModel.ResultModelFiterbyResource;
import com.cat.ahmed.cowfarm.Model.ResultModel.ResultModelLogin;
import com.cat.ahmed.cowfarm.Presenter.CustomMarketDialog;
import com.cat.ahmed.cowfarm.Presenter.customBuildingDialog;
import com.cat.ahmed.cowfarm.Presenter.customRequestsDialog;
import com.cat.ahmed.cowfarm.Presenter.customResourcesDialog;
import com.cat.ahmed.cowfarm.R;
import com.cat.ahmed.cowfarm.Retrofit.ApiConnection;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivty extends AppCompatActivity {



    TextView stock_level_count ,factorylevelcount , farm_count_level, hospital_level_count ,  txt_money_count , txt_food_count , txt_medicine_count , txt_animals_count;
    ImageView img_resources ,btn_water,btn_electricity, btn_doctors,btn_farmers,btn_workers,img_requests, img_market , img_stock , img_farm , img_factory , img_hospital , img_profile;

    CustomMarketDialog marketDialog;
    customBuildingDialog buildingDialog;
    customResourcesDialog resourceDialog;
    customRequestsDialog requestsDialog;
    public static String item , count;


    Handler handler;
    ProgressDialog progress;
    List<ResultModelFiterbyResource> resultModelFiterbyResources;
    String userId;

    ResultModelLogin resultModelLogin ;
    public static ResultModelBuyItem resultModelBuyItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activty);
        setupUI();


        // Get User Inventory

        resultModelLogin = LoginActivity.resultModelLogin;
        setdata(resultModelLogin);

        initView();
        getResourcesLists();
    }

    private void getResourcesLists() {

        for (int i = 1 ; i <= 5 ; ++i)
            getAlluserWithResource(String.valueOf(i));
    }

    private void getAlluserWithResource(final String id) {

        progress = new ProgressDialog(this);
        progress.setTitle(R.string.pleaseWait);
        progress.setMessage(getString(R.string.loading));
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



                final VillageApi marketApi = retrofit.create(VillageApi.class);

                final Call<ResultModelFiterbyResource> getInterestConnection = marketApi.getUsersByResource(id);

                getInterestConnection.enqueue(new Callback<ResultModelFiterbyResource>() {
                    @Override
                    public void onResponse(Call<ResultModelFiterbyResource> call, Response<ResultModelFiterbyResource> response) {
                        try {

                            if (!response.isSuccessful()) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                               //     Toast.makeText(c , jObjError.getString("data"), Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    //     Toast.makeText( c , e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            } else {

                           //     Toast.makeText(c , "successfully", Toast.LENGTH_LONG).show();
                                resultModelFiterbyResources.add(response.body()) ;

                            }

                            progress.dismiss();

                        } // try
                        catch (Exception e) {
                            Log.i("QP", "exception : " + e.toString());
                                     progress.dismiss();
                        } // catch
                    } // onResponse

                    @Override
                    public void onFailure(Call<ResultModelFiterbyResource> call, Throwable t) {
                        Log.i("QP", "error : " + t.toString());
                        progress.dismiss();
                    } // on Failure
                });
                // Retrofit
            }
        }.start();

    }


    private void setupUI() {

        img_resources = findViewById(R.id.img_resources);
        img_market = findViewById(R.id.img_market);
        img_requests = findViewById(R.id.img_requests);

        img_farm = findViewById(R.id.img_farm);
        img_stock = findViewById(R.id.img_stock);
        img_factory = findViewById(R.id.img_factory);
        img_hospital = findViewById(R.id.img_hospital);
        img_profile = findViewById(R.id.img_profile);

        txt_food_count = findViewById(R.id.txt_food_count);
        txt_animals_count = findViewById(R.id.txt_animals_count);
        txt_medicine_count = findViewById(R.id.txt_medicine_count);
        txt_money_count = findViewById(R.id.txt_money_count);

        stock_level_count = findViewById(R.id.stock_level_count);
        hospital_level_count = findViewById(R.id.hospital_level_count);
        farm_count_level = findViewById(R.id.farm_count_level);
        factorylevelcount = findViewById(R.id.factorylevelcount);

            btn_water= findViewById(R.id.btn_water);
            btn_electricity= findViewById(R.id.btn_electricity);
            btn_doctors= findViewById(R.id.btn_doctors);
            btn_farmers= findViewById(R.id.btn_farmers);
            btn_workers= findViewById(R.id.btn_workers);
    }

    private  void initView()
    {


        marketDialog = new CustomMarketDialog(this , userId );
        resourceDialog = new customResourcesDialog(this , userId , resultModelFiterbyResources);
        requestsDialog= new customRequestsDialog(this , userId);

        img_resources.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resourceDialog.show();
        }
      });

        img_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marketDialog.show();
            }
        });

        img_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestsDialog.show();
            }
        });

        img_farm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildingDialog = new customBuildingDialog(HomeActivty.this , userId , "farm");
                buildingDialog.show();
            }
        });

        img_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildingDialog = new customBuildingDialog(HomeActivty.this , userId , "stockyard");
                buildingDialog.show();
            }
        });

        img_factory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildingDialog = new customBuildingDialog(HomeActivty.this , userId , "factory");
                buildingDialog.show();
            }
        });

        img_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildingDialog = new customBuildingDialog(HomeActivty.this , userId , "hospital");
                buildingDialog.show();
            }
        });

        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    } // intialization of farmDialogs

    private void setdata(ResultModelLogin resultModelLogin) {

        if (resultModelLogin.getData() != null) {
            // users counts
            txt_money_count.setText(resultModelLogin.getData().getInventory().getGold());
            txt_medicine_count.setText(resultModelLogin.getData().getInventory().getDrug());
            txt_animals_count.setText(resultModelLogin.getData().getInventory().getAnimals());
            txt_food_count.setText(resultModelLogin.getData().getInventory().getFood());


            // buildings Levels
            farm_count_level.setText(resultModelLogin.getData().getBuildings().getFarm());
            hospital_level_count.setText(resultModelLogin.getData().getBuildings().getHospital());
            stock_level_count.setText(resultModelLogin.getData().getBuildings().getStockyard());
            factorylevelcount.setText(resultModelLogin.getData().getBuildings().getFactory());

            // on / Off resources
            if (resultModelLogin.getData().getResources().getWater().equals("1"))
                btn_water.setImageResource(R.drawable.wateron);

            if (resultModelLogin.getData().getResources().getWater().equals("1"))
                btn_electricity.setImageResource(R.drawable.electricityon);

            if (resultModelLogin.getData().getResources().getDoctors().equals("1"))
                btn_doctors.setImageResource(R.drawable.doctorson);

            if (resultModelLogin.getData().getResources().getFarmers().equals("1"))
                btn_farmers.setImageResource(R.drawable.farmerson);

            if (resultModelLogin.getData().getResources().getWorkers().equals("1"))
                btn_workers.setImageResource(R.drawable.workerson);

            userId = resultModelLogin.getData().getId();
        }
    }

    public static void updateUI() {
     //   Toast.makeText(HomeActivty.this , "successfully", Toast.LENGTH_LONG).show();
       // updateCounts(resultModelBuyItem);
    }

    private  void updateCounts(ResultModelBuyItem resultModelBuyItem) {
        txt_money_count.setText(resultModelBuyItem.getData().getGold());
        txt_medicine_count.setText(resultModelBuyItem.getData().getDrug());
        txt_animals_count.setText(resultModelBuyItem.getData().getAnimals());
        txt_food_count.setText(resultModelBuyItem.getData().getFood());
    }


}
