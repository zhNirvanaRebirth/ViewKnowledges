package lktower.zhwilson.com.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lktower.zhwilson.com.sample.sections.ViewCoordinateActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        //view坐标系
        Button viewCoordiante = (Button) findViewById(R.id.view_coordinate);
        viewCoordiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewCoordinateActivity.class);
                startActivity(intent);
            }
        });
    }
}
