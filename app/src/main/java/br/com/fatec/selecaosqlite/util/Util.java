package br.com.fatec.selecaosqlite.util;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

public class Util {

    public static final List<String> CONTINENTES = Arrays.asList("Europa", "Ásia", "África", "América", "Oceania");

    public static void carregarContinentes(Context context, Spinner spinner) {
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, CONTINENTES);
        spinner.setAdapter(adapter);
    }
}
