package cn.edu.uzz.activity.erweima;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class HuanshuActivity extends AppCompatActivity {
	JSONObject jsonObject;
	int booktype;
	int bookid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_huanshu);
		initView();
	}

	private void initView() {
		String result=getIntent().getStringExtra("result");
		try {
			jsonObject=new JSONObject(result);
			booktype=jsonObject.getInt("booktype");
			bookid=jsonObject.getInt("bookid");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
