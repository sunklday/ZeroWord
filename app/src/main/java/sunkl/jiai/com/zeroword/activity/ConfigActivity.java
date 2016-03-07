package sunkl.jiai.com.zeroword.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import sunkl.jiai.com.zeroword.R;
import sunkl.jiai.com.zeroword.db.DBManager;

public class ConfigActivity extends AppCompatActivity {
    private Button btnTianJia;
    private Button btnShezhi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        btnTianJia = (Button) findViewById(R.id.btn_adddb);
        btnShezhi = (Button) findViewById(R.id.btn_shezhi);

        btnTianJia.setVisibility(View.INVISIBLE);
        btnTianJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream inputStream = getResources().openRawResource(R.raw.sijiword3);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                DBManager dbManager = new DBManager(ConfigActivity.this);
                String line;
                int i = 0;
                try {
                    while (i < 500) {
                        i++;
                        line = bufferedReader.readLine();
                        String[] str = line.split("\\*", 3);
                        if (str.length < 2) {
                            continue;
                        }
                        dbManager.insert(str[0], str[1], str[2], 0);
                        for (int j = 0; j < str.length; j++) {
                            System.out.println(str[j] + " " + j);
                        }
                    }
                    if (i > 498) {
                        Toast.makeText(getApplicationContext(), "添加成功",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {

                }

            }
        });

        btnShezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigActivity.this,ConfigUserActivity.class);
                startActivity(intent);
            }
        });

    }
}
