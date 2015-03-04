package asia.takezoo.listview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //データを準備
        //Stringの配列にデータをいれる
       // String[] items={"item0","item1","item2"};
        //ArrayList
       // List<String> items = new ArrayList<String>();
        //HashMapでnameとlocationをitemsにいれる。
        //List<Map<String, String>> items = new ArrayList<Map<String, String>>();
        ArrayList<User> users = new ArrayList<User>();
        int[] images = {R.drawable.img1,R.drawable.img2,R.drawable.img3};
        String[] namelist={"name1","name2","name3"};
        String[] locationlist={"location1","location2","location3"};

        for (int i = 0;i<namelist.length;i++) {
            //Map<String, String> m = new HashMap<String, String>();
            User user = new User();
            user.setImage(BitmapFactory.decodeResource(getResources(),images[i]));
            user.setName(namelist[i]);
            user.setLocation(locationlist[i]);
            users.add(user);

            //m.put("name", namelist[i]);
            //m.put("location", locationlist[i]);
            //items.add(m);
        }

        for (int i = 0; i < 20; i++ ){
           // items.add("item" + i);
        }

        //adapterを準備
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item, items);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);

        //simple_list_item_2の準備
        //String[] from = {"name","location"};
        //int[] to = {android.R.id.text1, android.R.id.text2};
        //SimpleAdapter adapter = new SimpleAdapter(this, items, android.R.layout.simple_list_item_2, from, to);

        UserAdapter userAdapter = new UserAdapter(this, 0, users);

        //listviewにadapterを設置

        final ListView myListView = (ListView) findViewById(R.id.myListView);
        //データがなかったときにR.id.emptyを表示
        //myListView.setEmptyView(findViewById(R.id.empty));
        myListView.setAdapter(userAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view,int i, long l){
                //Toast.makeText(MainActivity.this, Integer.toString(i) + "番目の要素です",Toast.LENGTH_SHORT).show();

                //String s = myListView.getItemAtPosition(i).toString();
                //Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();


                //TextView tv = (TextView) view;
                //tv.setText("Clicked");

                //simple_list_item_2の場合
                //TextView tv1 = (TextView) view.findViewById(android.R.id.text1);
                //String s = tv1.getText().toString();
                //Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();

                TextView name = (TextView) view.findViewById(R.id.name);
                Toast.makeText(MainActivity.this, name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //viewholderで高速化
   private static class ViewHolder {
       ImageView image;
       TextView name;
       TextView location;

       public ViewHolder(View view){
           this.image = (ImageView) view.findViewById(R.id.image);
           this.name = (TextView) view.findViewById(R.id.name);
           this.location = (TextView) view.findViewById(R.id.location);
       }
   }

    public class UserAdapter extends ArrayAdapter<User>{

        private LayoutInflater layoutInflater;

        //コンストラクタ
        public UserAdapter(Context context,int viewResourceId, ArrayList<User> users){
            super(context, viewResourceId, users);
            this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public android.view.View getView(int position, View convertView, ViewGroup parent){
            ViewHolder viewHolder;
            //再利用できるviewがなかったらviewをつくる=LayoutInflaterを使ってrow.xmlをviewにする
            if (convertView == null){
                convertView = layoutInflater.inflate(R.layout.row, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //そのviewをデータにセット
            User user = (User ) getItem(position);

            viewHolder.image.setImageBitmap(user.getImage());
            viewHolder.name.setText(user.getName());
            viewHolder.location.setText(user.getLocation());
            /*ImageView userImage = (ImageView) convertView.findViewById(R.id.image);
            userImage.setImageBitmap(user.getImage());

            TextView userName = (TextView) convertView.findViewById(R.id.name);
            userName.setText(user.getName());

            TextView userLocation = (TextView) convertView.findViewById(R.id.location);
            userLocation.setText(user.getLocation());*/


            //viewを返す
            return convertView;
        }
    }


    private class User{
        private Bitmap image;
        private String name;
        private String location;

        public Bitmap getImage(){
            return this.image;
        }
        public void setImage(Bitmap image){
            this.image = image;
        }
        public String getName(){
            return this.name;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getLocation(){
            return this.location;
        }
        public void setLocation(String location){
            this.location = location;
        }

    }
}
