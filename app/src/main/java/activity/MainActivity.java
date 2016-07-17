package activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liangdekai.volleytest.R;

/**
 * Created by asus on 2016/7/17.
 */
public class MainActivity extends Activity implements View.OnClickListener{
        public static final int SUCCESS_IN = 1 ;
        public static final int FAIL_OUT = 0;
        private Button mBtSend ;
        private Button mBtLoad ;
        private TextView mTvResult ;
        private ImageView mIvImage ;
        private Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case SUCCESS_IN :
                        String result = (String) msg.obj;
                        mTvResult.setText(result);
                        break;
                    case FAIL_OUT:
                        Bitmap bitmap = (Bitmap) msg.obj;
                        mIvImage.setImageBitmap(bitmap);
                        break;
                }
            }
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        mBtSend = (Button) findViewById(R.id.request);
        mBtLoad = (Button) findViewById(R.id.load);
        mIvImage = (ImageView) findViewById(R.id.photo);
        mTvResult = (TextView) findViewById(R.id.result);
        mBtSend.setOnClickListener(this);
        mBtLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.request:
                Toast.makeText(this , "click" ,Toast.LENGTH_LONG).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(MainActivity.this , "aa" ,Toast.LENGTH_LONG).show();
                        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                        StringRequest stringRequest = new StringRequest("https://www.baidu.com/", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                Log.d("test", s);
                                Message message = new Message();
                                message.what = SUCCESS_IN ;
                                message.obj = s;
                                mHandler.sendMessage(message);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.d("test",volleyError.getMessage(),volleyError);
                                Message message = new Message();
                                message.what = FAIL_OUT ;
                                mHandler.sendMessage(message);
                            }
                        });
                        requestQueue.add(stringRequest);
                    }
                }).start();
                break;
            case R.id.load :
                Toast.makeText(this , "click" ,Toast.LENGTH_LONG).show();
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                        final ImageRequest imageRequest = new ImageRequest("https://www.baidu.com/img/baidu_jgylogo3.gif" ,
                                new Response.Listener<Bitmap>(){

                                    @Override
                                    public void onResponse(Bitmap bitmap) {
                                        Message message = new Message();
                                        message.what = FAIL_OUT ;
                                        message.obj = bitmap ;
                                        mHandler.sendMessage(message);
                                        Log.d("test","success");
                                    }
                                } , 0 , 0 , Bitmap.Config.RGB_565 , new Response.ErrorListener(){

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.d("test","error");
                            }
                        });
                        requestQueue.add(imageRequest);
                    }

                }).start();*/
                        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
                            @Override
                            public Bitmap getBitmap(String s) {
                                return null;
                            }

                            @Override
                            public void putBitmap(String s, Bitmap bitmap) {

                            }
                        });
                        ImageLoader.ImageListener listener = ImageLoader.getImageListener(mIvImage , R.mipmap.menu_city , R.mipmap.ic_launcher);
                        imageLoader.get("http://img.blog.csdn.net/20140413210233046" , listener);
        }
    }
}
