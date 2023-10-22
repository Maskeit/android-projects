package com.example.loginbasico;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usuario, password;
    Button ingresar;
    String usu, pass;
    Toast mensaje;
    Intent i;
    String[][] nombres = {{"Miguel", "asd"}, {"Maria", "456"},{"Brenda", "789"}};
    Boolean existe = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Ingresa tus credenciales");
        setContentView(R.layout.activity_main);
        usuario = (EditText) findViewById(R.id.usuario);
        password = (EditText) findViewById(R.id.password);
        ingresar = (Button) findViewById(R.id.ingresar);
        //Implementar el onClick
        ingresar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                usu = usuario.getText().toString();
                pass = password.getText().toString();

                for(int c=0; c<nombres.length; c++){
                    if(usu.equals(nombres[c][0]) && pass.equals(nombres[c][1])){
                        //Cambiamos la bandera a true
                        existe = true;

                    }
                } if(existe){
//                    mensaje = Toast.makeText(MainActivity.this, "Bienvenido",
//                            Toast.LENGTH_LONG);
                    i = new Intent(MainActivity.this, bienvenida.class);
                    i.putExtra("usuario", usu);
                    i.putExtra("pass",pass);
                    startActivity(i);
                    existe = false;
                }else {
                    mensaje = Toast.makeText(MainActivity.this, "Usuario o contraseña incorrecta",
                            Toast.LENGTH_LONG);
                }
                mensaje.show();
                usuario.setText("");
                password.setText("");
            }
        });
    }
}
