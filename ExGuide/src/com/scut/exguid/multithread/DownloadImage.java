package com.scut.exguid.multithread;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;

import com.scut.exguide.assist.MyActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

//三种泛型类型分别代表“启动任务执行的输入参数”、“后台任务执行的进度”、“后台计算结果的类型”。在特定场合下，
//并不是所有类型都被使用，如果没有被使用，可以用java.lang.Void类型代替。
//这个任务主要是下载单个图片用的
public class DownloadImage extends
		AsyncTask<String[], java.lang.Void, ArrayList<Bitmap>> {

	public MyActivity mInstance;
	
	public DownloadImage(MyActivity Instance) {
		mInstance = Instance;
	}
	
	
	
	/**
	 * 下载图片的后台程序
	 */
	@Override
	protected ArrayList<Bitmap> doInBackground(String[]... params) {
		// TODO Auto-generated method stub
		try {
			return getImage(params[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	

	@Override
	protected void onPostExecute(ArrayList<Bitmap> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		mInstance.UpdateImage(result);
		
	}



	/**
	 * 下载网上图片
	 * 
	 * @param url
	 * @return
	 */
	public Bitmap getImage(String romtepath) throws Exception {
		URL url = new URL(romtepath);// 获取到路径
		// http协议连接对象
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");// 这里是不能乱写的，详看API方法
		conn.setConnectTimeout(6 * 1000);
		Bitmap bitmap = null;
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			byte[] data = readStream(inputStream);
			File file = new File("smart.jpg");// 给图片起名子
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);			
			FileOutputStream outStream = new FileOutputStream(file);// 写出对象
			outStream.write(data);// 写入
			outStream.close(); // 关闭流
		}
		return bitmap;
	}

	/**
	 * 下载多个图片
	 * 
	 * @param remotepath
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Bitmap> getImage(String[] remotepath) throws IOException {
		ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
		Bitmap bitmap = null;
		for (int i = 0; i < remotepath.length; i++) {
			try {
				URL url = new URL(remotepath[i]);// 获取到路径
				// http协议连接对象
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setRequestMethod("GET");// 这里是不能乱写的，详看API方法
				conn.setConnectTimeout(6 * 1000);
				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					InputStream inputStream = conn.getInputStream();
					byte[] data = readStream(inputStream);
					File file = new File("smart.jpg");// 给图片起名子
					bitmap = BitmapFactory
							.decodeByteArray(data, 0, data.length);
					bitmapList.add(bitmap);
					FileOutputStream outStream = new FileOutputStream(file);// 写出对象
					outStream.write(data);// 写入
					outStream.close(); // 关闭流
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return bitmapList;

	}

	private byte[] readStream(InputStream inStream) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024]; // 用数据装
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outstream.write(buffer, 0, len);
		}
		outstream.close();
		inStream.close();
		// 关闭流一定要记得。
		return outstream.toByteArray();

	}

}
