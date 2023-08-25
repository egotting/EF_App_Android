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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mainscreen extends AppCompatActivity {

    String[] item = {"Nenhum","Pedreiro","Jovem Aprendiz","Atendente"};
    private HashMap<String, String> itemToUrlMap = new HashMap<>();

    // Na sua função onCreate ou init:
    private void initializeItemToUrlMap() {

        itemToUrlMap.put("Nenhum", "");
        itemToUrlMap.put("Pedreiro", "https://br.indeed.com/jobs?q=Pedreiro&l=nova+igua%C3%A7u%2C+rj&vjk=f25dac1a0b44aa7a");
        itemToUrlMap.put("Jovem Aprendiz", "https://br.indeed.com/jobs?q=Jovem+aprendiz&l=nova+igua%C3%A7u%2C+rj&vjk=b1eccbf30cf3ef03");
        itemToUrlMap.put("Atendente", "https://br.indeed.com/q-atendente-l-nova-igua%C3%A7u,-rj-vagas.html?vjk=155a43a7b593eb46");
        // Adicione outros conforme necessário
    }

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        initializeItemToUrlMap();
        // button e dropdown
        autoCompleteTextView = findViewById(R.id.auto_complete_text);

        // dropdown array
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);
        autoCompleteTextView.setAdapter(adapterItems);

        Button apiButton = findViewById(R.id.button);
        apiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedItem = autoCompleteTextView.getText().toString();
                String url = itemToUrlMap.get(selectedItem);

                if (url != null && !url.isEmpty()) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                } else {
                    Toast.makeText(mainscreen.this, "URL não encontrada para o item selecionado", Toast.LENGTH_SHORT).show();
                }
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