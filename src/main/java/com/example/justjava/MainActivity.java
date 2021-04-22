package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void increment(View view)
    {
        quantity=quantity+1;
        display(quantity);
    }
    public void decrement(View view)
    {
        if(quantity>=1){
        quantity=quantity-1;
        display(quantity); }}
    public void submitOrder(View view)
    {
        EditText FullName=(EditText) findViewById(R.id.PersonName);
        String Name=FullName.getText().toString();
        CheckBox whipped_checkbox=(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhipped=whipped_checkbox.isChecked();
        Log.v("MainActivity","Has Whipped Cream: "+hasWhipped);
        CheckBox chocolate=(CheckBox) findViewById(R.id.checkBox);
        boolean hasChocolate=chocolate.isChecked();
        Log.v("MainActivity","Has Chocolate added"+hasChocolate);
        int price=calaculatePrice();
        String priceMessage=createOrderSummary(price,hasWhipped,hasChocolate,Name);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java Order:"+Name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void display(int number)
     {
        TextView quantityTextView =(TextView)findViewById(R.id.textView1);
        quantityTextView.setText(""+number);
    }
    private int calaculatePrice(){return  quantity*5;}

    private String createOrderSummary(int price,boolean addWhippedCream,boolean addChocolate,String Name){
        String priceMessage=getString(R.string.Order_summary_name,Name);
        priceMessage +="\nAdd whipped cream?"+ addWhippedCream;
        priceMessage +="\nAdd Chocolate ?"+ addChocolate;
        priceMessage +="\nQuantity: "+quantity;
        priceMessage +="\nTotal Amount: "+price;
        priceMessage +="\n"+getString(R.string.thank_you);
        return priceMessage;
    }

}