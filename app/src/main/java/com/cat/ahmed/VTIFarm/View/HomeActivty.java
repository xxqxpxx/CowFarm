package com.cat.ahmed.VTIFarm.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cat.ahmed.VTIFarm.Model.ApiInterface.UserApi;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelBuildingsResponse;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelBuyItem;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelFiterbyResource;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelInventory;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelLogin;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelResources;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelUpgradeRequest;
import com.cat.ahmed.VTIFarm.Presenter.CustomMarketDialog;
import com.cat.ahmed.VTIFarm.Presenter.customBuildingDialog;
import com.cat.ahmed.VTIFarm.Presenter.customPlayerDialog;
import com.cat.ahmed.VTIFarm.Presenter.customRequestsDialog;
import com.cat.ahmed.VTIFarm.Presenter.customResourcesDialog;
import com.cat.ahmed.VTIFarm.Presenter.wrapper;
import com.cat.ahmed.VTIFarm.R;
import com.cat.ahmed.VTIFarm.Retrofit.ApiConnection;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivty extends AppCompatActivity implements wrapper {



    TextView stock_level_count ,factorylevelcount , farm_count_level, hospital_level_count ,  txt_money_count , txt_food_count , txt_medicine_count , txt_animals_count;
    ImageView img_resources ,btn_water,btn_electricity, btn_doctors,btn_farmers,btn_workers,img_requests, img_market , img_stock , img_farm , img_factory , img_hospital , img_profile;

    @Override
    public void onResume(){
        super.onResume();
        updateUiBuildingLevel(userId);
    }

    CustomMarketDialog marketDialog;
    customBuildingDialog buildingDialog;
    customResourcesDialog resourceDialog;
    customRequestsDialog requestsDialog;
    customPlayerDialog customPlayerDialog;
    public static String item , count;


    Handler handler;
    ProgressDialog progress;
    List<ResultModelFiterbyResource> resultModelFiterbyResources;
    String userId;

    public static ResultModelLogin resultModelLogin ;
    public static ResultModelBuyItem resultModelBuyItem;
    ResultModelInventory resultModelInventory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activty);

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        setupUI();


        // Get User Inventory

        resultModelLogin = LoginActivity.resultModelLogin;
        setdata(resultModelLogin);

        initView();
        // getResourcesLists();


        // Init
         final Handler handler = new Handler();
         Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getNewUidata();
                handler.postDelayed(this, 5000);
            }
        };

//Start
        handler.postDelayed(runnable, 1000);
    }



    private void getNewUidata() {



        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }

        };

        new Thread() {
            public void run() {
                //Retrofit
                ApiConnection connection = new ApiConnection();
                Retrofit retrofit = connection.connectWith();

                final UserApi userApi = retrofit.create(UserApi.class);


                final Call<ResultModelInventory> getInterestConnection = userApi.getInventory(userId);

                getInterestConnection.enqueue(new Callback<ResultModelInventory>() {
                    @Override
                    public void onResponse(Call<ResultModelInventory> call, Response<ResultModelInventory> response) {
                        try {

                            if (!response.isSuccessful()) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                } catch (Exception e) {
                                }
                            } else {
                                resultModelInventory = response.body();
                                updateInventoryUi(resultModelInventory);
                            }


                        } // try
                        catch (Exception e) {
                            Log.i("QP", "exception : " + e.toString());
                        } // catch
                    } // onResponse

                    @Override
                    public void onFailure(Call<ResultModelInventory> call, Throwable t) {
                        Log.i("QP", "error : " + t.toString());
                    } // on Failure
                });
                // Retrofit
            }
        }.start();



        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }

        };

        new Thread() {
            public void run() {
                //Retrofit
                ApiConnection connection = new ApiConnection();
                Retrofit retrofit = connection.connectWith();

                final UserApi userApi = retrofit.create(UserApi.class);


                final Call<ResultModelResources> getInterestConnection = userApi.getResources(userId);

                getInterestConnection.enqueue(new Callback<ResultModelResources>() {
                    @Override
                    public void onResponse(Call<ResultModelResources> call, Response<ResultModelResources> response) {
                        try {

                            if (!response.isSuccessful()) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                } catch (Exception e) {
                                }
                            } else {

                                updateInventoryUi(response.body());
                            }


                        } // try
                        catch (Exception e) {
                            Log.i("QP", "exception : " + e.toString());
                        } // catch
                    } // onResponse

                    @Override
                    public void onFailure(Call<ResultModelResources> call, Throwable t) {
                        Log.i("QP", "error : " + t.toString());
                    } // on Failure
                });
                // Retrofit
            }
        }.start();


    }

    private void updateInventoryUi(ResultModelResources body) {

        // on / Off resources
        if (body.getData().getWater().equals("1"))
            btn_water.setImageResource(R.drawable.wateron);

        if (body.getData().getElectricity().equals("1"))
            btn_electricity.setImageResource(R.drawable.electricityon);

        if (body.getData().getDoctors().equals("1"))
            btn_doctors.setImageResource(R.drawable.doctorson);

        if (body.getData().getFarmers().equals("1"))
            btn_farmers.setImageResource(R.drawable.farmerson);

        if (body.getData().getWorkers().equals("1"))
            btn_workers.setImageResource(R.drawable.workerson);


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

        customPlayerDialog = new customPlayerDialog(this , userId);

        img_resources.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resourceDialog.show();
        }
      });

        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPlayerDialog.show();
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
                goToBuildingInfo("farm");
            }
        });

        img_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBuildingInfo("stockyard");
            }
        });

        img_factory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBuildingInfo("factory");
            }
        });

        img_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBuildingInfo("hospital");
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

            if (resultModelLogin.getData().getResources().getElectricity().equals("1"))
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


    @Override
    public AppCompatActivity getActivity() {
        return this;
    }


    @Override
    public void updateUiCounter(ResultModelBuyItem resultModelBuyItem) {
        txt_money_count.setText(resultModelBuyItem.getData().getGold());
        txt_medicine_count.setText(resultModelBuyItem.getData().getDrug());
        txt_animals_count.setText(resultModelBuyItem.getData().getAnimals());
        txt_food_count.setText(resultModelBuyItem.getData().getFood());
    }

    @Override
    public void updateUiBuilding(ResultModelUpgradeRequest body) {
        updateUiBuildingLevel(userId);
    }

    private void goToBuildingInfo(String buildingType) {
        /* Create an Intent that will start the RegisterScreen. */
        Intent mainIntent = new Intent(HomeActivty.this,BuildingActivity.class);
        mainIntent.putExtra("type", buildingType);
        HomeActivty.this.startActivity(mainIntent);
    }


    public void updateInventoryUi(ResultModelInventory resultModelBuyItem) {
        txt_money_count.setText(resultModelBuyItem.getData().getGold());
        txt_medicine_count.setText(resultModelBuyItem.getData().getDrug());
        txt_animals_count.setText(resultModelBuyItem.getData().getAnimals());
        txt_food_count.setText(resultModelBuyItem.getData().getFood());
    }

    public void updateUiBuildingLevel(String userId) {


        final String  id = userId;
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.dismiss();
                super.handleMessage(msg);
            }

        };

        new Thread() {
            public void run() {


                ApiConnection connection = new ApiConnection();
                Retrofit retrofit = connection.connectWith();

                final UserApi userApi = retrofit.create(UserApi.class);


                final Call<ResultModelBuildingsResponse> getInterestConnection = userApi.getBuilding(id);

                getInterestConnection.enqueue(new Callback<ResultModelBuildingsResponse>() {
                    @Override
                    public void onResponse(Call<ResultModelBuildingsResponse> call, Response<ResultModelBuildingsResponse> response) {
                        try {

                            if (!response.isSuccessful()) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                } catch (Exception e) {
                                }
                            } else {
                                updateNewBuildingUI(response.body());

                            }


                        } // try
                        catch (Exception e) {
                            Log.i("QP", "exception : " + e.toString());
                        } // catch
                    } // onResponse

                    @Override
                    public void onFailure(Call<ResultModelBuildingsResponse> call, Throwable t) {
                        Log.i("QP", "error : " + t.toString());
                    } // on Failure
                });
                // Retrofit

            }
        }.start();
            }

    private void updateNewBuildingUI(ResultModelBuildingsResponse body) {
        // buildings Levels
        farm_count_level.setText(body.getData().getFarm());
        hospital_level_count.setText(body.getData().getHospital());
        stock_level_count.setText(body.getData().getStockyard());
        factorylevelcount.setText(body.getData().getFactory());

    }
}

