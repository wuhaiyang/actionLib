package cn.andthink.actionlibrary.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.andthink.actionlibrary.utils.FileUtil;
import cn.andthink.actionlibrary.utils.chooseImage.MultiImageSelectorActivity;

/**
 * Created by wuhaiyang on 2015/7/18.
 */
public abstract class BaseMediaActivity extends BaseActionbarActivity {

    private static final int REQUEST_MUILTE_IMAGE = 4;
    protected static final int CODE_CROP_REQUEST = 3;
    public static final int REQUEST_CODE_LOCAL = 2;
    public static final int REQUEST_CODE_CAMERA = 1;

    protected List<String> mSelectImgPath = new ArrayList<>();

    private static int output_X = 250;
    private static int output_Y = 250;

    protected boolean isCrop = false;
    protected File tmpFile; //临时存储牌照后的图片路径

    /**
     * 调用相机拍摄
     *
     * @param isResize 拍完照后是否 裁剪图片
     */
    protected void callCameraTakePhoto(boolean isResize) {
        isCrop = isResize;
        tmpFile = FileUtil.getCameraPicFile();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tmpFile));
        startActivityForResult(intent,REQUEST_CODE_CAMERA);
    }

    /**
     * 选取本地图库  单张模式
     * @param isResize 是否裁剪图片
     */
    protected void callGalleryTakeSinglePhoto(boolean isResize) {
        isCrop = isResize;
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
        } else {
            intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        startActivityForResult(intent, REQUEST_CODE_LOCAL);
    }

    /**
     * 选取本地图库 多张模式
     *
     * @param count 最多允许选取几张
     */
    protected void callGallerTakeMuiltePhoto(int count, boolean isShowCamera) {
        Intent intent = new Intent(this, MultiImageSelectorActivity.class);
        // 是否显示调用相机拍照
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, isShowCamera);
        // 最大图片选择数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, count);
        // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        startActivityForResult(intent, REQUEST_MUILTE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // 处理你自己的逻辑 ....
            }
        }*/  //上面是实例参考代码
    }

    protected abstract void onHandleResult(int requestCode, int resultCode, Intent data);

    /**
     * 裁剪图片
     *
     * @param uri
     */
    protected void cropRawPhoto(Uri uri) {
        //裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        //裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        //图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CODE_CROP_REQUEST);
    }


}
