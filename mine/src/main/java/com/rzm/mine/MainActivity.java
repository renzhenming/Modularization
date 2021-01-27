package com.rzm.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rzm.arouter_annotations.ARouter;
import com.rzm.arouter_annotations.Parameter;
import com.rzm.arouter_api.ParameterManager;
import com.rzm.arouter_api.RouterManager;
import com.rzm.library.bean.Worker;
import com.rzm.library.home.HomeRequest;
import com.rzm.library.news.NewsDrawable;

@ARouter(path = "/mine/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Parameter(name = "myId")
    String id;

    @Parameter
    String name;

    @Parameter(name = "/news/NewsDrawableImpl")
    NewsDrawable drawable;

    @Parameter
    Worker worker;

    @Parameter(name = "/home/HomeRequestApi")
    HomeRequest homeRequest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParameterManager.getInstance().loadParameter(this);
        ImageView image = findViewById(R.id.image);
        TextView text = findViewById(R.id.text);
        image.setImageResource(drawable.getDrawable());
        text.setText("name = " + worker.getName() + " age = " + worker.getAge());
    }

    public void jump(View view) {
        RouterManager.getInstance().build("/news/MainActivity")
                .withInt("age", 12)
                .withString("number", "32")
                .navigation(this);
    }

    public void requestWeather2(View view) {
        String weatherInfo = homeRequest2.getWeatherInfo("北京");
        System.out.println(weatherInfo);
    }

    public void requestWeather(View view) {
        HomeRequest homeRequest = (HomeRequest) RouterManager.getInstance().build("/home/HomeRequestApi").navigation(this);
        String weatherInfo = homeRequest.getWeatherInfo("上海");
        System.out.println(weatherInfo);
    }
}