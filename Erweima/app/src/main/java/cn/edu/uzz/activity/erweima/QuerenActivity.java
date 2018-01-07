package cn.edu.uzz.activity.erweima;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.StringTokenizer;

public class QuerenActivity extends AppCompatActivity implements View.OnClickListener{
	private String[] typeid=new String[4];
	private int lenth=0;
	private String result;
	private Button querenBtn;
	private int type1;
	private int id1;
	private int type2;
	private int id2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_queren);
		initView();
		querenBtn=findViewById(R.id.queren);
		querenBtn.setOnClickListener(this);
	}

	private void initView() {
		result=getIntent().getStringExtra("book");
		if (result.length()>4){
			StringTokenizer st = new StringTokenizer(result, "+");
			while(st.hasMoreElements()){
				typeid[lenth]=st.nextToken();
				lenth++;
			}
			type1=Integer.parseInt(typeid[0]) ;
			id1=Integer.parseInt(typeid[1]);
			type2=Integer.parseInt(typeid[2]);
			id2=Integer.parseInt(typeid[3]);
		}else{
			StringTokenizer st = new StringTokenizer(result, "+");
			while(st.hasMoreElements()){
				typeid[lenth]=st.nextToken();
				lenth++;
			}
			type1=Integer.parseInt(typeid[0]) ;
			id1=Integer.parseInt(typeid[1]);
		}
	}

	private void queren1() {
		//1创建请求队列
		RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
		//2创建请求
		JsonObjectRequest request=new JsonObjectRequest(
				"http://123.206.230.120/Book/rentoneServ?booktype="+type1+"&bookid="+id1, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					int resultCode = response.getInt("resultCode");
					if (resultCode == 3) {
						Toast.makeText(QuerenActivity.this,"用户取消了借阅",Toast.LENGTH_SHORT).show();
					} else if (resultCode == 4) {
						Toast.makeText(QuerenActivity.this,"借阅成功",Toast.LENGTH_SHORT).show();
					} else if (resultCode == 2) {
						Toast.makeText(QuerenActivity.this,"系统错误，请稍后再试",Toast.LENGTH_SHORT).show();
					}else if (resultCode==1){
						Toast.makeText(QuerenActivity.this,"系统错误，请稍后再试",Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
			}
		});
		//3请求加入队列
		queue.add(request);
	}

	private void queren2() {
		//1创建请求队列
		RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
		//2创建请求
		JsonObjectRequest request=new JsonObjectRequest(
				"http://123.206.230.120/Book/rentoneServ?type1="+type1+"&id1="+id1+"&type2="+type2+"&id2="+id2, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					int resultCode = response.getInt("resultCode");
					if (resultCode == 3) {

					} else if (resultCode == 4) {

					} else if (resultCode == 2) {

					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {

			}
		});
		//3请求加入队列
		queue.add(request);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.queren:
				Toast.makeText(QuerenActivity.this,"123",Toast.LENGTH_SHORT).show();
				Log.e("QQQ","123");
				queren1();
				break;
			default:
				break;
		}
	}
}
