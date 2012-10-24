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

import com.scut.exguide.entity.Exhibit;
import com.scut.exguide.entity.Exhibition;
import com.scut.exguide.entity.ExhibitionDetail;

import android.util.Log;

public class ExGuideJSON {

	public static final String BASE_URL = "";
	public static final String ATTACHMENT = "http://bcs.duapp.com/my-attachment/";
	public static final String POSTER = "http://bcs.duapp.com/my-poster/";
	public static final String VEDIO = "http://bcs.duapp.com/my-vedio/";

	public Exhibit getThingOfTemplate(int templateId, int id) {
		Exhibit data = null;
		String url = "http://exguide.duapp.com/product/utility.php?method=11&tid="
				+ templateId + "&id=" + id;
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
				data = new Exhibit();
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					JSONArray array;
					switch (i) {
					case 0:
						Log.i("attributename",
								jsonObject.getString("attributename"));
						array = jsonObject.getJSONArray("attributename");
						String[] strs6 = new String[array.length()];
						for (int j = 0; j < array.length(); j++) {
							strs6[j] = array.getString(j);
						}
						data.setAttributeNames(strs6);
						break;
					case 1:
						Log.i("name", jsonObject.getString("name"));
						data.setName(jsonObject.getString("name"));
						break;
					case 2:
						Log.i("attributevalue",
								jsonObject.getString("attributevalue"));
						array = jsonObject.getJSONArray("attributevalue");
						String[] strs5 = new String[array.length()];
						for (int j = 0; j < array.length(); j++) {
							strs5[j] = array.getString(j);
						}
						data.setAttributeValues(strs5);
						break;
					case 3:
						Log.i("posterurl", jsonObject.getString("posterurl"));
						array = jsonObject.getJSONArray("posterurl");
						String[] strs1 = new String[array.length()];
						for (int j = 0; j < array.length(); j++) {
							strs1[j] = POSTER + array.getString(j);
						}
						data.setpUrls(strs1);
						break;
					case 4:
						Log.i("postername", jsonObject.getString("postername"));
						array = jsonObject.getJSONArray("postername");
						String[] strs2 = new String[array.length()];
						for (int j = 0; j < array.length(); j++) {
							strs2[j] = array.getString(j);
						}
						break;
					case 5:
						Log.i("vediourl", jsonObject.getString("vediourl"));
						array = jsonObject.getJSONArray("vediourl");
						String[] strs3 = new String[array.length()];
						for (int j = 0; j < array.length(); j++) {
							strs3[j] = VEDIO + array.getString(j);
						}
						data.setvUrls(strs3);
						break;
					case 6:
						Log.i("vedioname", jsonObject.getString("vedioname"));
						array = jsonObject.getJSONArray("vedioname");
						String[] strs4 = new String[array.length()];
						for (int j = 0; j < array.length(); j++) {
							strs4[j] = array.getString(j);
						}
						data.setvNames(strs4);
						break;
					case 7:
						Log.i("attachmenturl",
								jsonObject.getString("attachmenturl"));
						array = jsonObject.getJSONArray("attachmenturl");
						String[] strs7 = new String[array.length()];
						for (int j = 0; j < array.length(); j++) {
							strs7[j] = array.getString(j);
						}
						data.setAttachmenturls(strs7);
						break;
					default:
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}

	/**
	 * 通过ID返回展会的信息
	 * 
	 * @param Id
	 *            展会ID
	 * @return 展会实体对象
	 * @author Leeforall
	 */
	public ExhibitionDetail getExhibitionInfo(int Id) {
		ExhibitionDetail data = null;
		String url = "http://exguide.duapp.com/Exhi/utility.php?method=7&tid="
				+ Id;
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
				data = new ExhibitionDetail();
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					JSONArray array;
					switch (i) {
					case 0:
						Log.i("description",
								jsonObject.getString("description"));
						data.setmDescription(jsonObject
								.getString("description"));
						break;
					case 1:
						Log.i("name", jsonObject.getString("name"));
						data.setmName(jsonObject.getString("name"));
						break;
					case 2:
						Log.i("posterurl", jsonObject.getString("posterurl"));
						array = jsonObject.getJSONArray("posterurl");
						String[] strs1 = new String[array.length()];
						for (int j = 0; j < array.length(); j++) {
							strs1[j] = POSTER + array.getString(j);
						}
						data.setmPosterurl(strs1);
						break;
					case 3:
						Log.i("postername", jsonObject.getString("postername"));
						array = jsonObject.getJSONArray("postername");
						String[] strs2 = new String[array.length()];
						for (int j = 0; j < array.length(); j++) {
							strs2[j] = array.getString(j);
						}
						break;
					case 4:
						Log.i("vediourl", jsonObject.getString("vediourl"));
						array = jsonObject.getJSONArray("vediourl");
						String[] strs3 = new String[array.length()];
						for (int j = 0; j < array.length(); j++) {
							strs3[j] = VEDIO + array.getString(j);
						}
						data.setmVediourl(strs3);
						break;
					case 5:
						Log.i("vedioname", jsonObject.getString("vedioname"));
						array = jsonObject.getJSONArray("vedioname");
						String[] strs4 = new String[array.length()];
						for (int j = 0; j < array.length(); j++) {
							strs4[j] = array.getString(j);
						}
						data.setmVedioname(strs4);
						break;
					default:
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}

	/**
	 * 获取展会信息的接口，
	 * 
	 * @return 返回Exhibition的ArrayList对象需要调整
	 * @author Leeforall
	 */
	public ArrayList<Exhibition> getExhibitions() {
		ArrayList<Exhibition> datas = new ArrayList<Exhibition>();

		String url = "http://exguide.duapp.com/Exhi/utility.php?method=1";
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
