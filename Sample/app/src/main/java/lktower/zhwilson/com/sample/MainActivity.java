package lktower.zhwilson.com.sample;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import lktower.zhwilson.com.sample.sections.AndroidStartActivity;
import lktower.zhwilson.com.sample.sections.AnimationActivity;
import lktower.zhwilson.com.sample.sections.BounceBallActivity;
import lktower.zhwilson.com.sample.sections.Camera3dRotationActivity;
import lktower.zhwilson.com.sample.sections.CanvasActivity;
import lktower.zhwilson.com.sample.sections.CanvasGraphActivity;
import lktower.zhwilson.com.sample.sections.CircleProgressActivity;
import lktower.zhwilson.com.sample.sections.ColorActivity;
import lktower.zhwilson.com.sample.sections.CoordinateActivity;
import lktower.zhwilson.com.sample.sections.DrawableClipActivity;
import lktower.zhwilson.com.sample.sections.HistogramActivity;
import lktower.zhwilson.com.sample.sections.MatrixPolyActivity;
import lktower.zhwilson.com.sample.sections.PaintProbeActivity;
import lktower.zhwilson.com.sample.sections.PathActivity;
import lktower.zhwilson.com.sample.sections.PathApplyActivity;
import lktower.zhwilson.com.sample.sections.PathMeasureActivity;
import lktower.zhwilson.com.sample.sections.PieChartActivity;
import lktower.zhwilson.com.sample.sections.PropertyAnimationActivity;
import lktower.zhwilson.com.sample.sections.SlipActivity;
import lktower.zhwilson.com.sample.sections.ViewColorActivity;
import lktower.zhwilson.com.sample.sections.ViewCoordinateActivity;
import lktower.zhwilson.com.sample.sections.ViewProcessActivity;
import lktower.zhwilson.com.sample.sections.ZombieActivity;

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
        //matrix多边形控制
        Button matrixPoly = (Button) findViewById(R.id.matrix_poly);
        matrixPoly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MatrixPolyActivity.class);
                startActivity(intent);
            }
        });
        //camera 旋转中心的控制
        Button cameraRotation = (Button) findViewById(R.id.camera_rotation);
        cameraRotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Camera3dRotationActivity.class);
                startActivity(intent);
            }
        });
        //仿滑动删除
        Button slipView = (Button) findViewById(R.id.slip);
        slipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SlipActivity.class);
                startActivity(intent);
            }
        });
        //属性动画
        Button property = (Button) findViewById(R.id.property);
        property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PropertyAnimationActivity.class);
                startActivity(intent);
            }
        });
        //坐标
        Button coordinate = (Button) findViewById(R.id.coordinate);
        coordinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CoordinateActivity.class);
                startActivity(intent);
            }
        });
        //颜色
        Button colorProbe = (Button) findViewById(R.id.color_probe);
        colorProbe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ColorActivity.class);
                startActivity(intent);
            }
        });
        //柱状图
        Button histogram = (Button) findViewById(R.id.histogram);
        histogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HistogramActivity.class);
                startActivity(intent);
            }
        });
        //圆形进度条
        Button circleProgress = (Button) findViewById(R.id.circle_progress);
        circleProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CircleProgressActivity.class);
                startActivity(intent);
            }
        });
        //饼状图
        Button pieChart = (Button) findViewById(R.id.pie_chart);
        pieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PieChartActivity.class);
                startActivity(intent);
            }
        });
        //Paint详解
        Button paintProbe = (Button) findViewById(R.id.paint_probe);
        paintProbe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PaintProbeActivity.class);
                startActivity(intent);
            }
        });
        //Android启动动画
        Button startAnim = (Button) findViewById(R.id.start_anim);
        startAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AndroidStartActivity.class);
                startActivity(intent);
            }
        });
        //Canvas操作
        Button canvasProbe = (Button) findViewById(R.id.canvas_probe);
        canvasProbe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CanvasActivity.class);
                startActivity(intent);
            }
        });
        //丧尸跑跑跑（canvas绘制图片不同的区域）
        Button zombieRRR = (Button) findViewById(R.id.zomibe_rrr);
        zombieRRR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ZombieActivity.class);
                startActivity(intent);
            }
        });

    }

}
