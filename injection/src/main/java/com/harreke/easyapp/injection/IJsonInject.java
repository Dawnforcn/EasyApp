package com.harreke.easyapp.injection;

/**
 * 由huoqisheng于2016/9/30创建
 */
public interface IJsonInject<ITEM> {
    String toJson(ITEM item);

    ITEM toObject(String json);
}