package cn.andthink.myframework;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;

import net.bither.util.NativeUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.andthink.actionlibrary.activity.BaseMediaActivity;

/**
 * Created by wuhaiyang on 2015/8/4.
 */
public class TestActivity extends BaseMediaActivity {


    private Button btn_choose_img;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_layout);
       /* btn_choose_img = (Button) findViewById(R.id.btn_choose_img);
        btn_choose_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callGallerTakeMuiltePhoto(1,true);

            }
        });*/

        testJpeg();
    }

    private void testJpeg() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int quality = 90;//original
                    quality=90;//同学们可以与原生的压缩方法对比一下，同样设置成50效果如何
                    InputStream in = getResources().getAssets()
                            .open("test.jpg");
                    Bitmap bit = BitmapFactory.decodeStream(in);
                    File dirFile = getExternalCacheDir();
                    dirFile = new File(dirFile.getAbsolutePath() + File.separator + "jni");
                    if (!dirFile.exists()) {
                        dirFile.mkdirs();
                    }
                    File originalFile = new File(dirFile, "original.jpg");
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            originalFile);
                    bit.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
                    File jpegTrueFile = new File(dirFile, "jpegtrue.jpg");
                    File jpegFalseFile = new File(dirFile, "jpegfalse.jpg");
                    NativeUtil.compressBitmap(bit, quality,
                            jpegTrueFile.getAbsolutePath(), true);
                    NativeUtil.compressBitmap(bit, quality,
                            jpegFalseFile.getAbsolutePath(), false);
//					Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();

    }

    @Override
    protected void onHandleResult(int requestCode, int resultCode, Intent data) {

    }
}
