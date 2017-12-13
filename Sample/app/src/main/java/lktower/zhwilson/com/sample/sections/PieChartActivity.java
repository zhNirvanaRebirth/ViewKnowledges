package lktower.zhwilson.com.sample.sections;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import lktower.zhwilson.com.sample.R;
import lktower.zhwilson.com.viewseries.coordinate.ViewPieChart;
import lktower.zhwilson.com.viewseries.coordinate.entity.PieChartData;

/**
 * Created by mshz on 2017/12/11.
 */
public class PieChartActivity extends Activity {
    private ViewPieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        pieChart = (ViewPieChart) findViewById(R.id.pie_chart);
        pieChart.setDatas(getData());
    }

    private List<PieChartData> getData() {
        List<PieChartData> datas = new ArrayList<>();

        PieChartData gingerbread = new PieChartData();
        gingerbread.setNote("Gingerbread");
        gingerbread.setProportion(0.6f);
        gingerbread.setColor(Color.parseColor("#a22ead"));

        PieChartData iceCreamSandwich = new PieChartData();
        iceCreamSandwich.setNote("Ice Cream Sandwich");
        iceCreamSandwich.setProportion(0.6f);
        iceCreamSandwich.setColor(Color.parseColor("#9c9ba3"));

        PieChartData jellyBean = new PieChartData();
        jellyBean.setNote("Jelly Bean");
        jellyBean.setProportion(6.9f);
        jellyBean.setColor(Color.parseColor("#029688"));

        PieChartData kitKat = new PieChartData();
        kitKat.setNote("Kit Kat");
        kitKat.setProportion(15.1f);
        kitKat.setColor(Color.parseColor("#0097ed"));

        PieChartData lollipop = new PieChartData();
        lollipop.setNote("Lollipop");
        lollipop.setProportion(28.8f);
        lollipop.setColor(Color.parseColor("#ff443d"));

        PieChartData marshmallow = new PieChartData();
        marshmallow.setNote("Marshmallow");
        marshmallow.setProportion(32.2f);
        marshmallow.setColor(Color.parseColor("#ffc236"));

        PieChartData nougat = new PieChartData();
        nougat.setNote("Nougat");
        nougat.setProportion(15.8f);
        nougat.setColor(Color.parseColor("#74587e"));

        datas.add(gingerbread);
        datas.add(iceCreamSandwich);
        datas.add(jellyBean);
        datas.add(kitKat);
        datas.add(lollipop);
        datas.add(marshmallow);
        datas.add(nougat);

        return datas;
    }
}
