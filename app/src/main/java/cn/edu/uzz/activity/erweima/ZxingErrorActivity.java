package cn.edu.uzz.activity.erweima;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ZxingErrorActivity extends AppCompatActivity implements View.OnClickListener{

	private ImageView saomiao_return;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zxing_error);
		init();
	}

	private void init() {
		saomiao_return=findViewById(R.id.saomiao_return);
		saomiao_return.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.saomiao_return:
				finish();
				break;
			default:
				break;
		}
	}
}
