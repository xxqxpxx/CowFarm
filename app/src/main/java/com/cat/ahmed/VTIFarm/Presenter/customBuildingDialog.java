package com.cat.ahmed.VTIFarm.Presenter;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cat.ahmed.VTIFarm.Model.ApiInterface.MarketApi;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelGeneric;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelUpgradeInfo;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelUpgradeRequest;
import com.cat.ahmed.VTIFarm.R;
import com.cat.ahmed.VTIFarm.Retrofit.ApiConnection;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class customBuildingDialog extends Dialog implements
        android.view.View.OnClickListener {

    public wrapper wrapper;
    String building, userId;
    public Dialog d;

    ImageView img_buidling_type;
    TextView info, txt_currentLeveL, txt_money_count , farm_count_level;
    Button btn_close, upgrade ;

    Handler handler;

    ResultModelUpgradeRequest resultModelUpgradeRequest;
    ResultModelUpgradeInfo resultModelUpgradeInfo;
    ResultModelGeneric resultModelGeneric;


    public customBuildingDialog(wrapper wrapper, String userId, String building) {
        super(wrapper.getActivity());
        // TODO Auto-generated constructor stub
        this.wrapper = wrapper;
        this.userId = userId;
        this.building = building;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_farm);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        img_buidling_type = findViewById(R.id.img_buidling_type);
        btn_close = findViewById(R.id.btn_close);
        info = findViewById(R.id.info);
        upgrade = findViewById(R.id.upgrade);
        txt_money_count = findViewById(R.id.txt_money_count);
        txt_currentLeveL = findViewById(R.id.txt_currentLeveL);
        farm_count_level = findViewById(R.id.farm_count_level);

        if (building.equals("farm"))
            img_buidling_type.setImageResource(R.drawable.farm);

        else if (building.equals("factory"))
            img_buidling_type.setImageResource(R.drawable.factory);

        else if (building.equals("stockyard"))
            img_buidling_type.setImageResource(R.drawable.stockyard);

        else
            img_buidling_type.setImageResource(R.drawable.hospital);

        getBuildingInfo();


        upgrade.setOnClickListener(this);
        btn_close.setOnClickListener(this);

    }

    private void getBuildingInfo() {

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

                final HashMap<String, String> data = new HashMap<>();

                data.put("user_id", userId);
                data.put("building", building);


                final MarketApi marketApi = retrofit.create(MarketApi.class);

                final Call<ResultModelUpgradeInfo> getInterestConnection = marketApi.get_building_info(data);

                getInterestConnection.enqueue(new Callback<ResultModelUpgradeInfo>() {
                    @Override
                    public void onResponse(Call<ResultModelUpgradeInfo> call, Response<ResultModelUpgradeInfo> response) {
                        try {

                            if (!response.isSuccessful()) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    Toast.makeText(wrapper.getActivity(), jObjError.getString("data"), Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    Toast.makeText(wrapper.getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            } else {

                                resultModelUpgradeInfo = response.body();
                                info.setText(resultModelUpgradeInfo.getData().getDetails());

                                txt_currentLeveL.setText(resultModelUpgradeInfo.getData().getNext_level());
                                farm_count_level.setText(resultModelUpgradeInfo.getData().getCurrent_level());
                                txt_money_count.setText(resultModelUpgradeInfo.getData().getReq_gold());

                            }

                        } // try
                        catch (Exception e) {
                            Log.i("QP", "exception : " + e.toString());
                        } // catch
                    } // onResponse

                    @Override
                    public void onFailure(Call<ResultModelUpgradeInfo> call, Throwable t) {
                        Log.i("QP", "error : " + t.toString());
                    } // on Failure
                });
                // Retrofit
            }
        }.start();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upgrade:
                upgradeBuilding();
                break;
            case R.id.btn_close:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    private void upgradeBuilding() {


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

                final HashMap<String, String> data = new HashMap<>();

                data.put("user_id", userId);
                data.put("building", building);

                final MarketApi marketApi = retrofit.create(MarketApi.class);

                final Call<ResultModelUpgradeRequest> getInterestConnection = marketApi.upgrade_item(data);

                getInterestConnection.enqueue(new Callback<ResultModelUpgradeRequest>() {
                    @Override
                    public void onResponse(Call<ResultModelUpgradeRequest> call, Response<ResultModelUpgradeRequest> response) {
                        try {

                            if (!response.isSuccessful()) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    Toast.makeText(wrapper.getActivity(), jObjError.getString("data"), Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    Toast.makeText(wrapper.getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(wrapper.getActivity(), response.body().getData() , Toast.LENGTH_LONG).show();
                                resultModelUpgradeRequest = response.body();
                                wrapper.updateUiBuilding(response.body());
                                dismiss();

                            }


                        } // try
                        catch (Exception e) {
                            Log.i("QP", "exception : " + e.toString());
                        } // catch
                    } // onResponse

                    @Override
                    public void onFailure(Call<ResultModelUpgradeRequest> call, Throwable t) {
                        Log.i("QP", "error : " + t.toString());
                    } // on Failure
                });
                // Retrofit
            }
        }.start();

    }


}


