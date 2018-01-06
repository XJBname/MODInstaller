package com.ICMOD.AI_ESON;

import android.app. * ;
import android.os. * ;
import android.widget. * ;
public class AboutActivity extends Activity {
    TextView tit,
    deg,
    dsg;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutthisapp);
        tit = (TextView) findViewById(R.id.aboutthisappTextView1);
        deg = (TextView) findViewById(R.id.aboutthisappTextView2);
        dsg = (TextView) findViewById(R.id.aboutthisappTextView3);
        tit.setText(AboutActivity.this.getResources().getText(R.string.aboutAPP声明).toString());
        deg.setText(AboutActivity.this.getResources().getText(R.string.aboutAPP内容).toString());
        dsg.setText(AboutActivity.this.getResources().getText(R.string.aboutAPP人员).toString());
    }
}