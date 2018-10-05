package com.example.ibrahim.cowfarm.View;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrahim.cowfarm.Model.ResultModel.ResultModelLogin;
import com.example.ibrahim.cowfarm.R;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivty extends AppCompatActivity {

    @BindView(R.id.txt_money_count)
    TextView txt_money_count;

    @BindView(R.id.txt_food_count)
    TextView txt_food_count;

    @BindView(R.id.txt_medicine_count)
    TextView txt_medicine_count;

    @BindView(R.id.txt_animals_count)
    TextView txt_animals_count;

    @BindView(R.id.img_profile)
    ImageView img_profile;

    @BindView(R.id.img_resources)
    ImageView img_resources;

    @BindView(R.id.img_market)
    ImageView img_market;

    @BindView(R.id.btn_water)
    ImageView btn_water;


    @BindView(R.id.btn_electricity)
    ImageView btn_electricity;

    @BindView(R.id.btn_doctors)
    ImageView btn_doctors;

    @BindView(R.id.btn_farmers)
    ImageView btn_farmers;

    @BindView(R.id.btn_workers)
    ImageView btn_workers;

    @BindView(R.id.farm_count_level)
    TextView farm_count_level;

    @BindView(R.id.stock_level_count)
    TextView stock_level_count;

    @BindView(R.id.factorylevelcount)
    TextView factorylevelcount;

    @BindView(R.id.hospital_level_count)
    TextView hospital_level_count;


    ResultModelLogin resultModelLogin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activty);


        // Get User Inventory

        setdata(resultModelLogin);

    }

    private void setdata(ResultModelLogin resultModelLogin) {

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

    }


    @OnClick(R.id.img_profile)
    public  void openProfile() {

    }


    @OnClick(R.id.img_resources)
    public  void resources() {

    }

    @OnClick(R.id.img_market)
    public  void market() {

    }

    @OnClick(R.id.img_farm)
    public  void onFarmClick() {

    }

    @OnClick(R.id.img_stock)
    public  void onStockClick() {

    }

    @OnClick(R.id.img_factory)
    public  void onFactoryClick() {

    }

    @OnClick(R.id.img_hospital)
    public  void onHospitalClick() {

    }


}
