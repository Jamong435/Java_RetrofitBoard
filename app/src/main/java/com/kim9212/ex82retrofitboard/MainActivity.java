package com.kim9212.ex82retrofitboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BoardAdapter adapter;
    ArrayList<BoardItem> boarditems= new ArrayList<>();
  


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recycler);
        adapter=new BoardAdapter(this,boarditems);
        recyclerView.setAdapter(adapter);
    }
    //액티비티가 화면에 보여질떄..자동 실행되는 라이프 사이클 메소드

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
    //서버에서 데이터를 불러들이는 작업메소드

    void  loadData(){
    //테스트용..
//        boarditems.add(new BoardItem(1,"aa","title","message","1000","",1,"2020"));
//        boarditems.add(new BoardItem(1,"aa","title","message","1000","",0,"2020"));
        Retrofit retrofit=RetrofitHelper.getInstance();//json응답받는중
        RetrofitService retrofitService= retrofit.create(RetrofitService.class);
        Call<ArrayList<BoardItem>> call= retrofitService.loadDataFromBoard();
        call.enqueue(new Callback<ArrayList<BoardItem>>() {
            @Override
            public void onResponse(Call<ArrayList<BoardItem>> call, Response<ArrayList<BoardItem>> response) {
                if(response.isSuccessful()) {
                    ArrayList<BoardItem> items = response.body();
                    //새로운 아답터를 만들면 조금 느리므로..
                    //기존 아답터가 보여주던 boarditems 리스트 객체의
                    //갑ㅅ들을 변경
                    //일단 기존리스트들을 모두삭제
                    boarditems.clear();
                    adapter.notifyDataSetChanged();

                    //서버에서 읽어온itmes를 boarditems에 새로추가
                    for(BoardItem item : items){
                        boarditems.add(0,item);
                        adapter.notifyItemChanged(0);
                    }

//                    //리사이클러에서 보여줘야 하므로  위에서 얻어온 리스트로 새로운 아답터 객체 생성!
//                    adapter=new BoardAdapter(MainActivity.this,items);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<BoardItem>> call, Throwable t) {
            }
        });
    }
    public void clickedit(View view) {

        Intent intent= new Intent(this, EditActivity.class);
        startActivity(intent);
    }
}