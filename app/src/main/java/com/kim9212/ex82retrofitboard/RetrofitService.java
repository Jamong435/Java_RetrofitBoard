package com.kim9212.ex82retrofitboard;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface RetrofitService {

    @Multipart
    @POST("/Retrofit/insertDB.php")
    Call<String> postDataToBoard(@PartMap Map<String, String> dataPart,
                                 @Part MultipartBody.Part filePart);


    //서버에서 데이터를 json 읽어와서 GSON 라이브러리를 통해 곧바로
    //자바객체로 응답결과를 주는 추상메소드
    @GET("/Retrofit/loadDB.php")
    Call<ArrayList<BoardItem>> loadDataFromBoard();

    //'좋아요' 업데이트 해주는 추상메소드
    // [ @Body : 전달받은 객체를 서버에 json으로 변환하여 전달 ]
    @PUT("/Retrofit/{filename}")
    Call<BoardItem> updateData(@Path("filename") String filename,
                               @Body BoardItem item);

}
