package br.com.grupouninter.ap.jogodamemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNovoJogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarNovoJogo();
        acionarEvento();

    }

    public void inicializarNovoJogo(){
        btnNovoJogo = findViewById(R.id.btnNovoJogo);
    }

    public void acionarEvento(){
        btnNovoJogo.setOnClickListener(this);
    }

    public void onClick(View v){
        Intent objIntent = new Intent(MainActivity.this, Activity_game.class);
        startActivity(objIntent);

    }

    // Código botão Voltar
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//Handle the back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            checkExit();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void checkExit()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja sair do app?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
//Ação tomada caso o usuário escolha sim.
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }




}
