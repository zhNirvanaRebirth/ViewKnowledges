package lktower.zhwilson.com.sample.sections;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import lktower.zhwilson.com.sample.R;
import lktower.zhwilson.com.viewseries.coordinate.MatrixProbe;

/**
 * Created by mshz on 2018/1/2.
 */
public class MatrixProbeActivity extends Activity {
    private MatrixProbe matrixProbe;
    //    private Button chCaremaPosition;
    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_probe);
        matrixProbe = (MatrixProbe) findViewById(R.id.matrix_probe);
//        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.point_count);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                int pointCount = 4;
//                switch (checkedId){
//                    case R.id.zero:
//                        pointCount = 0;
//                    break;
//                    case R.id.one:
//                        pointCount = 1;
//                    break;
//                    case R.id.two:
//                        pointCount = 2;
//                    break;
//                    case R.id.three:
//                        pointCount = 3;
//                    break;
//                    case R.id.four:
//                        pointCount = 4;
//                    break;
//                }
//                matrixProbe.setPointCount(pointCount);
//            }
//        });
//
//        chCaremaPosition = (Button) findViewById(R.id.change_camera_position);
//        chCaremaPosition.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("zhwilson", chCaremaPosition.getText().toString());
//                if ("increase".equals(chCaremaPosition.getText().toString())){
//                    chCaremaPosition.setText("decrease");
//                    matrixProbe.setIncreaseZ(false);
//                } else {
//                    chCaremaPosition.setText("increase");
//                    matrixProbe.setIncreaseZ(true);
//                }
//            }
//        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.axis_switch);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.axis_x:
                        matrixProbe.setAxis(1);
                        break;
                    case R.id.axis_y:
                        matrixProbe.setAxis(2);
                        break;
                    case R.id.axis_z:
                        matrixProbe.setAxis(3);
                        break;
                }
            }
        });
        startBtn = (Button) findViewById(R.id.start_rotate);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matrixProbe.startRotate();
            }
        });
    }
}
