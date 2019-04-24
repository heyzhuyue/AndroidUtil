package com.zy.util.camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import com.zy.util.PhoneInfo;

import java.io.File;

/**
 * <pre>
 *     author: zhuyue
 *     time  : 2019/2/18
 *     desc  :相机功能工具类
 * </pre>
 */
public class CameraUtils {

    /**
     * 跳转相册选择视频
     *
     * @param context     上下文
     * @param requestCode requestCode
     */
    @NonNull
    public static void goAlbumVideo(Activity context, int requestCode) {
        if (PhoneInfo.isMIUI()) {//是否是小米设备,是的话用到弹窗选取入口的方法去选取视频
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "video/*");
            context.startActivityForResult(Intent.createChooser(intent, "选择要导入的视频"), requestCode);
        } else {//直接跳到系统相册去选取视频
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("video/*");
            context.startActivityForResult(Intent.createChooser(intent, "选择要导入的视频"), requestCode);
        }
    }

    /**
     * 启动相机录制视频
     *
     * @param activity    activity
     * @param requestCode requestCode
     * @param filePath    文件路径
     * @param fileName    文件名
     * @param duration    视频时长 以秒为单位
     */
    @NonNull
    public static void goCameraVideo(Activity activity, int requestCode, String filePath, String fileName, int duration) {
        File mediaStorageDir = new File(filePath);
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }
        File pictureFile = new File(filePath, fileName);
        Uri pictureUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            pictureUri = FileProvider.getUriForFile(activity, "com.haier.haierylh.provider", pictureFile);
        } else {
            pictureUri = Uri.fromFile(pictureFile);
        }
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, duration);
        activity.startActivityForResult(intent, requestCode);
    }


    /**
     * 启动相机录制视频
     *
     * @param activity    Activity
     * @param requestCode requestCode
     * @param filePath    文件路径
     * @param fileName    文件名
     */
    @NonNull
    public static void goCameraVideo(Activity activity, int requestCode, String filePath, String fileName) {
        File mediaStorageDir = new File(filePath);
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }
        File pictureFile = new File(filePath, fileName);
        Uri pictureUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            pictureUri = FileProvider.getUriForFile(activity, "com.haier.haierylh.provider", pictureFile);
        } else {
            pictureUri = Uri.fromFile(pictureFile);
        }
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        activity.startActivityForResult(intent, requestCode);
    }
}
