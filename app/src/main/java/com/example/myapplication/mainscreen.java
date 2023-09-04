package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mainscreen extends AppCompatActivity {

    String[] sites = {"Catho", "Indeed"};
    String[] item = {"Nenhum","Pedreiro","Jovem Aprendiz","Atendente", "Professor", "Auxiliar Serviços Gerais"};

    private HashMap<String, String> itemToUrlMap2 = new HashMap<>();
    private HashMap<String, String> itemToUrlMap = new HashMap<>();

    // Na sua função onCreate ou init:

    private void cathoOptions(){
        itemToUrlMap2.put("Nenhum", "");
        itemToUrlMap2.put("Pedreiro", "https://www.catho.com.br/vagas/pedreiro/nova-iguacu-rj/");
        itemToUrlMap2.put("Jovem Aprendiz", "https://www.catho.com.br/vagas/jovem-aprendiz/nova-iguacu-rj/");
        itemToUrlMap2.put("Atendente", "https://www.catho.com.br/vagas/atendente/nova-iguacu-rj/");
        itemToUrlMap2.put("Professor", "https://www.catho.com.br/vagas/professor/nova-iguacu-rj/");
        itemToUrlMap2.put("Auxiliar Serviços Gerais", "https://www.catho.com.br/vagas/auxiliar-servicos-gerais/nova-iguacu-rj/");
    }
    private void indeedOptions() {

        itemToUrlMap.put("Nenhum", "");
        itemToUrlMap.put("Pedreiro", "https://br.indeed.com/jobs?q=Pedreiro&l=nova+igua%C3%A7u%2C+rj&vjk=f25dac1a0b44aa7a");
        itemToUrlMap.put("Jovem Aprendiz", "https://br.indeed.com/jobs?q=Jovem+aprendiz&l=nova+igua%C3%A7u%2C+rj&vjk=b1eccbf30cf3ef03");
        itemToUrlMap.put("Atendente", "https://br.indeed.com/q-atendente-l-nova-igua%C3%A7u,-rj-vagas.html?vjk=155a43a7b593eb46");
        itemToUrlMap.put("Professor", "https://br.indeed.com/jobs?q=professor+ensino+medio&l=Nova+Igua%C3%A7u%2C+RJ&vjk=28190a3cbd64462c");
        itemToUrlMap.put("Auxiliar Serviços Gerais", "https://br.indeed.com/jobs?q=Auxiliar+Servi%C3%A7os+Gerais&l=Nova+Igua%C3%A7u%2C+RJ&vjk=5b48fd29144094cf");

    }


    AutoCompleteTextView autoCompleteTextView2;
    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems2;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        indeedOptions();
        cathoOptions();

        // button of dropdown
        Button apiButton = findViewById(R.id.button);

        apiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedjob = autoCompleteTextView2.getText().toString();
                String selectedItem = autoCompleteTextView.getText().toString();
                String url = itemToUrlMap.get(selectedItem);
                String url2 = itemToUrlMap2.get(selectedItem);




                if(!selectedjob.equals("Catho")){
                    if (url != null && !url.isEmpty()) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(browserIntent);
                    } else {
                        Toast.makeText(mainscreen.this, "URL não encontrada para o item selecionado", Toast.LENGTH_SHORT).show();
                    }
                }else{
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url2));
                        startActivity(browserIntent);
                }
            }
        });






        // DROPDOWN
        autoCompleteTextView2 = findViewById(R.id.auto_complete_text2);
        autoCompleteTextView = findViewById(R.id.auto_complete_text);



        // dropdown array
        adapterItems2 = new ArrayAdapter<String>(this, R.layout.list_item, sites);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);
        autoCompleteTextView2.setAdapter(adapterItems2);
        autoCompleteTextView.setAdapter(adapterItems);



        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String site = adapterView.getItemAtPosition(i).toString();


            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();


            }
        });


    }


}