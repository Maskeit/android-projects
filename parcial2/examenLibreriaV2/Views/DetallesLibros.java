package com.maskeit.libreria.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.maskeit.libreria.Libros;
import com.maskeit.libreria.Models.LibrosModel;
import com.maskeit.libreria.R;
import com.maskeit.libreria.databinding.ActivityDetallesLibrosBinding;

public class DetallesLibros extends AppCompatActivity {
    private ActivityDetallesLibrosBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDetallesLibrosBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        Bundle objeto = getIntent().getExtras(); // para recibir el objeto enviado desde lista
        LibrosModel usu = null;
        if(objeto != null){
            usu = (LibrosModel) objeto.getSerializable("libro");
            b.txtid.setText(usu.getId().toString());
            b.txttitulo.setText(usu.getTitulo().toString());
            b.txtautor.setText(usu.getAutor().toString());
            b.txteditorial.setText(usu.getEditorial().toString());
            b.txtpaginas.setText(usu.getPaginas().toString());
            b.txtisbn.setText(usu.getISBN().toString());
        }else {
            b.txtid.setText("No hay datos para mostrar.");
        }
    }
}