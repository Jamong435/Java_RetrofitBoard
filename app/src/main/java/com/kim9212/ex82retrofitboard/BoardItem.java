package com.kim9212.ex82retrofitboard;


//서버에서 읽어온 게시글 board테이블 한 record(row)의 데이터를 저장하는 vo클래스

public class BoardItem {

    int no;
    String name;
    String title;
    String msg;
    String price;
    String file;
    int favor; //좋아요 여부[mysql에 저장이안되서 1,0으로 대체한다.]
    String date;

    public BoardItem() {
    }

    public BoardItem(int no, String name, String title, String msg, String price, String file, int favor, String date) {
        this.no = no;
        this.name = name;
        this.title = title;
        this.msg = msg;
        this.price = price;
        this.file = file;
        this.favor = favor;
        this.date = date;
    }
}
