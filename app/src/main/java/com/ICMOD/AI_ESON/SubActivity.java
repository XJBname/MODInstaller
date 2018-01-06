package com.ICMOD.AI_ESON;

import android.app.AlertDialog ;
import android.app.Activity;
import android.content. * ;
import android.graphics.drawable. * ;
import android.os. * ;
import android.view. * ;
import android.widget. * ;
import android.widget.AdapterView. * ;
import java.io. * ;
import java.util. * ;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.exception. * ;
import net.lingala.zip4j.model. * ;
import net.lingala.zip4j.util. * ;
import java.util.zip. * ;
import com.ICMOD.AI_ESON.SubActivity. * ;
import android.util. * ;
import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;




public class SubActivity extends AppCompatActivity {
    List < String > list; //列表json数据流
    File[] a = null; //不知道什么时候什么算法定义的蜜汁共享数据😓
    boolean ist = true; //if判断用的，忘了干啥的了QAQ
    boolean pdss = false;
    static int countFiles = 0; // 声明记数菌*1
    static int countFolders = 0; // 声明计数菌*2
    public static File[] searchFile(File folder, final String keyWord) { // 递归查找包含关键字的文件
        File[] subFolders = folder.listFiles(new FileFilter() { // 运用内部匿名类获得文件
            @Override
            public boolean accept(File pathname) { // 实现FileFilter类的accept方法
                if (pathname.isFile()) // 如果是文件
                countFiles++;
                else
                // 如果是目录
                countFolders++;
                if (pathname.isDirectory() || (pathname.isFile() && pathname.getName().toLowerCase().contains(keyWord.toLowerCase()))) // 目录或文件包含关键字
                return true;
                return false;
            }
        });
        List < File > result = new ArrayList < File > (); // 声明一个集合
        for (int i = 0; i < subFolders.length; i++) { // 循环显示文件夹或文件
            if (subFolders[i].isFile()) { // 如果是文件则将文件添加到结果列表中
                result.add(subFolders[i]);
            } else { // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                File[] foldResult = searchFile(subFolders[i], keyWord);
                for (int j = 0; j < foldResult.length; j++) { // 循环显示文件
                    result.add(foldResult[j]); // 文件保存到集合中
                }
            }
        }
        File files[] = new File[result.size()]; // 声明文件数组，长度为集合的长度
        result.toArray(files); // 集合数组化
        return files;
    }
    static String bbb = Environment.getExternalStorageDirectory().toString();
    static String bbaa = Environment.getExternalStorageDirectory().toString();@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (! (bbb.equals(Environment.getExternalStorageDirectory().toString()))) {
            File aaa = new File(bbb);
            String aaaa = aaa.getParent();
            bbb = aaaa;
            ListView listView = (ListView) findViewById(R.id.subListView1);
            File aa = new File(aaaa);
            String[] aaarray = aa.list();
            Arrays.sort(aaarray);
            try {
                list = new ArrayList < String > ();
                for (int i = 0; i <= aaarray.length; i++) {
                    list.add(aaarray[i]);
                }
            }
            catch(Exception e) {

            }
            SubActivity.MyAdapter adapter = new MyAdapter();
            listView.setAdapter(adapter);
        } else {
            SubActivity.this.finish();
        }
        a = null;
        ist = true;
        return false;
    }
	Toolbar tb;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		bbb = Environment.getExternalStorageDirectory().toString();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_bar);
		tb=(Toolbar) findViewById(R.id.toolbarsub);
		setSupportActionBar(tb);
		 ListView listView = (ListView) findViewById(R.id.subListView1);
        File bb = Environment.getExternalStorageDirectory();
        if (bb.isDirectory()) {
            String[] fileArray = bb.list();
            if (null != fileArray && 0 != fileArray.length) {
                Arrays.sort(fileArray);
                list = new ArrayList < String > ();
                SubActivity.MyAdapter adapter = new MyAdapter();
                listView.setAdapter(adapter);
                try {
                    for (int i = 0; i <= fileArray.length; i++) {
                        list.add(fileArray[i]);
                    }
                }
                catch(Exception e) {}
            }
        }
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
            public void onItemClick(AdapterView < ?>p1, View p2, int p3, long p4) {
                try {
                    String s = (String) p1.getItemAtPosition(p3);
                    if (ist) if (new File(bbb + File.separator + s).isDirectory()) {
                        bbaa = bbb;
                        bbb += (File.separator + s);
                    } else if (new File(bbb + File.separator + s).isFile()) {
                        String bba = bbb + File.separator + s;
                        if ((new ZipFile(new File(bba)).isValidZipFile())) {
                            ZipFile zip = new ZipFile(new File(bba));
                            String ffff = Environment.getExternalStorageDirectory().toString() + "//games//com.mojang//mods";
                            Toast.makeText(getApplicationContext(), "选择压缩文件成功，正在解压...", Toast.LENGTH_LONG).show();
                            String[] bef = new File(ffff).list();
                            zip.extractAll(ffff);
                            String[] beh = new File(ffff).list();
                            if (bef.length < beh.length) {
                                Toast.makeText(getApplicationContext(), "MOD安装成功，打开内核试试看吧！", Toast.LENGTH_LONG).show();
                            } else if (bef.length == beh.length) {
                                Toast.makeText(getApplicationContext(), "重复安装，已覆盖之前版本内容。", Toast.LENGTH_LONG).show();
                            }

                        } else Toast.makeText(getApplicationContext(), "所选文件不是压缩文件，请重试。", Toast.LENGTH_LONG).show();
                    }
                    if (a != null) {
                        for (int i = 0; i <= a.length; i++) {
                            if (a[i].getName().equals(s)) if ((new ZipFile(a[i]).isValidZipFile())) {
                                ZipFile zipp = new ZipFile(a[i]);
                                String ffff = Environment.getExternalStorageDirectory().toString() + "//games//com.mojang//mods";
                                String[] bef = new File(ffff).list();
                                zipp.extractAll(ffff);
                                String[] beh = new File(ffff).list();
                                Toast.makeText(getApplicationContext(), SubActivity.this.getResources().getText(R.string.unziptoa_unzip).toString(), Toast.LENGTH_SHORT).show();
                                if (bef.length < beh.length) {
                                    Toast.makeText(getApplicationContext(), SubActivity.this.getResources().getText(R.string.unziptoa_cg).toString(), Toast.LENGTH_LONG).show();
                                } else if (bef.length == beh.length) {
                                    Toast.makeText(getApplicationContext(), SubActivity.this.getResources().getText(R.string.unziptoa_repeat).toString(), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), SubActivity.this.getResources().getText(R.string.unziptoa_uniszip).toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    ListView listView = (ListView) findViewById(R.id.subListView1);
                    File f = new File(bbb);
                    String[] filee = f.list();
                    Arrays.sort(filee);
                    if (!bbb.equals(bbaa)) {
                        try {
                            list = new ArrayList < String > ();
                            for (int i = 0; i <= filee.length; i++) {
                                list.add(filee[i]);
                            }
                        }
                        catch(Exception e) {}
                        SubActivity.MyAdapter adapter = new MyAdapter();
                        listView.setAdapter(adapter);
                    }
                }
                catch(Exception errrr) {
                    Toast.makeText(getApplicationContext(), errrr.toString(), Toast.LENGTH_SHORT);
                }

            }
        });
    }
	@Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Toolbar 必须在onCreate()之后设置标题文本，否则默认标签将覆盖我们的设置
        if (tb == null||tb!=null) {
            tb = (Toolbar) findViewById(R.id.toolbarsub);
			tb.inflateMenu(R.menu.submen);
			tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						onOptionsItemSelected(item);
						return false;
					}

				});
		}}
	
    class MyAdapter extends BaseAdapter {
		
        @Override
        public int getCount() {
            // TODO:计算item总数。
            return list.size();
        }

        @Override
        public String getItem(int p1) {
            // TODO: 获取每个item对象
            return list.get(p1);
        }

        @Override
        public long getItemId(int p1) {
            // TODO: 获取每个item对象id
            return p1;
        }

        @Override
        public View getView(int p1, View convertView, ViewGroup p3) {
            Holder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(SubActivity.this).inflate(R.layout.entry, null);
                holder = new Holder();
                holder.imag = (ImageView) convertView.findViewById(R.id.entryImageView1);
                holder.tv = (TextView) convertView.findViewById(R.id.entryTextView1);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.tv.setText(getItem(p1));
            // TODO: 获取每个id对应的view视图
            //xml to viewObj
            //赋值view
            try {
                File babab = new File(bbb + File.separator + holder.tv.getText());
                if (babab.isFile()) holder.imag.setImageResource(R.drawable.ic_folder_black_48dp);
                else holder.imag.setImageResource(R.drawable.ic_folder_open_black_48dp);
                if (pdss) {
                    holder.imag.setImageResource(R.drawable.ic_folder_black_48dp);
                }
            }
            catch(Exception erro) {
                System.out.println(erro);
            }
            return convertView;
        }
    }
    class Holder {
        TextView tv;
        ImageView imag;
    }
    class AtyContainer {

        private AtyContainer() {}

        private AtyContainer instance = new AtyContainer();
        private List < Activity > activityStack = new ArrayList < Activity > ();

        public AtyContainer getInstance() {
            return instance;
        }

        public void addActivity(Activity aty) {
            activityStack.add(aty);
        }

        public void removeActivity(Activity aty) {
            activityStack.remove(aty);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate main_menu.xml 
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.submen, menu);
        menu.findItem(R.id.item).setTitle(R.string.Subbar搜索总);
        return true;
    }
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
        case R.id.item:
            {
                final AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity.this);
                builder.setIcon(R.drawable.ic_launcher);
                if (!pdss) {
                    builder.setTitle(R.string.SubDialog搜索);
                    builder.setMessage(R.string.SubDialog搜索副);
                } else {
                    builder.setTitle(R.string.SubDialog退出);
                    builder.setMessage(R.string.SubDialog退出副);
                }
                builder.setNegativeButton(R.string.取消, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //这里添加点击确定后的逻辑
                        if (!pdss) item.setTitle(R.string.Subbar搜索总);
                    }
                });
                builder.setPositiveButton(R.string.确定, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //这里添加点击确定后的逻辑
                        if (!pdss) {
                            item.setTitle(R.string.Subbar搜索副);
                            File folder = new File(Environment.getExternalStorageDirectory().toString()); // 默认目录
                            String keyword = ".zip";
                            if (!folder.exists()) { // 如果文件夹不存在
                                System.out.println("目录不存在：" + folder.getAbsolutePath());
                            }
                            File[] result = searchFile(folder, keyword); // 调用方法获得文件数组
                            final String[] aa = new String[result.length];
                            a = new File[result.length];
                            try {
                                for (int i = 0; i <= result.length; i++) {
                                    a[i] = result[i];
                                    aa[i] = result[i].getName();
                                }
                                ist = false;
                                Arrays.sort(a);

                            }
                            catch(Exception e) {
                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT);
                            }
                            ListView li = (ListView) findViewById(R.id.subListView1);
                            try {
                                list = new ArrayList < String > ();
                                for (int i = 0; i <= aa.length; i++) {
                                    list.add(aa[i]);
                                }
                            }
                            catch(Exception e) {}
                            SubActivity.MyAdapter adapter = new MyAdapter();
                            li.setAdapter(adapter);
                            Toast.makeText(getApplicationContext(), SubActivity.this.getResources().getText(R.string.Subtoast次).toString() + aa.length, Toast.LENGTH_LONG).show();
                            //计数菌归零☺☺☺☺
                            countFiles = 0;
                            countFolders = 0;
                            //搜索判断为是
                            pdss = true;
                        } else {
                            pdss = false;
                            SubActivity.this.finish();
                            Intent intent = new Intent(SubActivity.this, SubActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                builder.create().show();
            }
        }
        return false;
    }
}
