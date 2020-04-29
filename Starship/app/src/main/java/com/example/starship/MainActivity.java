package com.example.starship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
    public Button buttonaAdd, butttonViewList, buttonSend, buttonaClear;
    public EditText Name, Weight, Count;
    public String global_result;
    public int global_result_int;
    public static List<String> arr_name = new ArrayList<String>();
    public static List<Integer> arr_weight = new ArrayList<Integer>();
    public static List<Integer> arr_count = new ArrayList<Integer>();
    public static List<Integer> help_array = new ArrayList<Integer>();
    public static List<String> help_array_names = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = (EditText) findViewById(R.id.Name);
        Weight = (EditText) findViewById(R.id.Weight);
        Count = (EditText) findViewById(R.id.Count);
        buttonaAdd = (Button) findViewById(R.id.buttonaAdd);
        butttonViewList = (Button) findViewById(R.id.butttonViewList);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonaClear = (Button) findViewById(R.id.buttonaClear);
        if (arr_weight.isEmpty()) {
            arr_weight.add(0);
        }

        if (arr_count.isEmpty()) {
            arr_count.add(0);
        }




        buttonaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NameTemp = Name.getText().toString();
                String WeightTemp = Weight.getText().toString();
                String CountTemp = Count.getText().toString();
                if (!"".equals(NameTemp) & !"".equals(WeightTemp) & !"".equals(CountTemp)) {
                    if(existA(NameTemp)) {
                        Toast toast = Toast.makeText(MainActivity.this, "Данный предмет уже существует", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        arr_name.add(NameTemp);
                        arr_weight.add(Integer.parseInt(WeightTemp));
                        arr_count.add(Integer.parseInt(CountTemp));
                        Name.setText("");
                        Weight.setText("");
                        Count.setText("");
                    }
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "Заполните все поля", Toast.LENGTH_LONG);
                    toast.show();
                }


            }
        });

        butttonViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivityMain.class);
                startActivity(intent);
            }
        });

        buttonaClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr_name.clear();
                arr_count.clear();
                arr_count.add(0);
                arr_weight.clear();
                arr_weight.add(0);
                help_array_names.clear();
                help_array.clear();
                Toast toast = Toast.makeText(MainActivity.this, "Данные успешно очищенны", Toast.LENGTH_LONG);
                toast.show();
            }
        });


        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = arr_weight.size() - 1;
                int max_weight = 30;
                int[][] СalculationArray = new int[amount + 1][max_weight + 1];

                for (int n = 0; n <= max_weight; ++n) {
                    СalculationArray[0][n] = 0;
                }
                for (int c = 1; c <= amount; ++c) {
                    for (int i = 0; i <= max_weight; ++i) {
                        СalculationArray[c][i] = СalculationArray[c - 1][i];
                        if (i >= arr_weight.get(c) && (СalculationArray[c - 1][i - arr_weight.get(c)] + arr_count.get(c) > СalculationArray[c][i])) {
                            СalculationArray[c][i] = СalculationArray[c - 1][i - arr_weight.get(c)] + arr_count.get(c);
                        }
                    }
                    int  n = amount;
                    int m = max_weight;
                    Items(n, m, СalculationArray, arr_weight);



                }

                for (int i = amount - 1; i < amount; i++) {
                    for (int j = 0; j < max_weight; j++) {
                        if (j == max_weight - 1) {
                            int result = СalculationArray[amount][max_weight];
                            global_result = Integer.toString(result);
                            global_result_int = result;
                        }
                    }
                }


                Set<Integer> set = new HashSet<>(help_array);
                help_array.clear();
                help_array.addAll(set);

                if (global_result_int < 500) {
                    Toast toast = Toast.makeText(MainActivity.this, "Миссия не может быть отправлена если планируемый доход меньше 500 красных камней", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Intent intent = new Intent(MainActivity.this, ListItemIncludedInBag.class);
                    intent.putExtra("label", global_result);
                    startActivity(intent);
                }


            }


        });


    }

    public static void Items(int n, int m, int[][] СalculationArray,  List<Integer> arr_weight) {
        if (СalculationArray[n][m] == 0) {
            return;
        }
        if (СalculationArray[n - 1][m] == СalculationArray[n][m]) {
            Items(n - 1, m, СalculationArray, arr_weight);
        } else {
            Items(n - 1, m - arr_weight.get(n), СalculationArray, arr_weight);
            help_array.add(n);

        }

    }

    private boolean existA(String a) {
        for (String s : arr_name) {
            if (a.equals(s)) {
                return true;
            }
        }
        return false;
    }


}
