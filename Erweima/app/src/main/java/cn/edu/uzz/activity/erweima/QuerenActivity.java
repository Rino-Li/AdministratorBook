package cn.edu.uzz.activity.erweima;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
	}

	private void initView() {
		result=getIntent().getStringExtra("book");
		querenBtn=findViewById(R.id.queren);
		querenBtn.setOnClickListener(this);
	}

	private void queren() {
		//1创建请求队列
		RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
		//2创建请求
		JsonObjectRequest request=new JsonObjectRequest(
				"http://123.206.230.120/Book/rentoneServ?result="+result, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					int resultCode = response.getInt("resultCode");
					int errrorNum=response.getInt("errorNum");
					if (resultCode == 3) {
						Toast.makeText(QuerenActivity.this,"用户取消了借阅",Toast.LENGTH_SHORT).show();
					} else if (resultCode == 4) {
						Toast.makeText(QuerenActivity.this,"借阅成功",Toast.LENGTH_SHORT).show();
					} else if (resultCode == 2||resultCode==1) {
						Toast.makeText(QuerenActivity.this,"系统错误，请稍后再试",Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				Toast.makeText(QuerenActivity.this,"系统错误，请稍后再试",Toast.LENGTH_SHORT).show();
			}
		});
		//3请求加入队列
		queue.add(request);
	}
	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.queren:
				queren();
				break;
			default:
				break;
		}
	}
}
