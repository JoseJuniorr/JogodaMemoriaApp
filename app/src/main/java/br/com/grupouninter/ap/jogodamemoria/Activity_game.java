package br.com.grupouninter.ap.jogodamemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

public class Activity_game extends AppCompatActivity implements View.OnClickListener {

    private int cardsVirados = 0; //qtd de cards virados
    private int ptsJogador = 0;
    private int ptsCPU = 0;
    private Button btnReinicarJogo;
    private TextView textJogador;
    private TextView textCPU;
    private ImageButton primeiroCardVirado;
    private ImageButton segundoCardVirado;
    private ImageButton btn0;
    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    private ImageButton btn4;
    private ImageButton btn5;
    private ImageButton btn6;
    private ImageButton btn7;
    private ImageButton btn8;
    private ImageButton btn9;
    private ImageButton btn10;
    private ImageButton btn11;
    private ImageButton btn12;
    private ImageButton btn13;
    private ImageButton btn14;
    private ImageButton btn15;
    private ImageButton btn16;
    private ImageButton btn17;
    private ImageButton btn18;
    private ImageButton btn19;

    private int capa = R.drawable.capa;
    private int rebel = R.drawable.rebel;
    private int starwarscapa = R.drawable.starwarscapa;
    private int starwarsclassic = R.drawable.starwarsclassic;

    private int darthsidious = R.drawable.darthsidious;
    private int darthvader = R.drawable.darthvader;
    private int hansolo = R.drawable.hansolo;
    private int c3po = R.drawable.c3po;
    private int luke = R.drawable.luke;
    private int obiwan = R.drawable.obiwan;
    private int leia = R.drawable.leia;
    private int r2d2 = R.drawable.r2d2;
    private int yoda = R.drawable.yoda;
    private int chewbacca = R.drawable.chewbacca;

    private ImageButton[] buttons; //array de botoes
    private int[] img; //array das imagens

    private MediaPlayer mediaPlayer;


    //metodo onCreate, chamada dos demais metodos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_game);

        playSound ();

        inicializarComponents ();
        inicializarArrays ();
        adicionarListener ();
        embaralharCards ();
        verificarPontuacao ();


        //delay de 5s para esconder as cartas no inicio do jogo
        Handler handle1 = new Handler ();
        handle1.postDelayed (new Runnable () {
            @Override
            public void run() {
                esconderCards ();

            }
        }, 5000);

        restartActivity ();

        checkEnd ();


    }


    //metodo de inicializacao dos componentes da tela
    public void inicializarComponents() {
        textJogador = findViewById (R.id.textJogador);
        textCPU = findViewById (R.id.textCPU);
        btn0 = findViewById (R.id.btn0);
        btn1 = findViewById (R.id.btn1);
        btn2 = findViewById (R.id.btn2);
        btn3 = findViewById (R.id.btn3);
        btn4 = findViewById (R.id.btn4);
        btn5 = findViewById (R.id.btn5);
        btn6 = findViewById (R.id.btn6);
        btn7 = findViewById (R.id.btn7);
        btn8 = findViewById (R.id.btn8);
        btn9 = findViewById (R.id.btn9);
        btn10 = findViewById (R.id.btn10);
        btn11 = findViewById (R.id.btn11);
        btn12 = findViewById (R.id.btn12);
        btn13 = findViewById (R.id.btn13);
        btn14 = findViewById (R.id.btn14);
        btn15 = findViewById (R.id.btn15);
        btn16 = findViewById (R.id.btn16);
        btn17 = findViewById (R.id.btn17);
        btn18 = findViewById (R.id.btn18);
        btn19 = findViewById (R.id.btn19);
        btnReinicarJogo = findViewById (R.id.btnReiniciar);
    }

    //metodo para inicializar os arrays de botoes e imagens
    public void inicializarArrays() {
        buttons = new ImageButton[]{
                btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,
                btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19
        };

        img = new int[]{
                c3po, darthsidious, darthvader, hansolo, leia, obiwan, r2d2, yoda,
                chewbacca, luke,
                c3po, darthsidious, darthvader, hansolo, leia, obiwan, r2d2, yoda,
                chewbacca, luke
        };

    }

    //metodo setonclicklistener utilizando o array de botoes com laço for
    public void adicionarListener() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener (this);
        }
    }

    //metodo para embaralhamento dos cards
    public void embaralharCards() {
        Collections.shuffle (Arrays.asList (img));
        Collections.shuffle (Arrays.asList (buttons));

        for (int i = 0; i < buttons.length; i++)
            buttons[i].setBackgroundResource (img[i]);

    }

    //adiciona a capa sobre as imagens do background utilizando setImageResource
    public void esconderCards() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setImageResource (R.drawable.starwarsclassicred);
        }

    }


    //metodo onclick, if else das jogadas, comparacao dos cards jogados e incrementa os pontos
    @Override
    public void onClick(View view) {
        ImageButton cardJogado = (ImageButton) view;

        if (cardsVirados == 0) {
            primeiroCardVirado = cardJogado;
            virarCard (cardJogado);
            cardsVirados = 1;
            primeiroCardVirado.setClickable (false);

        } else {
            segundoCardVirado = cardJogado;
            cardsVirados = 0;
            virarCard (segundoCardVirado);
            segundoCardVirado.setClickable (false);


            if (compararCards (primeiroCardVirado, segundoCardVirado)) {
                ptsJogador++;
                verificarPontuacao ();
                checkEnd ();


            } else {
                ptsCPU++;
                verificarPontuacao ();


                //delay de 1s ao esconder as cartas jogadas novamente
                Handler handle2 = new Handler ();
                handle2.postDelayed (new Runnable () {
                    @Override
                    public void run() {
                        ocultarCards (primeiroCardVirado, segundoCardVirado);
                        primeiroCardVirado.setClickable (true);
                        segundoCardVirado.setClickable (true);

                    }
                }, 1000);

            }


        }

    }


    //metodo que mostra a imagem que esta embaixo da capa, utilizando o recurso transparent do android
    public void virarCard(ImageButton cardJogado) {
        cardJogado.setImageResource (android.R.color.transparent);

    }

    //metodo booleano de comparacao dos dois cards jogados, retorna se as imagens sao iguais
    public boolean compararCards(ImageButton card1, ImageButton card2) {


        return card1.getBackground ().getConstantState ().equals (card2.getBackground ().getConstantState ());


    }

    //verificacao do pontuacao
    public void verificarPontuacao() {
        if (ptsJogador == 0) {
            textJogador.setTextColor (Color.RED);
        } else if (ptsJogador > 0) {
            textJogador.setTextColor (Color.YELLOW);
        }
        textJogador.setText ("Jogador: " + ptsJogador);

        if (ptsCPU == 0) {
            textCPU.setTextColor (Color.RED);
        } else if (ptsCPU > 0) {
            textCPU.setTextColor (Color.YELLOW);
        }
        textCPU.setText ("CPU: " + ptsCPU);
    }


    //oculta novamente os cards, setando as imagens de capa
    public void ocultarCards(ImageButton card1, ImageButton card2) {
        card1.setImageResource (R.drawable.starwarsclassicred);
        card2.setImageResource (R.drawable.starwarsclassicred);


    }
    //em andamento, falta implementar algumas opçoes
    public void checkEnd() {
        if (ptsJogador >= 10) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder (Activity_game.this);
            alertDialogBuilder.setMessage ("Fim de Jogo!");
            alertDialogBuilder.show ();

        }
    }


    //metodo para reiniciar o jogo na mesma tela
    private void restartActivity() {
        btnReinicarJogo.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent ();
                finish ();
                startActivity (intent);

            }
        });
    }


    //metodo para iniciar a musica tema Star Wars, ao voltar, reiniciar o jogo ou sair a musica é interrompida utilizando os metodos onPause e onDestroy
    private void playSound() {
        if (mediaPlayer != null) {
            mediaPlayer.reset ();
        } else {
            mediaPlayer = MediaPlayer.create (Activity_game.this, R.raw.starwarstheme);
            mediaPlayer.start ();
        }
    }

    @Override
    public void onPause() {
        super.onPause ();
        if (mediaPlayer != null) {
            mediaPlayer.stop ();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy ();
        if (mediaPlayer != null) {
            mediaPlayer.release ();
        }
    }


}
