package lktower.zhwilson.com.sample.sections;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import lktower.zhwilson.com.sample.R;
import lktower.zhwilson.com.viewseries.coordinate.ViewHistogram;
import lktower.zhwilson.com.viewseries.coordinate.entity.HistogramData;

/**
 * Created by zhwilson on 2017/12/6.
 */
public class HistogramActivity extends Activity {
    private ViewHistogram histogram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histogram);
        histogram = (ViewHistogram) findViewById(R.id.histogram);
        histogram.setDatas(getDatas());
    }

    private List<HistogramData> getDatas() {
        List<HistogramData> datas = new ArrayList<>();
        HistogramData data1 = new HistogramData();
        data1.setName("Kit");
        data1.setNum(1909);
        HistogramData data2 = new HistogramData();
        data2.setName("Jelly");
        data2.setNum(560);
        HistogramData data3 = new HistogramData();
        data3.setName("Lollipop");
        data3.setNum(2059);
        HistogramData data4 = new HistogramData();
        data4.setName("Froyo");
        data4.setNum(59);

        datas.add(data1);
        datas.add(data2);
        datas.add(data3);
        datas.add(data4);
        return datas;
    }
}
