package com.ICMOD.AI_ESON;
import android.app. * ;
import android.content. * ;
import android.os. * ;
import android.view. * ;
import android.widget. * ;
import android.net. * ;


public class Jqqq extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jq);

    }
    public void jqqq(View view) {
        try {
            String url11 = "mqqwpa://im/chat?chat_type=group&uin=542464190&version=1";
startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url11)));
        } catch(Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}