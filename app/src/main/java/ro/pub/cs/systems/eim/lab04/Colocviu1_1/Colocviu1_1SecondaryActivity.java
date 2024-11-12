package ro.pub.cs.systems.eim.lab04.Colocviu1_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Colocviu1_1SecondaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_activity_colocviu1_1);
        TextView textView2 = findViewById(R.id.textView2);
        Button register = findViewById(R.id.register);
        Button cancel = findViewById(R.id.cancel);

        Intent intent = getIntent();
        if (intent != null) {
            String instructions = getIntent().getStringExtra("instructions");
            textView2.setText(instructions);
        }

        register.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Register button clicked", Toast.LENGTH_SHORT).show();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("reset", true);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        cancel.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Cancel button clicked", Toast.LENGTH_SHORT).show();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("reset", false);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
