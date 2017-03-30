package lktower.zhwilson.com.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lktower.zhwilson.com.sample.sections.AnimationActivity;
import lktower.zhwilson.com.sample.sections.BounceBallActivity;
import lktower.zhwilson.com.sample.sections.CanvasGraphActivity;
import lktower.zhwilson.com.sample.sections.DrawableClipActivity;
import lktower.zhwilson.com.sample.sections.PathActivity;
import lktower.zhwilson.com.sample.sections.PathApplyActivity;
import lktower.zhwilson.com.sample.sections.PathMeasureActivity;
import lktower.zhwilson.com.sample.sections.ViewColorActivity;
import lktower.zhwilson.com.sample.sections.ViewCoordinateActivity;
import lktower.zhwilson.com.sample.sections.ViewProcessActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        //view坐标系
        Button viewCoordiante = (Button) findViewById(R.id.view_coordinate);
        viewCoordiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewCoordinateActivity.class);
                startActivity(intent);
            }
        });

        //颜色混合
        Button viewColor = (Button) findViewById(R.id.view_color);
        viewColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewColorActivity.class);
                startActivity(intent);
            }
        });
        //自定义流程
        Button viewProcess = (Button) findViewById(R.id.view_process);
        viewProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewProcessActivity.class);
                startActivity(intent);
            }
        });
        //Canvas绘制基本图形
        Button canvasGraph = (Button) findViewById(R.id.canvas_graph);
        canvasGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CanvasGraphActivity.class);
                startActivity(intent);
            }
        });
        //Paht基本操作
        Button pathBase = (Button) findViewById(R.id.path_base);
        pathBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PathActivity.class);
                startActivity(intent);
            }
        });
        //Paht花样
        Button pathMeasure = (Button) findViewById(R.id.path_measure);
        pathMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PathMeasureActivity.class);
                startActivity(intent);
            }
        });
        //Paht花样
        Button anim = (Button) findViewById(R.id.object_anim);
        anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnimationActivity.class);
                startActivity(intent);
            }
        });
        //扇形显示图片
        Button dc = (Button) findViewById(R.id.drawable_clip);
        dc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DrawableClipActivity.class);
                startActivity(intent);
            }
        });
        //path贝塞尔应用
        Button pathApply = (Button) findViewById(R.id.path_apply);
        pathApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PathApplyActivity.class);
                startActivity(intent);
            }
        });

        //path贝塞尔弹跳球
        Button pathball = (Button) findViewById(R.id.path_ball);
        pathball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BounceBallActivity.class);
                startActivity(intent);
            }
        });
    }
}
