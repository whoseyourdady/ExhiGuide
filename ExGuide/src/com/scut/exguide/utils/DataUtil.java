/*
 * Copyright (C) 2012 The Team of BGOOO
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scut.exguide.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class DataUtil {
	// 保存用于验证用户的Access_Token
	public static void saveAccessToken(Context con, String token) {
		SharedPreferences sp = con.getSharedPreferences("token",
				Context.MODE_PRIVATE);
		if (token != null) {
			sp.edit().putString("accesstoken", token).commit();
		} else {
			sp.edit().putString("accesstoken", null).commit();
		}
	}

	// 读取用户验证的Access Token
	public static String readProfile(Context con) {
		String at = null;
		SharedPreferences sp = con.getSharedPreferences("token",
				Context.MODE_PRIVATE);
		String username = sp.getString("accesstoken", null);
		if (username != null) {
			at = username;
		}
		return at;
	}

	public static void SetFirstUse(Context con) {
		SharedPreferences sp = con.getSharedPreferences("first",
				Context.MODE_PRIVATE);

		sp.edit().putBoolean("accesstoken", false).commit();

	}

	public static boolean isFirstTime(Context con) {
		SharedPreferences sp = con.getSharedPreferences("first",
				Context.MODE_PRIVATE);
		return sp.getBoolean("accesstoken", true);
	}
}
