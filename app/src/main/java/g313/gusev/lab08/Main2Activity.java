package g313.gusev.lab08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    EditText txtctl;
    int nid;
    String ntxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtctl = findViewById(R.id.txt_content);

        Intent i = getIntent();
        nid = i.getIntExtra("note-id", 0);
        ntxt = i.getStringExtra("note-txt");

        txtctl.setText(ntxt);
    }
}