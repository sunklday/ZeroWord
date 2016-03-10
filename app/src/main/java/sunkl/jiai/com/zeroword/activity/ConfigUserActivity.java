package sunkl.jiai.com.zeroword.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

import java.security.PrivateKey;

import sunkl.jiai.com.zeroword.R;
import sunkl.jiai.com.zeroword.db.DBManager;

/**
 * 设置每日的单词数
 */
public class ConfigUserActivity extends AppCompatActivity {

    private Switch aSwitch;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_user);
        aSwitch = (Switch) findViewById(R.id.swh_number);
        editText = (EditText) findViewById(R.id.edt_wordnumber);

        DBManager dbManager = new DBManager(this);
        Cursor cursor = dbManager.userSelect();
        String number = "";
        while (cursor.moveToNext()) {
            editText.setText(cursor.getString(cursor.getColumnIndex("amount")));
        }

    }
    //finish activity的时候保存数据
    @Override
    public void finish() {
        String number = editText.getText().toString();
        DBManager dbManager = new DBManager(this);
        dbManager.userAmountUpdate(number);
        super.finish();
    }
}
