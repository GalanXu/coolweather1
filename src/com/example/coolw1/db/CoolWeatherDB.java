package com.example.coolw1.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coolw1.model.City;

public class CoolWeatherDB {
	/**
	 * ���ݿ���
	 */
	public static final String DB_NAME = "cool_weather";

	/**
	 * ���ݿ�汾
	 */
	public static final int VERSION = 1;

	private static CoolWeatherDB coolWeatherDB;

	private SQLiteDatabase db;

	/**
	 * �����췽��˽�л�
	 */
	private CoolWeatherDB(Context context) {
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,
				DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * ��ȡCoolWeatherDB��ʵ����
	 */
	public synchronized static CoolWeatherDB getInstance(Context context) {
		if (coolWeatherDB == null) {
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}

	/**
	 * ��Provinceʵ���洢�����ݿ⡣
	 */
	public void saveProvince(City citys) {
		if (citys != null) {
			ContentValues values = new ContentValues();
			values.put("id", citys.id);
			values.put("province", citys.province);
			values.put("city", citys.city);
			values.put("district", citys.district);
			db.insert("Province", null, values);
		}
	}

	/**
	 * �����ݿ��ȡȫ�����е�ʡ����Ϣ��
	 */
	public List<City> loadProvinces() {
		List<City> list = new ArrayList<City>();
		Cursor cursor = db
				.query("Province", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				City citys = new City();
				citys.id = cursor.getInt(cursor.getColumnIndex("id"));
				citys.province = cursor.getString(cursor.getColumnIndex("province"));
				citys.city = cursor.getString(cursor.getColumnIndex("city"));
				citys.district = cursor.getString(cursor.getColumnIndex("district"));
				list.add(citys);
			} while (cursor.moveToNext());
		}
		return list;
	}



}
