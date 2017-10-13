package com.roof.vote.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ProductionStatusEnum {
    waitProcess("waitProcess", "等待审核"), processed("processed", "审核通过"), cancel("cancel",
            "取消"), managecancel("managecancel", "审核不通过作废");

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

    public static List<Map<String, String>> getProductionStatusEnums() {
        List<Map<String, String>> list = new ArrayList<>();
        ProductionStatusEnum[] inventoryEventEnums = ProductionStatusEnum.values();
        for (ProductionStatusEnum eventEnum : inventoryEventEnums) {

            Map<String, String> map = new HashMap<>(2);
            map.put("value", eventEnum.getCode());
            map.put("text", eventEnum.getName());

            list.add(map);

        }
        return list;
    }
}
