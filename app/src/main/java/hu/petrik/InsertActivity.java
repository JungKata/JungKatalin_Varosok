package hu.petrik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {

    private EditText nev, orszag, lakossag;
    private Button felvetel, vissza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();

        vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MyIntent = new Intent(InsertActivity.this, MainActivity.class);
                InsertActivity.this.startActivity(MyIntent);
            }
        });
    }

    private void init(){
        nev = findViewById(R.id.nev);
        orszag = findViewById(R.id.orszag);
        lakossag = findViewById(R.id.lakossag);
        felvetel = findViewById(R.id.felvetel);
        vissza = findViewById(R.id.vissza);
    }
}