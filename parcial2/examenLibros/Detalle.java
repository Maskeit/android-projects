package com.maskeit.libreria;

import androidx.appcompat.app.AppCompatActivity;
import com.maskeit.libreria.databinding.ActivityDetalleBinding;
import com.maskeit.libreria.databinding.ActivityListaBinding;

import android.os.Bundle;

public class Detalle extends AppCompatActivity {
    private ActivityDetalleBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDetalleBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        Bundle objeto = getIntent().getExtras(); // para recibir el objeto enviado desde lista
        Libros usu = null;
        if(objeto != null){
            usu = (Libros) objeto.getSerializable("libro");
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
