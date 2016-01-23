package com.example.aesencrypt;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv1,tv2,tv3;
	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv1=(TextView)findViewById(R.id.textView1);
		tv2=(TextView)findViewById(R.id.textView2);
		tv3=(TextView)findViewById(R.id.textView3);
		
		et=(EditText)findViewById(R.id.editText1);
	}

	public void onClick(View v){
		switch (v.getId()) {
		case R.id.button1:
			tv1.setText(AesUtil.encrypt(et.getText().toString(), "china"));
			tv2.setText(AesUtil.encrypt(et.getText().toString(), "china"));
			break;
		case R.id.button2:
			tv3.setText(AesUtil.decrypt(tv2.getText().toString(), "china"));
			break;

		default:
			break;
		}
	}

}
