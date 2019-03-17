package com.zy.util.camera;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;

/**
 * <pre>
 *     author: zhuyue
 *     time  : 2019/2/18
 *     desc  : 从Uri获取文件路径工具类
 * </pre>
 */
public class PathFromUriUtils {


    /**
     * Uri转文件路径
     * <p>
     * 用到文件的时候只需要 File file = new File(res);
     *
     * @param uri 文件Uri路径
     * @return 文件绝对路径
     */
    public static File getFileFromUri(Context context, Uri uri) {
        String filePath;
        if (Build.VERSION.SDK_INT >= 19) { //Android4.4之后
            filePath = uriToFilePathOnKitKat(context, uri);
        } else { //Android4.4之前
            filePath = uriToFilePathBeforeKitKat(context, uri);
        }
        return new File(filePath);
    }

    /**
     * Android4.4之前版本从Uri获取图片路径方式
     *
     * @param uri Uri
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String uriToFilePathOnKitKat(Context context, Uri uri) {
        String imagePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if ("com.android.externalstorage.documents".equals(uri.getAuthority())) { //外部存储
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) { //下载
                String docId = DocumentsContract.getDocumentId(uri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = uriToFilePath(context, contentUri, null, null);
            } else if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String docId = DocumentsContract.getDocumentId(uri);
                String type = docId.split(":")[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{docId.split(":")[0]};
                imagePath = uriToFilePath(context, contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if ("com.google.android.apps.photos.content".equals(uri.getAuthority())) {
                imagePath = uri.getLastPathSegment();
            } else {
                String id = uri.getLastPathSegment().split(":")[0];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = uriToFilePath(context, uri, selection, null);
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        return imagePath;
    }

    /**
     * Android4.4之前从Uri获取路径
     *
     * @param context 上下文
     * @param uri     uri
     */
    private static String uriToFilePathBeforeKitKat(Context context, Uri uri) {
        return uriToFilePath(context, uri, null, null);
    }

    /**
     * uri转文件路径
     *
     * @param context   上下文
     * @param uri       Uri
     * @param selection selection
     * @return 文件路径(非绝对路径)
     */
    private static String uriToFilePath(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndexOrThrow(column);
                    return cursor.getString(index);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (uri == null) {
                return null;
            }
            if (!TextUtils.isEmpty(uri.getPath())) {
                File file = new File(Environment.getExternalStorageDirectory(), uri.getPath());
                return file.getAbsolutePath();
            }
            return uri.getPath();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

}
