package com.example.dialogs;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3, button4;
    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "Кнопка номер 1 нажата", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "Кнопка номер 2 нажата", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.mipmap.test_icon)
                        .setTitle("Заголовок")
                        .setMessage("Сделать текст красным?")
                        .setPositiveButton("Да", ((dialog, which) -> {
                            button1.setTextColor(Color.RED);
                            button2.setTextColor(Color.RED);
                            button3.setTextColor(Color.RED);
                            button4.setTextColor(Color.RED);
                            closeContextMenu();
                        }))
                        .setNegativeButton("Отмена", ((dialog, which) -> {
                            Toast toast = Toast.makeText(MainActivity.this, "Вы закрыли окно", Toast.LENGTH_SHORT);
                            toast.show();
                            closeContextMenu();
                        }))
                        .show();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] choices = {"Блок булыжника", "Блок земли", "Ведро воды", "Палка", "Железный слиток"};
                boolean[] checkedItems = {false, false, false, false, false};
                boolean[] rightAnswers = {true, false, false, true, true};

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Что можно использовать для крафта меча в Minecraft?")
                        .setIcon(R.mipmap.test_icon)
                        .setMultiChoiceItems(choices, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checkedItems[which] = isChecked;
                            }
                        })
                        .setPositiveButton("Ответить", ((dialog, which) -> {
                            Toast toast;
                            if (Arrays.equals(checkedItems, rightAnswers)) {
                                toast = Toast.makeText(MainActivity.this, "Вы молодец! Всё верно!", Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                toast = Toast.makeText(MainActivity.this, "Вы допустили ошибку!", Toast.LENGTH_SHORT);
                                toast.show();
                                button1.setVisibility(View.INVISIBLE);
                                button2.setVisibility(View.INVISIBLE);
                                button3.setVisibility(View.INVISIBLE);
                                button4.setVisibility(View.INVISIBLE);

                                textView.setVisibility(View.VISIBLE);
                                imageView.setVisibility(View.VISIBLE);
                            }
                            closeContextMenu();
                        }))
                        .setNegativeButton("Отмена", ((dialog, which) -> {
                            closeContextMenu();
                        }))
                        .show();
            }
        });
    }
}