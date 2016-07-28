package com.know.you.app.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaojunzi on 16-7-28.
 */
public class FileUtils {
	public static final int IO_BUFFER_SIZE = 8 * 1024;

	/** 获取应用缓存地址根目录 */
	public static File getDiskCacheFile(Context context, String cacheDir) {

		// Check if media is mounted or storage is built-in, if so, try and use
		// external cache dir
		// otherwise use internal cache dir
		final String cachePath = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED || !isExternalStorageRemovable() ? getExternalCacheDir(context).getPath() : context.getCacheDir()
				.getPath();
		File file = new File(cachePath + File.separator + cacheDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return new File(cachePath + File.separator + cacheDir);
	}

	public static boolean isExternalStorageRemovable() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			return Environment.isExternalStorageRemovable();
		}
		return true;
	}

	public static File getExternalCacheDir(Context context) {
		if (hasExternalCacheDir()&&context.getExternalCacheDir()!=null) {
			return context.getExternalCacheDir();
		}

		// Before Froyo we need to construct the external cache dir ourselves
		final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
		return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
	}

	public static boolean hasExternalCacheDir() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
	}

	@SuppressLint("NewApi")
	public static long getUsableSpace(File path) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			return path.getUsableSpace();
		}
		final StatFs stats = new StatFs(path.getPath());
		return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
	}

	public static void saveBitmap(Bitmap bm, String cpPath, String picName) {
		// Log.e("", "保存图片");
		try {
			File f = new File(cpPath, picName + ".JPEG");
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			// Log.e("", "已经保存");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 删除目录下所有文件 */
	public static void deleteDir(String filePath) {
		File dir = new File(filePath);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;

		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); // 删除所有文件
			else if (file.isDirectory())
				deleteDir(file.getPath()); // 递规的方式删除文件夹
		}
		// dir.delete();// 删除目录本身
	}

	/** 根据文件路径返回File对象 */
	public static List<File> getFileByPath(List<String> paths) {
		List<File> files = new ArrayList<File>();
		if (paths != null && paths.size() > 0) {
			for (String path : paths) {
				if (!TextUtils.isEmpty(path)) {
					File file = new File(path);
					if (file.exists()) {
						files.add(file);
					}
				}
			}
		}
		return files;
	}

	/** 根据文件路径返回InputStream对象 */
	public static List<InputStream> getInputStreamByPath(List<String> paths) {
		List<InputStream> files = new ArrayList<InputStream>();
		try {
			if (paths != null && paths.size() > 0) {
				for (String path : paths) {
					if (!TextUtils.isEmpty(path)) {
						File file = new File(path);
						if (file.exists()) {
							files.add(new FileInputStream(file));
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return files;
	}

}
