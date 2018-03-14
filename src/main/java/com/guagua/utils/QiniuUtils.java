package com.guagua.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QiniuUtils {
    public static String getUpToken() {
        String accessKey = "ngX8DY8MVF76Ht-tepHc2a8TUIk_0f6-Nfm_vQGp";
        String secretKey = "qXRLvlU3JJ7utTasYTBkSG32tx9xX_eeLz6JALy1";    // 密钥配置
        Auth auth = Auth.create(accessKey, secretKey);    // TODO Auto-generated constructor stub
        String bucket = "cloudcs";    //空间名
        return auth.uploadToken(bucket, null, 3600, new StringMap().put("insertOnly", 0));
    }
    public static void uploadByBase64(String imgbyte,String key) throws Exception {
        String url = "http://upload.qiniu.com/putb64/" + getSize(imgbyte) +"/key/"+ UrlSafeBase64.encodeToString(key);
        //非华东空间需要根据注意事项 1 修改上传域名
        RequestBody rb = RequestBody.create(null,imgbyte);
        Request request = new Request.Builder().
                url(url).
                addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb).build();
        System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
    }

    public static Integer getSize(String imgbyte){
        String copeimgbyte = imgbyte;
        copeimgbyte = replaceBlank(copeimgbyte);
        Integer equalIndex= copeimgbyte.indexOf("=");//2.找到等号，把等号也去掉
        if(copeimgbyte.indexOf("=")>0) {
            copeimgbyte=copeimgbyte.substring(0, equalIndex);
        }
        Integer strLength=copeimgbyte.length();//3.原来的字符流大小，单位为字节
        return strLength - (strLength/8) * 2 - 1;
    }

    private static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static void deleteImg(String key){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        String accessKey = "ngX8DY8MVF76Ht-tepHc2a8TUIk_0f6-Nfm_vQGp";
        String secretKey = "qXRLvlU3JJ7utTasYTBkSG32tx9xX_eeLz6JALy1";
        String bucket = "cloudcs";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
