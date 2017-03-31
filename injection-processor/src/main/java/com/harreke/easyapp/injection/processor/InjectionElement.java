package com.harreke.easyapp.injection.processor;

import javax.lang.model.element.Element;

/**
 * 由 huoqisheng 于 2016/6/16 创建
 */
public class InjectionElement {
    public String elementName;
    public String typeName;
    public String[] values;

    public InjectionElement(Element element, String[] values) {
        this.elementName = element.getSimpleName().toString();
        this.typeName = element.asType().toString();
        this.values = values;
    }

    public InjectionElement(Element element, String value) {
        this.elementName = element.getSimpleName().toString();
        this.typeName = element.asType().toString();
        this.values = new String[]{value};
    }
}