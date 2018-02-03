package cn.edu.uzz.activity.erweima;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HuanshuActivity extends AppCompatActivity {
	private JSONObject jsonObject;
	private String booktype;
	private String bookid;
	private Button button;
	private TextView information;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_huanshu);
		initView();
	}

	private void initView() {
		information=findViewById(R.id.information);
		button=findViewById(R.id.huanshu);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				back();
			}
		});
		String result=getIntent().getStringExtra("book");
		try {
			jsonObject=new JSONObject(result);
			booktype= String.valueOf(jsonObject.getInt("booktype"));
			bookid= String.valueOf(jsonObject.getInt("bookid"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void back(){
		final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
		StringRequest stringrequest=new StringRequest(Request.Method.POST, "http://123.206.230.120/Book/backServ", new Response.Listener<String>() {
			@Override
			public void onResponse(String s) {
				try {
					JSONObject jsonObject=new JSONObject(s);
					int resultCode=jsonObject.getInt("resultCode");
					if (resultCode==1){
						Toast.makeText(HuanshuActivity.this,"此书没有被借阅",Toast.LENGTH_SHORT).show();
					}else if (resultCode==2){
						Toast.makeText(HuanshuActivity.this,"系统故障，请稍后再试",Toast.LENGTH_SHORT).show();
					}else if (resultCode==3){
						Toast.makeText(HuanshuActivity.this,"还书成功",Toast.LENGTH_SHORT).show();
					}else if (resultCode==4){
						String time=jsonObject.getString("time");
						information.setText("此书超期"+time);
						Toast.makeText(HuanshuActivity.this,"此书超期"+time,Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				Log.e("VolleyError---", volleyError.getMessage(), volleyError);
				byte[] htmlBodyBytes = volleyError.networkResponse.data;  //回应的报文的包体内容
				Log.e("VolleyError body---->", new String(htmlBodyBytes), volleyError);
				return;
			}
		}){
			protected Map<String,String> getParams(){
				Map<String,String> map=new HashMap<String, String>();
				map.put("booktype",booktype);
				map.put("bookid",bookid);
				return map;
			}
		};
		requestQueue.add(stringrequest);
	}
}
