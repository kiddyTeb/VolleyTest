package util;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import bean.FutureBean;
import bean.JsonBean;
import bean.Result;
import bean.ResultBean;
import bean.Today;

/**
 * Created by asus on 2016/7/20.
 */
public class PraseByGson {

    public static void praseByGson(String data){
        /*Gson gson = new Gson();
        JsonBean jsonBean = gson.fromJson(data , JsonBean.class);
        Log.d("test","i have it");
        Log.d("test",jsonBean.toString());
        Log.d("test",jsonBean.getReason());
        List<ResultBean> list = jsonBean.getResult();
        for (int i= 0; i < 5 ; i++){
            ResultBean resultBean = list.get(i);
            Log.d("test",resultBean.getProvince()+resultBean.getCity()+resultBean.getDistrict());
        }*/
        Gson gson = new Gson();
        JsonBean jsonBean = gson.fromJson(data , JsonBean.class);
        Result result = jsonBean.getResult();
        Today today = result.getToday();
        Log.d("test" , today.getWeek()+today.getCity()+today.getTemperature());
        List<FutureBean> futureBeen = result.getFuture();
        for (int i=0;i<5;i++){
            FutureBean futureBean = futureBeen.get(i);
            Log.d("test",futureBean.getWeek()+futureBean.getWeek()+futureBean.getWeek());
        }
    }
}
