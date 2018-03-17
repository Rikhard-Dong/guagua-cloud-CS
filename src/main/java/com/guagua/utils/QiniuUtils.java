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

public class QiniuUtils {
    private static final String ACCESS_KEY = "ngX8DY8MVF76Ht-tepHc2a8TUIk_0f6-Nfm_vQGp";
    private static final String SECRET_KEY = "qXRLvlU3JJ7utTasYTBkSG32tx9xX_eeLz6JALy1";
    private static final String BUCKET = "cloudcs";

    /**
     * 获取token
     *
     * @return token值
     */
    private static String getUpToken() {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);    // TODO Auto-generated constructor stub
        return auth.uploadToken(BUCKET, null, 7200, new StringMap().put("insertOnly", 0));
    }

    /**
     * 通过base64的方法上传图片
     *
     * @param imgbyte  base64
     * @param filename 文件名
     * @throws Exception 异常
     */
    public static void uploadByBase64(String imgbyte, String filename) throws Exception {
        // 去掉头部信息
        String[] var1 = imgbyte.split(",");
        imgbyte = var1.length == 1 ? var1[0] : var1[1];

        String url = "http://upload.qiniu.com/putb64/" + getSize(imgbyte) + "/key/" + UrlSafeBase64.encodeToString(filename);
        //非华东空间需要根据注意事项 1 修改上传域名
        RequestBody rb = RequestBody.create(null, imgbyte);
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

    /**
     * 得到图片大小
     *
     * @param imgbyte base64
     * @return 图片大小
     */
    private static Integer getSize(String imgbyte) {
        Integer equalIndex = imgbyte.indexOf("=");//2.找到等号，把等号也去掉
        if (imgbyte.indexOf("=") > 0) {
            imgbyte = imgbyte.substring(0, equalIndex);
        }
        Integer strLength = imgbyte.length();//3.原来的字符流大小，单位为字节
        return strLength - (strLength / 8) * 2 - 1;
    }

    /**
     * 删除图片
     *
     * @param filename 文件名
     */
    public static void deleteImg(String filename) throws QiniuException {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        bucketManager.delete(BUCKET, filename);
    }
}
