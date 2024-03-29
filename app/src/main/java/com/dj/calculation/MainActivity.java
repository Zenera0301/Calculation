package com.dj.calculation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    NavController controller;
    MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myViewModel = ViewModelProviders.of(this, new SavedStateViewModelFactory(getApplication(),this)).get(MyViewModel.class);
        controller = Navigation.findNavController(this, R.id.fragment2);
        NavigationUI.setupActionBarWithNavController(this, controller);
    }

    // test
    @Override
    public boolean onSupportNavigateUp() {
        if(controller.getCurrentDestination().getId() == R.id.questionFragment) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.quit_dialog));
            builder.setPositiveButton(R.string.dialog_positive_message, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    myViewModel.getCurrentScore().setValue(0);
                    controller.navigateUp();
                }
            });
            builder.setNegativeButton(R.string.dialog_negative_message, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }else if(controller.getCurrentDestination().getId() == R.id.titleFragment){
            // 在titleFragment点退出键，就直接finish掉
            finish();
        }else{
            controller.navigate(R.id.titleFragment);
        }
        return super.onSupportNavigateUp();
    }

    /**
     * 按下系统back键要做的事情
     */
    @Override
    public void onBackPressed() {
        onSupportNavigateUp();
    }
}






























