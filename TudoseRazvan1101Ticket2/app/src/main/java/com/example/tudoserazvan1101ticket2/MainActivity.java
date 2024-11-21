package com.example.tudoserazvan1101ticket2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Pacient> pacienti = new ArrayList<>();
    ListView lvPacienti;
    private ActivityResultLauncher<Intent> addLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        addLauncher = registerAddLauncer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_add){
            Intent intentAdd = new Intent(getApplicationContext(), AddPacientActivity.class);
            addLauncher.launch(intentAdd);
        }
        else if(item.getItemId() == R.id.menu_details){
            return true;
        }
        return true;
    }

    private ActivityResultLauncher<Intent> registerAddLauncer(){
        ActivityResultCallback<ActivityResult> callback = getCallback();
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
    }

    //Building the callback -> the object that will receives the information passed between the two activities
    private ActivityResultCallback<ActivityResult> getCallback(){
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK && result.getData() != null){
                    Pacient pacient = (Pacient) result.getData().getSerializableExtra(AddPacientActivity.PACIENT_KEY);
                    pacienti.add(pacient);
                    ArrayAdapter adapter = (ArrayAdapter) lvPacienti.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        };
    }

    public void initComponents(){
        lvPacienti = findViewById(R.id.lvPacienti);
        ArrayAdapter<Pacient> pacientArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, pacienti);
        lvPacienti.setAdapter(pacientArrayAdapter);
    }


}