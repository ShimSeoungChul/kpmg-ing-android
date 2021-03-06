package com.example.kpmgtest.async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.kpmgtest.common.CmmnUtil;

import org.json.JSONObject;

public class GetUserEachPoint extends AsyncTask<Object, Integer, String> {
    String tag = "GetUserEachPoint";
    public GetUserEachPoint (){
    }
    @Override
    protected String doInBackground(Object... params) {
        try{
            //첫 번째 인자에 서버uri, 두 번째 인자에 이미지 정보 변수를 담아 POST 메소드를 통해 서버에 전달
            return CmmnUtil.GET((String) params[0]);
        } catch (Exception e){
            Log.e(tag,e.getMessage());
            return "ServerFail";
        }
    }
    @Override
    protected void onPostExecute(String data) {
        if(data.equals("ServerFail")){
            Log.e(tag,"서버와의 통신에 이상이 있습니다.");
        } else {
            try {

                JSONObject jsonObject = new JSONObject(data);
                int image = Integer.parseInt(jsonObject.getString("image"));
                int ocr = Integer.parseInt(jsonObject.getString("ocr"));
                int stt = Integer.parseInt(jsonObject.getString("stt"));
                Log.e(tag,"image:"+image+"  ocr:"+ocr+"   stt:"+stt);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}