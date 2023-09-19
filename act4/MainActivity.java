//package com.maskeit.calculadoraintermedia;
package com.example.calculadoraintermedia;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.lang.annotation.Documented;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button sumar, restar, multiplicar, dividir, tigual,
            log, ln,fact,inverso,sen,cos,tan,exp,absoluto,
            t0,t1,t2,t3,t4,t5,t6,t7,t8,t9,
            tpunto,traiz, tpotencia, tborrar, tborrartodo;
    Double op1, op2;
    int operador;
    String primero, segundo;
    EditText num;
    TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num = (EditText) findViewById(R.id.numero);
        resultado = (TextView) findViewById(R.id.operacion);
        // Operadores
        sumar = (Button) findViewById(R.id.tsumar);
        restar = (Button) findViewById(R.id.trestar);
        multiplicar = (Button) findViewById(R.id.tmultiplicar);
        dividir = (Button) findViewById(R.id.tdividir);
        // Operadores  cientificos
        log = (Button) findViewById(R.id.tlog);
        ln = (Button) findViewById(R.id.tln);
        fact = (Button) findViewById(R.id.tfact);
        inverso = (Button) findViewById(R.id.tinverso);
        sen = (Button) findViewById(R.id.tsin);
        cos = (Button) findViewById(R.id.tcos);
        tan = (Button) findViewById(R.id.ttan);
        exp = (Button) findViewById(R.id.texp);
        absoluto = (Button) findViewById(R.id.tabsolute);

        tigual = (Button) findViewById(R.id.tigual);
        tpunto = (Button) findViewById(R.id.tpunto);
        traiz = (Button) findViewById(R.id.traiz);
        tpotencia = (Button) findViewById(R.id.tpotencia);
        // Limpiar
        tborrar = (Button) findViewById(R.id.tborrar);
        tborrartodo = (Button) findViewById(R.id.tborrartodo);
        // Teclado numerico
        t0 = (Button) findViewById(R.id.t0);
        t1 = (Button) findViewById(R.id.t1);
        t2 = (Button) findViewById(R.id.t2);
        t3 = (Button) findViewById(R.id.t3);
        t4 = (Button) findViewById(R.id.t4);
        t5 = (Button) findViewById(R.id.t5);
        t6 = (Button) findViewById(R.id.t6);
        t7 = (Button) findViewById(R.id.t7);
        t8 = (Button) findViewById(R.id.t8);
        t9 = (Button) findViewById(R.id.t9);
//        Button[] buttons = new Button[10]; // Creamos un array de botones para almacenar los botones t0, t1, ..., t9
//
//        for (int i = 0; i <= 9; i++) {
//            int buttonId = getResources().getIdentifier("t" + i, "id", getPackageName());
//            buttons[i] = findViewById(buttonId);
//            buttons[i].setOnClickListener(this);
//        }


        //asigar el evento Click a los botones
        sumar.setOnClickListener(this);
        restar.setOnClickListener(this);
        multiplicar.setOnClickListener(this);
        dividir.setOnClickListener(this);
        tigual.setOnClickListener(this);
        tpunto.setOnClickListener(this);
        traiz.setOnClickListener(this);
        tpunto.setOnClickListener(this);
        tpotencia.setOnClickListener(this);
        tborrar.setOnClickListener(this);
        tborrartodo.setOnClickListener(this);
        log.setOnClickListener(this);
        ln.setOnClickListener(this);
        fact.setOnClickListener(this);
        inverso.setOnClickListener(this);
        sen.setOnClickListener(this);
        cos.setOnClickListener(this);
        tan.setOnClickListener(this);
        exp.setOnClickListener(this);
        absoluto.setOnClickListener(this);

        t0.setOnClickListener(this);
        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);
        t4.setOnClickListener(this);
        t5.setOnClickListener(this);
        t6.setOnClickListener(this);
        t7.setOnClickListener(this);
        t8.setOnClickListener(this);
        t9.setOnClickListener(this);
//        for (int i = 0; i <= 9; i++) {
//            int id = getResources().getIdentifier("t" + i, "id", getPackageName());
//            Button button = findViewById(id);
//            button.setOnClickListener(this);
//        }
        Switch switchMostrarBotones = findViewById(R.id.switchSci);
        LinearLayout botonesSci1 = findViewById(R.id.botonesSci1);
        LinearLayout botonesSci2 = findViewById(R.id.botonesSci2);
        switchMostrarBotones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Si el Switch está activado, muestra los botones científicos
                    botonesSci1.setVisibility(View.VISIBLE);
                    botonesSci2.setVisibility(View.VISIBLE);
                } else {
                    // Si el Switch está desactivado, oculta los botones científicos
                    botonesSci1.setVisibility(View.GONE);
                    botonesSci2.setVisibility(View.GONE);
                }
            }
        });
    }

    // metodo para encontrar el factorial de un numero
    public double factorial(double op1){
        if(op1 < 0){
            return Double.NaN;
        }
        if (op1 == 0){
            return 1.0;
        }
        double factorial = 1.0;
        for (int i = 1; i<=op1; i++){
            factorial*=i;
        }
        return factorial;
    }

    @Override
    public void onClick(View v) {
        primero = num.getText().toString();
        int id = v.getId();

        if (!primero.isEmpty()){
            //Operandos
                    if(v.getId()==R.id.tsumar){
                        op1 = Double.parseDouble(primero);
                        resultado.setText(num.getText()+"+");
                        num.setText("");
                        operador = 0; //sumar
                    }
                    if(v.getId()==R.id.trestar){
                        op1 = Double.parseDouble(primero);
                        resultado.setText(num.getText()+"-");
                        num.setText("");
                        operador = 1; // restar
                    }
                    if(v.getId()==R.id.tmultiplicar){
                        op1 = Double.parseDouble(primero);
                        resultado.setText(num.getText()+"x");
                        num.setText("");
                        operador = 2; //multiplicar
                    }
                    if(v.getId()==R.id.tdividir){
                        op1 = Double.parseDouble(primero);
                        resultado.setText(num.getText()+"/");
                        num.setText("");
                        operador = 3; //dividir
                    }
                    if(v.getId()==R.id.tpotencia){
                        op1 = Double.parseDouble(primero);
                        resultado.setText(num.getText()+"^");
                        num.setText("");
                        operador = 4; //potencia
                    }
                    //operaciones cientificas
                    if(v.getId()==R.id.tlog){
                        op1 = Double.parseDouble(primero);
                        //resultado.setText(num.getText()+" log10");
                        num.setText("");
                        resultado.setText(Math.log10(op1)+"");
                    }
                    if(v.getId()==R.id.tln){
                        op1 = Double.parseDouble(primero);
                        //resultado.setText(num.getText()+" ln");
                        num.setText("");
                        resultado.setText(Math.log(op1)+"");
                    }
                    if(v.getId()==R.id.tabsolute){
                        op1 = Double.parseDouble(primero);
                        //resultado.setText(num.getText()+" abs");
                        num.setText("");
                        resultado.setText(Math.abs(op1)+"");
                    }
                    if(v.getId()==R.id.tinverso){
                        op1 = Double.parseDouble(primero);
                        //resultado.setText(num.getText()+" inverso");
                        num.setText("");
                        resultado.setText((1.0/op1)+"");
                    }
                    if(v.getId()==R.id.tsin){
                        op1 = Double.parseDouble(primero);
                        double rad = Math.toRadians(op1);
                        num.setText("");
                        resultado.setText(Math.sin(rad)+"");
                    }if(v.getId()==R.id.tcos){
                        op1 = Double.parseDouble(primero);
                        double rad = Math.toRadians(op1);
                        num.setText("");
                        resultado.setText(Math.cos(rad)+"");
                    }if(v.getId()==R.id.ttan){
                        op1 = Double.parseDouble(primero);
                        double rad = Math.toRadians(op1);
                        num.setText("");
                        resultado.setText(Math.tan(rad)+"");
                    }if(v.getId()==R.id.texp){
                        op1 = Double.parseDouble(primero);
                        num.setText("");
                        resultado.setText(Math.pow(10,op1)+"");
                    }if(v.getId()==R.id.traiz){
                        op1 = Double.parseDouble(primero);
                        num.setText("");
                        resultado.setText(Math.sqrt(op1)+"");
                    }if(v.getId()==R.id.tfact){
                        op1 = Double.parseDouble(primero);
                        num.setText("");
                        resultado.setText(factorial(op1)+"");
                    }
            if(v.getId()==R.id.tigual){
                    if(operador==0){
                        op2 = Double.parseDouble(primero);
                        resultado.setText((op1 + op2)+"");
                    }
                    if(operador==1){
                        op2 = Double.parseDouble(primero);
                        resultado.setText((op1 - op2)+"");
                    }
                    if(operador==2){
                        op2 = Double.parseDouble(primero);
                        resultado.setText((op1 * op2)+"");
                    }
                    if(operador==3){
                        op2 = Double.parseDouble(primero);
                        resultado.setText((op1 / op2)+"");
                    }
                    if(operador==4){
                        op2 = Double.parseDouble(primero);
                        resultado.setText(Math.pow(op1,op2)+"");
                    }
                    num.setText("");
            }
        } else if (primero.isEmpty()) {
            if(v.getId() ==R.id.trestar){
                String ct = num.getText().toString();
                if(ct.isEmpty()) {
                    num.setText("-");
                }
            }
        }
        //borrar y borrartodo
        if(id == R.id.tborrar){
            num.setText("");
        } else if (id == R.id.tborrartodo) {
            num.setText("");
            resultado.setText("");
        }
        //Teclado numerico
        if(id == R.id.t0){
            num.setText(num.getText()+"0");
        }else if (id==R.id.t1){
            num.setText(num.getText()+"1");
        } else if (id == R.id.t2) {
            num.setText(num.getText()+"2");
        }else if (id == R.id.t3) {
            num.setText(num.getText()+"3");
        }else if (id == R.id.t4) {
            num.setText(num.getText()+"4");
        }else if (id == R.id.t5) {
            num.setText(num.getText()+"5");
        }else if (id == R.id.t6) {
            num.setText(num.getText()+"6");
        }else if (id == R.id.t7) {
            num.setText(num.getText()+"7");
        }else if (id == R.id.t8) {
            num.setText(num.getText()+"8");
        }else if (id == R.id.t9) {
            num.setText(num.getText()+"9");
        }else if (id == R.id.tpunto) {
            String textoActual = num.getText().toString();
            if(!textoActual.contains(".")){ //Primero verifica que el edit no contenga un punto
                if(!textoActual.isEmpty()){ // Luego verifica que no este vacio
                    num.setText(textoActual + "."); // si no es vacio por ej: 40, entonces agrega el "." => 40.3
                }else {
                    num.setText("0."); //si es vacio, entonces empieza con 0.
                }
            }
        }
    }


}
