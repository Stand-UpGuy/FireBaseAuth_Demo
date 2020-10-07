package com.example.pizza_configurator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "id.ul.myfirstapp.EXTRA_MESSAGE";
    Pizza pizza;
    TextView total;
    double total_Price;
    TextView deliveryMessage;
    String messageforPizza = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pizza = new Pizza();
        total = findViewById(R.id.total);
        deliveryMessage = findViewById(R.id.deliveryMessage);
    }

    public void radioClicked(View view) {

        boolean clicked = ((RadioButton) view).isChecked();
        switch(view.getId()){
            case R.id.rb1 : if(clicked){
                pizza.setPizza_size(5.0);
                break;
            }
            case R.id.rb2 : if(clicked){
                pizza.setPizza_size(8.0);
                break;
            }
            case R.id.rb3 : if(clicked){
                pizza.setPizza_size(10.0);
                break;
            }
        }
        total.setText("Total Price : " + calculate_Price());

    }


    public String extraClick(View view) {
        String extraString = "";
        boolean extras = ((CheckBox) view).isChecked();
        switch(view.getId()){
            case R.id.cb1: if(extras){pizza.setPizza_cheese(2.0); extraString = "with extra cheese";} else pizza.setPizza_cheese(0); break;
            case R.id.cb2: if(extras){pizza.setPizza_pepperoni(3.0); extraString = "with extra pepperoni";} else pizza.setPizza_pepperoni(0); break;
            case R.id.cb3: if(extras){pizza.setPizza_pineapple(2); extraString = "with extra pineapple";} else pizza.setPizza_pineapple(0);break;

        }
        total.setText("Total Price : " + calculate_Price());
        return extraString;

    }

    public void switchClick(View view){
        Switch deliverySwitch = ((Switch) view);
        if(deliverySwitch.isChecked())
            deliveryMessage.setText("Order will be delivered ASAP!");
        else deliveryMessage.setText("");
    }

    private double calculate_Price()
    {
        total_Price = pizza.getPizza_size() + pizza.getPizza_pepperoni() + pizza.getPizza_cheese() + pizza.getPizza_pineapple();
        return total_Price;
    }

    public String setPizza_Message(View view)
    {
        String extras = extraClick(view);
        return extras;
    }

    public String getPizza_Message(){
        return messageforPizza;
    }

    public void pizzaMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        Intent intent = new Intent(this, PizzaMessage.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


    }



}