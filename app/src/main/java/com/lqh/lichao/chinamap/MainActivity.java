package com.lqh.lichao.chinamap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView = (MapView) findViewById(R.id.view_map);
    }

    /**
     * 加载预设数据
     */
    public void loadMapData(View view) {

        parseDemoData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        dataList -> mapView.setData(dataList),
                        throwable -> Toast.makeText(getApplicationContext(), "加载演示数据失败！", Toast.LENGTH_SHORT).show()
                );
    }

    /**
     * 异步解析演示数据
     *
     * @return 异步结果
     */
    private Observable<List<ProvinceData>> parseDemoData() {

        return Observable.create(subscriber -> {

            List<ProvinceData> demoDataList = new ArrayList<>();
            try {
                InputStreamReader isr = new InputStreamReader(getAssets().open("demo.json"), "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }
                br.close();
                isr.close();

                JSONObject root = new JSONObject(builder.toString());
                JSONArray dataList = root.optJSONArray("province");
                if (dataList != null && dataList.length() != 0) {
                    for (int i = 0; i < dataList.length(); i++) {

                        ProvinceData item = new ProvinceData();
                        JSONObject obj = dataList.optJSONObject(i);

                        item.setNumber(obj.optInt("number"));
                        item.setProvinceId(obj.optInt("provinceName"));
                        item.setProvinceId(obj.optInt("provinceId"));
                        demoDataList.add(item);
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            subscriber.onNext(demoDataList);
            subscriber.onCompleted();
        });
    }

    /**
     * 清除预设数据
     */
    public void clearMapData(View view) {
        mapView.setData(null);
    }
}
