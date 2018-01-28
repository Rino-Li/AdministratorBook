package cn.edu.uzz.activity.erweima;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class QuerenActivity extends AppCompatActivity implements View.OnClickListener {
	private String[] typeid = new String[4];
	private int lenth = 0;
	private String result;
	private Button querenBtn;
	private JSONObject data = new JSONObject();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_queren);
		initView();
	}

	private void initView() {
		result = getIntent().getStringExtra("book");
		Log.e("BBBB", result);
		querenBtn = findViewById(R.id.queren);
		querenBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.queren:
				queren();
				break;
			default:
				break;
		}
	}

	private void queren() {
		final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
		StringRequest stringrequest=new StringRequest(Request.Method.POST, "http://192.168.218.2:8080/Book/rentoneServ", new Response.Listener<String>() {
			@Override
			public void onResponse(String s) {
				try {
					JSONObject jsonObject=new JSONObject(s);
					Log.e("BBBB",s);
					int rentLength=jsonObject.getInt("rentLength");
					int errorNum=jsonObject.getInt("errorNum");
					if(errorNum==0){
						Toast.makeText(QuerenActivity.this,"借阅成功",Toast.LENGTH_SHORT).show();
					}else {
						Toast.makeText(QuerenActivity.this,errorNum+" 本借阅失败",Toast.LENGTH_SHORT).show();
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
				map.put("result",result);
				return map;
			}
		};
		requestQueue.add(stringrequest);
	}
}
