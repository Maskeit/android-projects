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
            @Override
            public void onClick(View view) {
                usu = usuario.getText().toString();
                pass = password.getText().toString();
                //Notificacioens popup rapido
//               mensaje =  Toast.makeText(MainActivity.this, "Hola",Toast.LENGTH_LONG);
//               mensaje.show();

//                mensaje = Toast.makeText(MainActivity.this, usu + " " + pass,
//                        Toast.LENGTH_LONG);
//                mensaje.show();
                if(usu.isEmpty() || pass.isEmpty()){
                        mensaje = Toast.makeText(MainActivity.this, "Completa los campos correctamente",
                        Toast.LENGTH_LONG);
                }
                else if (usu.equals("mike") && pass.equals("asd")){
                    mensaje = Toast.makeText(MainActivity.this, "Bienvenido",
                            Toast.LENGTH_LONG);
                    i = new Intent(MainActivity.this, bienvenida.class);
                            i.putExtra("usuario", usu);
                            i.putExtra("pass",pass);
                            startActivity(i);
                }
                else {
                    mensaje = Toast.makeText(MainActivity.this, "Usuario o contrasena incorrecta",
                    Toast.LENGTH_LONG);
                }
                    mensaje.show();
                usuario.setText("");
                password.setText("");
            }
        });
    }
}
