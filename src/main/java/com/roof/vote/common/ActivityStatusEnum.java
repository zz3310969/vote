package com.roof.vote.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ActivityStatusEnum {
	newact("newact", "新建活动"), inProgress("inProgress", "进行中"), end("end", "结束"), del("del", "删除");

	private String code;

	private String name;

	ActivityStatusEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getStatusEnumName(String code) {
		if (code == null) {
			return "";
		}
		ActivityStatusEnum[] statusEnums = ActivityStatusEnum.values();
		for (ActivityStatusEnum senum : statusEnums) {
			if (senum.getCode().equals(code)) {
				return senum.getName();
			}
		}
		return "";
	}

	public static List<Map<String, String>> getAll() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		ActivityStatusEnum[] statusEnums = ActivityStatusEnum.values();
		for (ActivityStatusEnum senum : statusEnums) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", senum.getCode());
			map.put("name", senum.getName());
			list.add(map);
		}
		return list;
	}

}
