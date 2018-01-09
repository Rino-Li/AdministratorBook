package cn.edu.uzz.activity.erweima;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xys.libzxing.zxing.activity.CaptureActivity;

public class MainActivity extends AppCompatActivity {
	private Button shengc;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void scan(View view){
		startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class),0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK){
			Bundle bundle=data.getExtras();
			String result=bundle.getString("result");
			Intent intent=new Intent();
			intent.putExtra("book",result);
			intent.setClass(MainActivity.this,QuerenActivity.class);
			startActivity(intent);

		}
	}

}
