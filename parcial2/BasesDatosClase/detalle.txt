package com.maskeit.basesdatos;

import androidx.appcompat.app.AppCompatActivity;
import com.maskeit.basesdatos.databinding.ActivityDetalleBinding;
import com.maskeit.basesdatos.databinding.ActivityListaBinding;

import android.os.Bundle;

public class detalle extends AppCompatActivity {
    private ActivityDetalleBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDetalleBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        Bundle objeto = getIntent().getExtras(); // para recibir el objeto enviado desde lista
        Usuarios usu = null;
        if(objeto != null){
            usu = (Usuarios) objeto.getSerializable("usuario");
            b.txtid.setText(usu.getId().toString());
            b.txtnombre.setText(usu.getNombre().toString());
            b.txttelefono.setText(usu.getTelefono().toString());
        }

    }
}