package com.roof.vote.common;

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
}
