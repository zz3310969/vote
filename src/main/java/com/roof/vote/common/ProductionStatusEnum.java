package com.roof.vote.common;

public enum ProductionStatusEnum {
	waitProcess("waitProcess", "等待审核"), processed("processed", "审核通过"), cancel("cancel", "取消");

	private String code;

	private String name;

	ProductionStatusEnum(String code, String name) {
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
		ProductionStatusEnum[] statusEnums = ProductionStatusEnum.values();
		for (ProductionStatusEnum senum : statusEnums) {
			if (senum.getCode().equals(code)) {
				return senum.getName();
			}
		}
		return "";
	}
}
