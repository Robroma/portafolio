package com.example.androidfirebase.controller;

import com.example.androidfirebase.dao.FireStoreDB;
import com.example.androidfirebase.view.HangedManActivity;
import com.example.androidfirebase.view.HomeActivity;
import com.example.androidfirebase.view.LoginActivity;
import com.example.androidfirebase.view.ParaulogicActivity;
import com.example.androidfirebase.view.ProfileActivity;
import com.example.androidfirebase.view.StadisticsActivity;

public class Controller {
    private static Controller controller;
    private FireStoreDB fireStore;

    private LoginActivity loginActivity;
    private HomeActivity homeActivity;
    private HangedManActivity hangedManActivity;
    private StadisticsActivity stadisticsActivity;
    private ProfileActivity profileActivity;

    private LoginController loginController;
    private HomeController homeController;
    private HangedManController hangedManController;
    private StadisticsController stadisticsController;

    private ParaulogicActivity paraulogicActivity;
    private ParaulogicController paraulogicCotroller;
    private ProfileController profileController;

    private Controller() {
        fireStore = new FireStoreDB();
    }

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public void loginActivity(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        this.loginActivity.createAllItemsGlobalWithGetters();
        this.loginController = new LoginController(this.loginActivity, fireStore);
        this.loginController.createActivityButtons();
    }

    public void paraulogicActivity(ParaulogicActivity paraulogicActivity){
        this.paraulogicActivity = paraulogicActivity;
        this.paraulogicActivity.createAllItemsGlobalWithGetters();
        this.paraulogicCotroller = new ParaulogicController(this.paraulogicActivity,fireStore);
        this.paraulogicCotroller.createActivityButtons();
    }

    public void hangedManActivity(HangedManActivity hangedManActivity) {
        this.hangedManActivity = hangedManActivity;
        this.hangedManActivity.createAllItemsGlobalWithGetters();
        this.hangedManController = new HangedManController(this.hangedManActivity, fireStore);
        this.hangedManController.createActivityButtons();
    }

    public void homeActivity(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
        this.homeActivity.createAllItemsGlobalWithGetters();
        this.homeController = new HomeController(this.homeActivity, fireStore);
        this.homeController.createActivityButtons();
    }

    public void stadisticsActivity(StadisticsActivity stadisticsActivity) {
        this.stadisticsActivity = stadisticsActivity;
        this.stadisticsActivity.createAllItemsGlobalWithGetters();
        this.stadisticsController = new StadisticsController(this.stadisticsActivity, fireStore);
        this.stadisticsController.createActivityButtons();
    }
    public void profileActivity(ProfileActivity profileActivity) {
        this.profileActivity = profileActivity;
        this.profileActivity.createAllItemsGlobalWithGetters();
        this.profileController = new ProfileController(this.profileActivity, fireStore);
        this.profileController.createActivityButtons();
    }
}
