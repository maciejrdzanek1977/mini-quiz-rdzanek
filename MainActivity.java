package com.example.miniquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static class Pytanie {
        String tresc;
        List<String> odp;
        int poprawna;

        Pytanie(String t, List<String> o, int p) {
            this.tresc = t;
            this.odp = o;
            this.poprawna = p;
        }
    }

    private List<Pytanie> wszystkiePytania = Arrays.asList(
            new Pytanie("Ile to 2 + 2?", Arrays.asList("3", "4", "5"), 1),
            new Pytanie("Stolica Polski?", Arrays.asList("Kraków", "Warszawa", "Gdańsk"), 1),
            new Pytanie("Kolor nieba?", Arrays.asList("Zielony", "Niebieski", "Czarny"), 1),
            new Pytanie("Największy ocean?", Arrays.asList("Spokojny", "Atlantycki", "Indyjski"), 0),
            new Pytanie("Ile dni ma rok?", Arrays.asList("365", "366", "360"), 0),
            new Pytanie("Ile nóg ma pies?", Arrays.asList("3", "4", "5"), 1),
            new Pytanie("Najbliższa gwiazda?", Arrays.asList("Księżyc", "Słońce", "Mars"), 1)
    );

    private List<Pytanie> pytania;
    private int index = 0;
    private int wynik = 0;

    TextView textTytul, textWynik, textPytanie;
    Button odp1, odp2, odp3, startBtn, resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTytul = findViewById(R.id.textTytul);
        textWynik = findViewById(R.id.textWynik);
        textPytanie = findViewById(R.id.textPytanie);

        odp1 = findViewById(R.id.buttonOdp1);
        odp2 = findViewById(R.id.buttonOdp2);
        odp3 = findViewById(R.id.buttonOdp3);

        startBtn = findViewById(R.id.buttonStart);
        resetBtn = findViewById(R.id.buttonReset);

        blokujOdpowiedzi(true);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rozpocznijQuiz();
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetujQuiz();
            }
        });

        odp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sprawdzOdp(0);
            }
        });

        odp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sprawdzOdp(1);
            }
        });

        odp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sprawdzOdp(2);
            }
        });
    }

    private void rozpocznijQuiz() {
        wynik = 0;
        index = 0;
        textWynik.setText("Wynik: 0");

        Collections.shuffle(wszystkiePytania);
        pytania = wszystkiePytania.subList(0, 5);

        blokujOdpowiedzi(false);

        pokazPytanie();
    }

    private void resetujQuiz() {
        wynik = 0;
        index = 0;

        textWynik.setText("Wynik: 0");
        textPytanie.setText("Naciśnij ROZPOCZNIJ QUIZ");
        odp1.setText("A");
        odp2.setText("B");
        odp3.setText("C");

        blokujOdpowiedzi(true);
    }

    private void blokujOdpowiedzi(boolean blokuj) {
        odp1.setEnabled(!blokuj);
        odp2.setEnabled(!blokuj);
        odp3.setEnabled(!blokuj);
    }

    private void pokazPytanie() {
        Pytanie p = pytania.get(index);
        textPytanie.setText(p.tresc);

        odp1.setText(p.odp.get(0));
        odp2.setText(p.odp.get(1));
        odp3.setText(p.odp.get(2));
    }

    private void sprawdzOdp(int wybor) {
        Pytanie p = pytania.get(index);

        if (wybor == p.poprawna) {
            wynik++;
        }

        index++;
        textWynik.setText("Wynik: " + wynik);

        if (index >= 5) {
            koniecQuizu();
            return;
        }

        pokazPytanie();
    }

    private void koniecQuizu() {
        textPytanie.setText("Koniec quizu! Twój wynik: " + wynik + " / 5");
        blokujOdpowiedzi(true);
        odp1.setText("A");
        odp2.setText("B");
        odp3.setText("C");
    }
}
