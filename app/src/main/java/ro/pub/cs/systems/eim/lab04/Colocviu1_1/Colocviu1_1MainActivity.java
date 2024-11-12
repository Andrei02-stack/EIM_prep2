package ro.pub.cs.systems.eim.lab04.Colocviu1_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Colocviu1_1MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_colocviu1_1_main);

        Button northButton = findViewById(R.id.north);
        Button southButton = findViewById(R.id.south);
        Button eastButton = findViewById(R.id.east);
        Button westButton = findViewById(R.id.west);
        Button navigateToSecondaryActivityButton = findViewById(R.id.navigateToSecondaryActivity);
        TextView textView = findViewById(R.id.textView);
        TextView buttonPressedCount = findViewById(R.id.buttonPressedCount);

        View.OnClickListener buttonClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String currentText = textView.getText().toString();
                String buttonText = ((Button) v).getText().toString();
                if (!currentText.isEmpty()) {
                    currentText += ", ";
                }
                textView.setText(currentText + buttonText);
                count++;
                buttonPressedCount.setText("Button pressed count: " + count);
            }
        };

        northButton.setOnClickListener(buttonClickListener);
        southButton.setOnClickListener(buttonClickListener);
        eastButton.setOnClickListener(buttonClickListener);
        westButton.setOnClickListener(buttonClickListener);

        // Replace your existing navigation code
        navigateToSecondaryActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Colocviu1_1SecondaryActivity.class);
            intent.putExtra("instructions", textView.getText().toString());
            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        TextView textView = findViewById(R.id.textView);
        TextView buttonPressedCount = findViewById(R.id.buttonPressedCount);
        savedInstanceState.putString("textView", textView.getText().toString());
        savedInstanceState.putString("buttonPressedCount", buttonPressedCount.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView textView = findViewById(R.id.textView);
        TextView buttonPressedCount = findViewById(R.id.buttonPressedCount);
        textView.setText(savedInstanceState.getString("textView"));
        buttonPressedCount.setText(savedInstanceState.getString("buttonPressedCount"));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView textView = findViewById(R.id.textView);
        TextView buttonPressedCount = findViewById(R.id.buttonPressedCount);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            boolean reset = data.getBooleanExtra("reset", false);
            if (reset) {
                textView.setText("");
                buttonPressedCount.setText("Button pressed count: " + count);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}