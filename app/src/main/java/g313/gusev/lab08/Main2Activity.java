package g313.gusev.lab08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText txtctl, txtId;
    int nid;
    String txt;
    TextView tvtxt, idtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtctl = findViewById(R.id.txt_content);
        idtxt = findViewById(R.id.txtId);
        //tvtxt = findViewById(R.id.currentNote);
        //txtId = findViewById(R.id.txtIdElem);

        Intent i = getIntent();
        nid = i.getIntExtra("note-id", 0);
        txt = i.getStringExtra("note-txt");

        txt = String.valueOf(txt);
        tvtxt.setText(txt);
        idtxt.setText(String.valueOf(nid));
        //txtId.setText(String.valueOf(nid));
        //txtctl.setText(txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_save) {
            g.notes.alterNote(nid, txtctl.getText().toString());
            Toast.makeText(this, "Значения поля было изменено!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.item_delete) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Delete this note");
            dialog.setMessage("Are you sure?");
            dialog.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            g.notes.deleteNote(nid);
                            finish();
                        }
                    });
            dialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnBackClick(View v) {
        finish();
    }
}