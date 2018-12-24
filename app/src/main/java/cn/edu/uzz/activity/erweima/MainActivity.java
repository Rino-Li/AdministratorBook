package cn.edu.uzz.activity.erweima;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void scan(View view){
		startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class),1);
	}
	public void scan2(View view){
		startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class),2);
	}
	public void exit(View view){
		File file= new File("/data/data/cn.edu.uzz.activity.erweima/shared_prefs","admin.xml");
		file.delete();
		System.exit(0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==1){
			if(resultCode==RESULT_OK){
				Bundle bundle=data.getExtras();
				String result=bundle.getString("result");
				Log.e("BBBB",result);
				Intent intent=new Intent();
				intent.putExtra("book",result);
				intent.setClass(MainActivity.this,QuerenActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
			}
		}else if (requestCode==2){
			if(resultCode==RESULT_OK){
				Bundle bundle=data.getExtras();
				String result=bundle.getString("result");
				Log.e("BBBB",result);
				Intent intent=new Intent();
				intent.putExtra("book",result);
				intent.setClass(MainActivity.this,HuanshuActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
			}
		}
	}

}
