package com.example.tudoserazvan1101ticket2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPacientActivity extends AppCompatActivity {
    public static final String PACIENT_KEY = "sendPacient";
    Button btn;

    EditText et_nume, et_cost;
    CheckBox cbInsurance;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initComponents();

        intent = getIntent();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInputs()){
                    Pacient pacient = buildPatientFromComponents();
                    intent.putExtra(PACIENT_KEY, pacient);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    public void initComponents(){
        btn = findViewById(R.id.btnSave);
        et_nume = findViewById(R.id.et_nume);
        et_cost = findViewById(R.id.et_cost);
        cbInsurance = findViewById(R.id.cbInsurance);
    }

    public Pacient buildPatientFromComponents(){
        String nume = et_nume.getText().toString();
        Float cost = Float.parseFloat(et_cost.getText().toString());
        Boolean insurance = cbInsurance.isChecked();
        return new Pacient(nume, cost, insurance);
    }

    public boolean validateInputs(){
        String nume = et_nume.getText().toString();
        Float cost = Float.parseFloat(et_cost.getText().toString());
        Boolean insurance = cbInsurance.isChecked();

        if(nume.isEmpty()){
            Toast.makeText(this, "Numele nu trebuie sa fie gol", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(cost < 0){
            Toast.makeText(this, "Costul trebuie sa fie mai mare sau egal decat 0", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



}