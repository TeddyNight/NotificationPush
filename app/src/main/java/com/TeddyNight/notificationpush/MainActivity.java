package com.TeddyNight.notificationpush;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(ThemeProvider.getCurrentStyle(this));
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.preference, new Preferences())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.color:
                AlertDialog.Builder listDialog = new MaterialAlertDialogBuilder(this);
                listDialog.setTitle("选择颜色");
                listDialog.setSingleChoiceItems(ThemeProvider.getThemeNameList(), ThemeProvider.getCurrentTheme(this).color, (dialog, click) -> {
                    ThemeProvider.setTheme(this, click);
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                });
                listDialog.show();
                break;
            case R.id.about:
                final AlertDialog.Builder normalDialog = new MaterialAlertDialogBuilder(this);
                normalDialog.setTitle("关于");
                normalDialog.setMessage(getResources().getString(R.string.HowToUse));
                normalDialog.setNeutralButton("GITHUB", (dialog, which) -> startActivity(new Intent().setData(Uri.parse("https://github.com/TeddyNight/NotificationPush")).setAction("android.intent.action.VIEW")));
                normalDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
