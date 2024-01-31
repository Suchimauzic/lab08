package g313.gusev.lab08;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lstctl;
    ArrayList<mynote> lst = new ArrayList<mynote>();
    ArrayAdapter<mynote> adp;

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctx = this;

        g.notes = new DB(this, "notes.db", null, 1);

        lstctl = findViewById(R.id.listNotes);
        adp = new ArrayAdapter<mynote>(this, android.R.layout.simple_list_item_1, lst);
        lstctl.setAdapter(adp);

        lstctl.setOnItemClickListener((parent, view, position, id) -> {
            mynote n = adp.getItem(position);
            Intent i = new Intent(ctx, Main2Activity.class);
            i.putExtra("note-id", n.id);
            i.putExtra("note-id", n.txt);
        });

        update_list();
    }

    void update_list() {
        lst.clear();
        g.notes.getAllNotes(lst);
        adp.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        update_list();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_new) {
            int nid = g.notes.getMaxId() + 1;
            g.notes.addNote(nid, "Text");
            update_list();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}