package g313.gusev.lab08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText editNode, editId;
    int nid;
    String txt;
    TextView txtNote, txtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editNode = findViewById(R.id.editTxtNode);
        editId = findViewById(R.id.editTxtId);
        txtNote = findViewById(R.id.currentTxtNote);
        txtId = findViewById(R.id.currentTxtId);

        Intent i = getIntent();
        nid = i.getIntExtra("note-id", 0);
        txt = i.getStringExtra("note-txt");

        txt = String.valueOf(txt);

        txtNote.setText(txt);
        txtId.setText(String.valueOf(nid));
        editId.setText(String.valueOf(nid));
        editNode.setText(txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        int newInt;

        if (id == R.id.item_save) {
            try {
                newInt = Integer.valueOf(editId.getText().toString());
            }
            catch (Exception ex) {
                Toast.makeText(this, "Это конечно перебор....", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
            }

            g.notes.alterNote(nid, editId.getText().toString(), editNode.getText().toString());
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