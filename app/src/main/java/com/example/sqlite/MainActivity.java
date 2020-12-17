package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    DataUser dataUser;
    Button btnAdd, btnRemove, btnCancel;
    EditText txtName;
    ListView lvwName;
    ArrayList listName;
    ArrayList idList;
    ArrayAdapter arrayAdapter;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listName = new ArrayList();
        idList = new ArrayList();
        dataUser = new DataUser(this,"dbUser",null,1);

        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        btnRemove = findViewById(R.id.btnRemove);
        lvwName = findViewById(R.id.lvwName);
        txtName = findViewById(R.id.txtName);

        getAllName();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listName);
        lvwName.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                User user = new User(name);
                dataUser.addUser(user);
                getAllName();
                arrayAdapter.notifyDataSetChanged();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.deleteUser((int)idList.get(index));
                getAllName();
                arrayAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_SHORT);
            }
        });

        lvwName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }
        });
    }

    private ArrayList getAllName(){
        listName.clear();
        idList.clear();
        for (Iterator iterator = dataUser.getAllUser().iterator(); iterator.hasNext(); ) {
            User user = (User)iterator.next();
            listName.add(user.getName());
            idList.add(user.getId());
        }
        return  listName;
    }
}