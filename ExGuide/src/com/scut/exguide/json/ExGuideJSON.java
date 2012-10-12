package com.scut.exguide.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.scut.exguide.entity.Exhibition;

import android.util.Log;

public class ExGuideJSON {

	public static final String BASE_URL = "";

	public JSONObject getExhibitioninfo(int Id) {
		String url = BASE_URL + "?option=GetExhibition" + "&id=" + Id;
		HttpGet request = new HttpGet(url);
		JSONObject jsonObject = null;
		/* StringBulder,jdk_1.5新增，用法基本跟StringBuffer一样，但效率要比StringBuffer高得多 */
		StringBuilder sbuilder = new StringBuilder();
		try {
			/* 模拟浏览器客户端 */
			HttpClient client = new DefaultHttpClient();
			;
			/* 获取客户端对浏览器发出请求后的响应情况 */
			HttpResponse response = client.execute(request);
			/* 测试是否请求成功 */
			System.out.println(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));
				for (String s = reader.readLine(); s != null; s = reader
						.readLine()) {
					// s=new String(s.getBytes("UNICODE"), "UTF-8");
					sbuilder.append(s);
				}
				Log.i("json_str", sbuilder.toString());
				jsonObject = new JSONObject(sbuilder.toString());
				Log.i("id", jsonObject.getInt("id") + "");

				Log.i("website_name", jsonObject.getString("site_name"));

				Log.i("website_url", jsonObject.getString("site_url"));

				Log.i("category", jsonObject.getInt("category") + "");

				Log.i("title", jsonObject.getString("title"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;

	}

	public JSONObject getThings(int Id) {
		String url = BASE_URL + "?option=GetExhibition" + "&id=" + Id;
		HttpGet request = new HttpGet(url);
		JSONObject jsonObject = null;
		/* StringBulder,jdk_1.5新增，用法基本跟StringBuffer一样，但效率要比StringBuffer高得多 */
		StringBuilder sbuilder = new StringBuilder();
		try {
			/* 模拟浏览器客户端 */
			HttpClient client = new DefaultHttpClient();
			;
			/* 获取客户端对浏览器发出请求后的响应情况 */
			HttpResponse response = client.execute(request);
			/* 测试是否请求成功 */
			System.out.println(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));
				for (String s = reader.readLine(); s != null; s = reader
						.readLine()) {
					// s=new String(s.getBytes("UNICODE"), "UTF-8");
					sbuilder.append(s);
				}
				Log.i("json_str", sbuilder.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;

	}

	/**
	 * 获取展会信息的接口，
	 * 
	 * @return 返回JSONArray的对象需要调整
	 */
	public ArrayList<Exhibition> getExhibitions() {
		ArrayList<Exhibition> datas = new ArrayList<Exhibition>();

		String url = "http://192.168.1.101/exhibition/GetExhiList.php";
		/* 模拟浏览器GET请求 */
		HttpGet request = new HttpGet(url);
		JSONArray jsonArray = null;
		/* StringBulder,jdk_1.5新增，用法基本跟StringBuffer一样，但效率要比StringBuffer高得多 */
		StringBuilder sbuilder = new StringBuilder();
		try {
			/* 模拟浏览器客户端 */
			HttpClient client = new DefaultHttpClient();
			;
			/* 获取客户端对浏览器发出请求后的响应情况 */
			HttpResponse response = client.execute(request);
			/* 测试是否请求成功 */
			System.out.println(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));
				for (String s = reader.readLine(); s != null; s = reader
						.readLine()) {
					// s=new String(s.getBytes("UNICODE"), "UTF-8");
					sbuilder.append(s);
				}
				Log.i("json_str", sbuilder.toString());

				jsonArray = new JSONArray(sbuilder.toString());
				for (int i = 0; i < jsonArray.length(); i++) {

					JSONObject jsonObject = jsonArray.getJSONObject(i);

					Log.i("id", jsonObject.getInt("id") + "");
					Log.i("name", jsonObject.getString("name"));
					Log.i("province", jsonObject.getString("province"));
					Log.i("city", jsonObject.getString("city") + "");
					Log.i("hall", jsonObject.getString("hall"));

					Exhibition ex = new Exhibition(
							jsonObject.getString("name"),
							jsonObject.getString("province"),
							jsonObject.getString("city"),
							jsonObject.getString("hall"),
							jsonObject.getInt("id"));

					datas.add(ex);
				}
				// JSONArray jssonArray=json.toJSONArray(jssonArray);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return datas;
	}
}
