package com.maskeit.basesdatos;

import androidx.appcompat.app.AppCompatActivity;
import com.maskeit.basesdatos.databinding.ActivityDetalleBinding;
import com.maskeit.basesdatos.databinding.ActivityListaBinding;

import android.os.Bundle;
import android.view.View;

public class detalle extends AppCompatActivity {
    private ActivityDetalleBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDetalleBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setTitle("Usuario");
        Bundle objeto = getIntent().getExtras(); // para recibir el objeto enviado desde lista
        Usuarios usu = null;
        if(objeto != null){
            usu = (Usuarios) objeto.getSerializable("usuario");
            b.txtid.setText("id: "+usu.getId().toString());
            b.txtnombre.setText("Nombre: "+usu.getNombre().toString());
            b.txtapellido.setText("Apellido"+usu.getApellido().toString());
            b.txttelefono.setText("Telefono: "+usu.getTelefono().toString());
            b.txtedad.setText("Edad: "+usu.getEdad().toString());
            b.txtcumpleanos.setText("Cumpleaños: "+usu.getBdate().toString());
            b.txtestatura.setText(usu.getEstatura().toString()+" cm");
            b.txtgenero.setText("Genero: "+usu.getGenero().toString());
        }
        setSupportActionBar(b.btnTopAppBack);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        b.btnTopAppBack.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
