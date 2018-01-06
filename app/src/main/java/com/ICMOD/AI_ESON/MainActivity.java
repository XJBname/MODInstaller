package com.ICMOD.AI_ESON;

import android.content.*;
import android.content.pm.*;
import android.graphics.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v4.content.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import com.ICMOD.AI_ESON.*;
import java.io.*;
import android.support.v7.app.ActionBar;
import org.json.*;
import android.support.v7.app.*;
import android.support.v4.widget.*;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.util.*;
import java.util.*;
import android.*;
import android.support.v4.view.*;
import android.view.animation.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
	int aaa = 0,rec=2,
    backkk;
	Toolbar toolbar;
	Timer timer = new Timer(),timerr = new Timer();
    String Stb;
	NavigationView navigationView;
	ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
			toolbar = (Toolbar) findViewById(R.id.toolbar);
			
			super.onCreate(savedInstanceState);
		
		
            setContentView(R.layout.dawer_main);
			setSupportActionBar(toolbar);
//显示导航按钮
			
		
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
			this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
			try{
			if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
			Toast.makeText(MainActivity.this,"请授予权限。",Toast.LENGTH_LONG).show();
		ActivityCompat.requestPermissions(this,
										  new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
										  MY_PERMISSIONS_REQUEST_CALL_PHONE);
		
}
            backkk = 0;
			TextView bt = (TextView) findViewById(R.id.mainTextView);
            Button bt1 = (Button) findViewById(R.id.mainButton);
            Button bt2 = (Button) findViewById(R.id.mainButton2);
            bt.setText(R.string.main标题);
            bt1.setText(R.string.main按钮);
            bt2.setText(R.string.main按钮下载);读取2 = "";
            ListView listView = (ListView) findViewById(R.id.mainListView1);
            final String gamepath = Environment.getExternalStorageDirectory().toString() + "/games/com.mojang/mods/";
            File fgamepath = new File(gamepath);
            String[] arr = fgamepath.list();
            Arrays.sort(arr);
            ListAdapter adapter = new MyAdapter(this, arr);
			if(arr.length==0)
				Toast.makeText(MainActivity.this, "No File At mods Direction",Toast.LENGTH_SHORT).show();
            listView.setAdapter(adapter);
			listView.setOnItemLongClickListener(new OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
												   View view, int position, long id) {

						//长按监听。
						try{
							
						}catch(Exception e){
							Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
						}
						return true;
					}
				});
            listView.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView < ?>p1, View p2, int p3, long p4) {
                    
                    final String s = (String) p1.getItemAtPosition(p3);
                    if ((new File(gamepath + s)).isDirectory()) {
						aaa = -1;
                        Stb = gamepath + s;
                        setContentView(R.layout.aboutmod);
                        TextView modname = (TextView) findViewById(R.id.modmingzi);
                        ImageView modtp = (ImageView) findViewById(R.id.modtupian);
                        modname.setText(s);
                        if (new File(gamepath + s + "/mod_icon.png").isFile()) modtp.setImageBitmap(BitmapFactory.decodeFile((gamepath + s + "//mod_icon.png")));
                        else {
                            Toast.makeText(getApplicationContext(), R.string.APPMODpic, Toast.LENGTH_SHORT).show();
                            modtp.setImageResource(R.drawable.image_2);
                        }
                        if (new File(gamepath + s + "/mod.info").isFile()) {
                            File mod = new File(gamepath + s + "/mod.info");
                            try {
                                InputStreamReader 编码 = new InputStreamReader(new FileInputStream(mod), "UTF-8");
                                BufferedReader 读取 = new BufferedReader(编码);
                                try {
                                    String 读取1;
                                    while ((读取1 = 读取.readLine()) != null) {读取1 += 读取.readLine();读取2 += 读取1;

                                    }
                                }
                                catch(IOException e) {} {}
                            }
                            catch(UnsupportedEncodingException e) {}
                            catch(FileNotFoundException e) {}
                            try {
                                JSONObject js = new JSONObject(读取2);
                                String namee = js.get("name").toString();
                                String zuoz = js.get("author").toString();
                                String banben = js.get("version").toString();
                                String miaoshu = js.get("description").toString();
                                String path = gamepath + s;
                                TextView name = (TextView) findViewById(R.id.modmingzi);
                                TextView zozhe = (TextView) findViewById(R.id.zuozhe);
                                TextView vi = (TextView) findViewById(R.id.banben);
                                TextView pa = (TextView) findViewById(R.id.lujing);
                                TextView miaosh = (TextView) findViewById(R.id.miaoshu);
                                name.setText(namee);
                                zozhe.setText(MainActivity.this.getString(R.string.MODwriter) + zuoz);
                                vi.setText(MainActivity.this.getString(R.string.MODver) + banben);
                                pa.setText(MainActivity.this.getString(R.string.MODpath) + path);
                                miaosh.setText(MainActivity.this.getString(R.string.MOD描述) + miaoshu);
                            }
                            catch(JSONException e) {}
                        } else {
                            Toast.makeText(getApplicationContext(), "未找到MOD说明文件...", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.APPstat, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        catch(Exception e) {
            Toast.makeText(this, "Cannot find mods", Toast.LENGTH_LONG).show();
			Toast.makeText(this, "Creating...", Toast.LENGTH_LONG).show();
			new File(Environment.getExternalStorageDirectory().toString() + "/games/com.mojang/mods/").mkdir();
        }
    }
	
	@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
	@Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
		
        //Toolbar 必须在onCreate()之后设置标题文本，否则默认标签将覆盖我们的设置
        if (toolbar == null||toolbar!=null) {
			
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){//4.4 全透明状态栏
				getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			}

			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
				Window window = getWindow();
				window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
															| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
				window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
				window.setStatusBarColor(Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)
			}
		
            toolbar = (Toolbar) findViewById(R.id.toolbar);
			DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
			ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
			drawer.setDrawerListener(toggle);
			toggle.syncState();
			NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
			navigationView.setNavigationItemSelectedListener(this);
			toolbar.inflateMenu(R.menu.menu_bar);
			toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						onOptionsItemSelected(item);
						return false;
					}
					
		});
    }
		

			
		
		
	}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		try{
        aaa++;
        if (aaa == 1) Toast.makeText(this, R.string.APPexit, Toast.LENGTH_SHORT).show();
        else if (aaa >= 2) finish();
        else if (aaa == 0) {
            finish();
            Intent intennt = new Intent(this, MainActivity.class);
            startActivity(intennt);
        }
		timer.schedule(task, 1000, 1000);
        
    }catch(Exception e){Toast.makeText(MainActivity.this,e.toString(), Toast.LENGTH_SHORT);}
	return false;
	}

    String 读取2 = "";

	TimerTask task = new TimerTask() {
		@Override
		public void run()
		{
			// TODO: Implement this method
			runOnUiThread(new Runnable() {

					@Override
					public void run()
					{
						// TODO: Implement this method
						rec--;
						if(rec<0.2){
							Toast.makeText(MainActivity.this, "计时完成", Toast.LENGTH_LONG);
							timer.cancel();
aaa--;
						}
					}
				});
		}
	};
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate main_menu.xml 
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        MenuItem about = menu.findItem(R.id.item);
        MenuItem exit = menu.findItem(R.id.exit);
        MenuItem jq = menu.findItem(R.id.jq);
        MenuItem sx = menu.findItem(R.id.sx);
        MenuItem open = menu.findItem(R.id.dk);
        CharSequence abab = MainActivity.this.getResources().getText(R.string.mainbar关于);
        about.setTitle(abab);
        CharSequence exxx = MainActivity.this.getResources().getText(R.string.mainbar退出);
        exit.setTitle(exxx);
        CharSequence jqq = MainActivity.this.getResources().getText(R.string.mainbar加群);
        jq.setTitle(jqq);
        CharSequence sxx = MainActivity.this.getResources().getText(R.string.mainbar刷新);
        sx.setTitle(sxx);
        CharSequence ope = MainActivity.this.getResources().getText(R.string.mainbar打开);
        open.setTitle(ope);
        return true;
    }
    public void onButtonClick(View view) {
        Intent intent = new Intent(this, SubActivity.class);
        startActivity(intent);
    }
	private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
	@Override
	public void onRequestPermissionsResult(int requestCode,
										   String permissions[], int[] grantResults) {
		switch (requestCode) {
			case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
					// If request is cancelled, the result arrays are empty.
					if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {

						// permission was granted, yay! Do the
						// contacts-related task you need to do.

					} else {

						// permission denied, boo! Disable the
						// functionality that depends on this permission.
					}
					return;
				}
		}
	}
    public void delete(View vi) {
		try{
        backkk++;
        if (backkk == 1) Toast.makeText(this, R.string.MODdell, Toast.LENGTH_SHORT).show();
        else if (backkk == 2) {
            File del = new File(Stb);
            deleteAll(del);
			backkk--;
            backkk--;
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            
        }
			//timerr.schedule(task2,1000,1000);
		}catch(Exception e){}
    }
	TimerTask task2 = new TimerTask() {
		@Override
		public void run()
		{
			// TODO: Implement this method
			runOnUiThread(new Runnable() {

					@Override
					public void run()
					{
						// TODO: Implement this method
						rec--;
						if(rec<0.2){
							Toast.makeText(MainActivity.this, "计时完成", Toast.LENGTH_LONG);
							timer.cancel();
							backkk--;
						}
					}
				});
		}
	};
    public void deleteAll(File file) {
        try {
            if (file.isFile() || file.list().length == 0) {
                file.delete();
            } else {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteAll(files[i]);
                    files[i].delete();
                }
                if (file.exists()) //如果文件本身就是目录 ，就要删除目录
                file.delete();
            }
        }
        catch(Exception e) {
            Toast.makeText(getApplicationContext(), "删除失败。因为：" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
	
    public void onDown(View view) {
        //Intent intennt = new Intent(this, download.class);
        //startActivity(intennt);
        Toast.makeText(MainActivity.this, R.string.main不支持, 1).show();
    }
	
    public void backMenu(View v) {
        this.finish();
        Intent intennt = new Intent(this, MainActivity.class);
        startActivity(intennt);
    }
	public void fab(View v){
		Animation b = new TranslateAnimation(0,500,0,500);
		TranslateAnimation a = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF,0F,TranslateAnimation.RELATIVE_TO_SELF,0F,TranslateAnimation.RELATIVE_TO_SELF,2F,TranslateAnimation.RELATIVE_TO_SELF,2F);
		Toast.makeText(MainActivity.this, "fab is taped",Toast.LENGTH_LONG).show();
		b.setDuration(2000);
		b.setFillAfter(false);
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.startAnimation(b);
	}
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.item:
            Intent intent1 = new Intent(this, AboutActivity.class);
            startActivity(intent1);
            return true;
        case R.id.exit:
            if (aaa == -1) {
                aaa++;
                this.finish();
                Intent intennt = new Intent(this, MainActivity.class);
                startActivity(intennt);
            } else finish();
            return true;
        case R.id.jq:

            Intent intent = new Intent(this, Jqqq.class);
            startActivity(intent);
            return true;
        case R.id.sx:
            if (aaa == -1) {
                Toast.makeText(getApplicationContext(), "不能在这里操作！", Toast.LENGTH_SHORT).show();
            } else {
                ListView listView = (ListView) findViewById(R.id.mainListView1);
                final String gamepath = Environment.getExternalStorageDirectory().toString() + "//games//com.mojang//mods//";
                File fgamepath = new File(gamepath);
                String[] arr = fgamepath.list();
                Arrays.sort(arr);

                ListAdapter adapter = new MyAdapter(this, arr);
                listView.setAdapter(adapter);
            }
            return true;
        case R.id.dk:
            PackageManager packageManager = MainActivity.this.getPackageManager(); // 当前Activity获得packageManager对象
            Intent intentic = new Intent();
            try {
                //下面字符串就是你另外一个应用的包的路径
                intentic = packageManager.getLaunchIntentForPackage("com.zhekasmirnov.innercore");
                startActivity(intentic);
            }
            catch(Exception e) {
                Toast.makeText(this, R.string.openf, 1).show();
            }
            return true;
        }
        return false;
    }
}

class MyAdapter extends ArrayAdapter < String > {

    public MyAdapter(Context context, String[] values) {
        super(context, R.layout.entry, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.entry, parent, false);
        final String text = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.entryTextView1);
        textView.setText(text);
        ImageView imageView = (ImageView) view.findViewById(R.id.entryImageView1);
        imageView.setImageResource(R.drawable.mod);
        return view;
    }
}
