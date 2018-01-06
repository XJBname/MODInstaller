package com.ICMOD.AI_ESON;

import android.app. * ;
import android.content. * ;
import android.os. * ;
import android.view. * ;
import android.widget. * ;
import java.io. * ;
import java.lang.ref. * ;
import java.net. * ;
import android.net. * ;
import android.util. * ;

public class download extends Activity implements View.OnClickListener {
    String Filename;
    int lonng;@Override
    public void onClick(View p1) {
        // TODO: Implement this method

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread() {
            public void run() {
                try {
                    Looper.prepare();
                    URL doo = new URL("http://vc.chinaz.pw/ce/列表.txt");
                    Filename = doo.toString().substring(doo.toString().lastIndexOf("/") + 1).toString();
                    String path = Environment.getExternalStorageDirectory().toString() + "//2333//" + Filename;
                    if (! (new File(path)).exists())(new File(path)).mkdir();
                    Looper.loop();
                    URLConnection httpwj = doo.openConnection();
                    InputStream is = httpwj.getInputStream();
                    byte[] bs = new byte[1024];
                    OutputStream os = new FileOutputStream(Environment.getExternalStorageDirectory().toString() + "//2333//" + Filename);
                    while ((lonng = is.read(bs)) != -1) {
                        os.write(bs, 0, lonng);
                    }
                    os.close();
                    is.close();
                    Looper.prepare();
                    Toast.makeText(getApplicationContext(), "名称:" + Filename + "====" + "长度:" + lonng + "", 1).show();
                    Looper.loop();
                } catch(Exception eee) {}
            }
        }.start();


    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(download.this);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("你确定要离开吗？");
        builder.setMessage("离开则会停止所有下载。\n暂不支持后台下载，不过您可以随意切换应用程序，不要退出本界面。");
        builder.setPositiveButton(R.string.确定, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //这里添加点击确定后的逻辑
                download.this.finish();
            }
        });
        builder.setNegativeButton(R.string.取消, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //这里添加点击确定后的逻辑
            }
        });
        builder.create().show();

        return false;
    }
}