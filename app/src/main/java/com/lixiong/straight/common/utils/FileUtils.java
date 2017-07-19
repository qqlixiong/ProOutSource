package com.lixiong.straight.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.os.Environment;

public class FileUtils {
	public static String getFileName(String path) {
		int index = path.lastIndexOf("/");
		return path.substring(index + 1);
	}

	public static boolean hasSDCard() {
		String status = Environment.getExternalStorageState();
		if(status.equals(Environment.MEDIA_MOUNTED)){
			return true;
		}else{
			return false;
		}
	}

	public static void createFile(File file) throws IOException {
		if(!file.exists()){
			file.createNewFile();
		}
	}

	public static void createPath(String path) {
		File file = new File(path);
		if(!file.exists()){
			file.mkdir();
		}
	}

	public static void copyFile(String oldPath, String newPath) {
		try{
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if(oldfile.exists()){ // 文件不存在时
				InputStream inputStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream outputStream = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inputStream.read(buffer)) != -1){
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					outputStream.write(buffer, 0, byteread);
				}
				inputStream.close();
				outputStream.close();
			}
		}catch (Exception e){
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	public static String getRandomFileName() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.getDefault());
		return sdf.format(now);
	}

	public static String getExternalCacheDir(Context context) {
		return context.getExternalCacheDir().getAbsolutePath();
	}

	/**
	 * 获取图片文件的路径
	 * 
	 * @param context
	 * @return
	 */
	public static String getImageFilePath(Context context) {
		String saveDirPath = getExternalCacheDir(context) + File.separator + Config.FILE_TYPE_IMAGE;
		createPath(saveDirPath);
		String fileName = getRandomFileName() + ".jpg";
		return saveDirPath + File.separator + fileName;
	}

	public static boolean hasFileExists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	public static boolean writeToFile(String filePath, String content, boolean isCleanFile) {
		return writeFile(new File(filePath), content, isCleanFile);
	}

	public static boolean writeFile(File file, String content, boolean isCleanFile) {
		FileOutputStream fileOutputStream = null;
		try{
			// 重新创建一个空文件
			if(isCleanFile && file.exists()){
				file.delete();
				file.createNewFile();
			}

			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(content.getBytes());

		}catch (FileNotFoundException e){
			e.printStackTrace();
			return false;
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}finally{
			try{
				if(fileOutputStream != null){
					fileOutputStream.close();
				}
			}catch (IOException e){
				e.printStackTrace();
			}
		}

		return true;
	}

	public static String readFile(String filePath) {
		return readFile(new File(filePath));
	}

	public static String readFile(File file) {
		FileInputStream fileInputStream = null;
		String content = null;
		try{
			// 文件存在
			if(file.exists()){
				fileInputStream = new FileInputStream(file);
				byte[] b = new byte[fileInputStream.available()];
				fileInputStream.read(b);
				content = new String(b);
			}
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(fileInputStream != null){
					fileInputStream.close();
				}
			}catch (IOException e){
				e.printStackTrace();
			}
		}

		return content;
	}
	/**
	 * 删除文件
	 *
	 * @param file
	 * @return
	 */
	public static boolean fileDeleteToLog(File file) {
		boolean result = true;

		if(file.exists()){
			result = file.delete();
		}
		return result;
	}

	/**
	 * 删除目录
	 *
	 * @param file
	 * @return
	 */
	public static boolean dirCreateToLog(File file) {
		boolean result = true;

		if(!file.exists()){
			result = file.mkdir();
		}

		return result;
	}

	/**
	 * 删除目录下的所有文件，支持层级删除
	 * 
	 * @param file
	 *            要删除的目录
	 */
	public static void clearFiles(File file) {
		if(!file.exists()){
			return;
		}

		if(file.isDirectory()){
			File[] childFile = file.listFiles();
			if(childFile != null){
				for (File f : childFile){
					clearFiles(f);
				}
			}
		}else if(file.isFile()){
			fileDeleteToLog(file);
		}
	}

	/**
	 * 删除目录，以及目录下所有目录，文件
	 * 
	 * @param file
	 *            要删除的目录
	 */
	public static void deleteDirs(File file) {
		if(!file.exists()){
			return;
		}

		if(file.isDirectory()){
			File[] childFile = file.listFiles();
			if(childFile != null){
				for (File f : childFile){
					deleteDirs(f);
				}
			}
		}
		fileDeleteToLog(file);
	}
}