package com.example.ibrahim.cowfarm.Model.ResultModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class data {

    @SerializedName("id")
    public String id;
    @SerializedName("village_id")
    public String village_id;
    @SerializedName("name")
    public String name;
    @SerializedName("username")
    public String username;
    @SerializedName("email")
    public String email;
    @SerializedName("admin")
    public String admin;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("player_id")
    public String player_id;

    @SerializedName("village")
    public Village village;

    @SerializedName("resources")
    public resources resources;

    @SerializedName("inventory")
    public inventory inventory;

    @SerializedName("buildings")
    public buildings buildings;

    @SerializedName("notifications")
    public List<NotificationsModel> notifications;


    public class Village{
        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("benefits")
        public String benefits;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("updated_at")
        public String updated_at;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBenefits() {
            return benefits;
        }

        public void setBenefits(String benefits) {
            this.benefits = benefits;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }

    public class resources{
        @SerializedName("id")
        public String id;
        @SerializedName("user_id")
        public String user_id;
        @SerializedName("water")
        public String water;
        @SerializedName("electricity")
        public String electricity;
        @SerializedName("workers")
        public String workers;

        @SerializedName("farmers")
        public String farmers;


        @SerializedName("doctors")
        public String doctors;


        @SerializedName("updated_at")
        public String updated_at;

        @SerializedName("created_at")
        public String created_at;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getWater() {
            return water;
        }

        public void setWater(String water) {
            this.water = water;
        }

        public String getElectricity() {
            return electricity;
        }

        public void setElectricity(String electricity) {
            this.electricity = electricity;
        }

        public String getWorkers() {
            return workers;
        }

        public void setWorkers(String workers) {
            this.workers = workers;
        }

        public String getFarmers() {
            return farmers;
        }

        public void setFarmers(String farmers) {
            this.farmers = farmers;
        }

        public String getDoctors() {
            return doctors;
        }

        public void setDoctors(String doctors) {
            this.doctors = doctors;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }

    public class inventory{

        @SerializedName("id")
        public String id;

        @SerializedName("user_id")
        public String user_id;

        @SerializedName("food")
        public String food;

        @SerializedName("drug")
        public String drug;

        @SerializedName("animals")
        public String animals;

        @SerializedName("gold")
        public String gold;

        @SerializedName("created_at")
        public String created_at;


        @SerializedName("updated_at")
        public String updated_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getFood() {
            return food;
        }

        public void setFood(String food) {
            this.food = food;
        }

        public String getDrug() {
            return drug;
        }

        public void setDrug(String drug) {
            this.drug = drug;
        }

        public String getAnimals() {
            return animals;
        }

        public void setAnimals(String animals) {
            this.animals = animals;
        }

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }

    public class buildings{

        @SerializedName("id")
        public String id;

        @SerializedName("user_id")
        public String user_id;

        @SerializedName("farm")
        public String farm;

        @SerializedName("factory")
        public String factory;

        @SerializedName("hospital")
        public String hospital;

        @SerializedName("stockyard")
        public String stockyard;

        @SerializedName("created_at")
        public String created_at;


        @SerializedName("updated_at")
        public String updated_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getFarm() {
            return farm;
        }

        public void setFarm(String farm) {
            this.farm = farm;
        }

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public String getStockyard() {
            return stockyard;
        }

        public void setStockyard(String stockyard) {
            this.stockyard = stockyard;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public data.resources getResources() {
        return resources;
    }

    public void setResources(data.resources resources) {
        this.resources = resources;
    }

    public data.inventory getInventory() {
        return inventory;
    }

    public void setInventory(data.inventory inventory) {
        this.inventory = inventory;
    }

    public data.buildings getBuildings() {
        return buildings;
    }

    public void setBuildings(data.buildings buildings) {
        this.buildings = buildings;
    }

    public List<NotificationsModel> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationsModel> notifications) {
        this.notifications = notifications;
    }
}
