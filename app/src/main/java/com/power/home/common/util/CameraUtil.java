package com.power.home.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.power.home.ui.fragment.BaseFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by zhangpeng on 2018/1/29.
 */

public class CameraUtil {
    public final static int REQUEST_PICTURE_CHOOSE = 1;
    public final static int REQUEST_CAMERA_IMAGE = 2;
    public final static int REQUEST_CROP_IMAGE = 3;

    public static File mPictureFile;
    public static Bitmap mImage;
    public static byte[] mImageData;


    public static void choosePhoto(Activity activity) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        activity.startActivityForResult(intent, REQUEST_PICTURE_CHOOSE);
    }


    public static void choosePhoto(BaseFragment fragment) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        fragment.startActivityForResult(intent, REQUEST_PICTURE_CHOOSE);
    }


    public static void openCamera(Activity activity) {
        mPictureFile = new File(Environment.getExternalStorageDirectory(),
                "picture" + System.currentTimeMillis() / 1000 + ".jpg");
        Intent mIntent = new Intent();
        mIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        mIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPictureFile));
        mIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        activity.startActivityForResult(mIntent, REQUEST_CAMERA_IMAGE);


    }


    public static void openCamera(BaseFragment fragment) {
        mPictureFile = new File(Environment.getExternalStorageDirectory(),
                "picture" + System.currentTimeMillis() / 1000 + ".jpg");
        Intent mIntent = new Intent();
        mIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        mIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPictureFile));
        mIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        fragment.startActivityForResult(mIntent, REQUEST_CAMERA_IMAGE);


    }

    public static int getBitmapSize(Bitmap bitmap){
        if(bitmap == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//API 12
            return bitmap.getByteCount();
        }


       return bitmap.getRowBytes()*bitmap.getHeight();
    }

    public static Bitmap dealPic(Activity activity, int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return null;
        }
        String fileSrc = null;
        if (requestCode == REQUEST_PICTURE_CHOOSE) {
            if ("file".equals(data.getData().getScheme())) {
                fileSrc = data.getData().getPath();
            } else {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = activity.getContentResolver().query(data.getData(), proj,
                        null, null, null);
                cursor.moveToFirst();
                int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                fileSrc = cursor.getString(idx);
                cursor.close();
            }
        } else if (requestCode == REQUEST_CAMERA_IMAGE) {
            if (null == mPictureFile) {
                return null;
            }
            fileSrc = mPictureFile.getAbsolutePath();
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        mImage = BitmapFactory.decodeFile(fileSrc, options);
        options.inSampleSize = Math.max(1, (int) Math.ceil(Math.max(
                (double) options.outWidth / 1024f,
                (double) options.outHeight / 1024f)));
        options.inJustDecodeBounds = false;
        mImage = BitmapFactory.decodeFile(fileSrc, options);

        if (null == mImage) {
            Log.e("CameraUtil", "图片信息无法正常获取！");
            return null;
        }

        int degree = CameraUtil.readPictureDegree(fileSrc);
        if (degree != 0) {
            mImage = CameraUtil.rotateImage(degree, mImage);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        mImage.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        mImageData = baos.toByteArray();
//        if(null != imageView){
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setImageBitmap(mImage);
//        }
        mPictureFile = null;
        mImageData = null;

        return mImage;
    }


    public static File dealPic2(Activity activity,  int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return null;
        }
        String fileSrc = null;
        if (requestCode == REQUEST_PICTURE_CHOOSE) {
            if ("file".equals(data.getData().getScheme())) {
                fileSrc = data.getData().getPath();
            } else {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = activity.getContentResolver().query(data.getData(), proj,
                        null, null, null);
                cursor.moveToFirst();
                int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                fileSrc = cursor.getString(idx);
                cursor.close();
            }
        } else if (requestCode == REQUEST_CAMERA_IMAGE) {
            if (null == mPictureFile) {
                return null;
            }
            fileSrc = mPictureFile.getAbsolutePath();
        }
        return new File(fileSrc);
    }


    public static String getImagePath(Context context, String fileName) {
        String path;

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = context.getFilesDir().getAbsolutePath();
        } else {
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/msc/";
        }

        if (!path.endsWith("/")) {
            path += "/";
        }

        File folder = new File(path);
        if (folder != null && !folder.exists()) {
            folder.mkdirs();
        }
        if (TextUtils.isEmpty(fileName)) {
            path += "ifd.jpg";
        } else {
            path += (fileName + ".jpg");
        }
        return path;
    }


    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static Bitmap rotateImage(int angle, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }



    static public Rect RotateDeg90(Rect r, int width, int height) {
        int left = r.left;
        r.left = height - r.bottom;
        r.bottom = r.right;
        r.right = height - r.top;
        r.top = left;
        return r;
    }

    public static File saveBitmapToFile(Context context, Bitmap bmp, String file_Name) {
        String file_path = "";
        if (TextUtils.isEmpty(file_Name)) {
            file_path = getImagePath(context, "ifd");
        } else {
            file_path = getImagePath(context, file_Name);
        }
        File file = new File(file_path);

        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    public static void saveBitmapToFile(Context context, Bitmap bmp) {
        saveBitmapToFile(context, bmp, null);
    }

    public static long getFileSize(File file) throws Exception
    {
        long size = 0;
        if (file.exists()){
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        }
        else{
            file.createNewFile();
        }
        return size;
    }
}
