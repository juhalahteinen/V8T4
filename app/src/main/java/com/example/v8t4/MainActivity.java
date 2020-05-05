package com.example.v8t4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private Spinner sp;
    TextView teksti;
    ProgressBar pbar;
    SeekBar bar;
    TextView printti;
    BottleDispenser bd = BottleDispenser.getInstance();
    float money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        printti = (TextView) findViewById(R.id.textView2);
        teksti = (TextView) findViewById(R.id.massiskaala);
        bar = (SeekBar)findViewById(R.id.simpleSeekBar);
        pbar = (ProgressBar) findViewById(R.id.progressBar);
        sp = findViewById(R.id.spinner);

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                teksti.setText(" " + progress + "€");
                pbar.setProgress(10*progress);
                money = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        final ArrayList<String> pullotLista = new ArrayList<String>();


        pullotLista.add("Valitse pullo");
        pullotLista.add("PepsiMax 0.5 l 1.8 €");
        pullotLista.add("PepsiMax 1.5 l 2.2 €");
        pullotLista.add("Coca-ColaZero 0.5 l 2 €");
        pullotLista.add("Coca-ColaZero 1.5 l 2.5 €");
        pullotLista.add("FantaZero 0.5 l 1.95 €");

        ArrayAdapter<String> pullot = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pullotLista);
        pullot.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(pullot);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Valitse pullo")) {
                    System.out.println("Testiprintti");

                } else {
                    String valinta = parent.getItemAtPosition(position).toString();
                    String[] valinnat = valinta.split(" ");

                    String name = valinnat[0].trim();
                    String size = valinnat[1].trim();
                    String pri = valinnat[3].trim();
                    float p = Float.parseFloat(pri);

                    int luku;
                    luku = bd.buyBottle(name, size, p);

                    if (luku == 1) {
                        printti.setText("Pullo pamahti ulos!");
                    } else if (luku == 2) {
                        printti.setText("Lisää massia eka!");
                    } else {
                        printti.setText("Pulloa ei ole.");
                    }
                } sp.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addMoney (View v){
        String moneyStr = new Float(money).toString();
        bd.addMoney(money);
        printti.setText("Rahaa lisätty "+moneyStr + "€");
        bar.setProgress(0);
    }

    public void returnMoney(View v){
        float massit;
        massit = bd.returnMoney();
        String massitS = new Float(massit).toString();
        printti.setText("Sait takaisin " + massitS+ "€");
    }
}
