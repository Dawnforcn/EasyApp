package com.harreke.easyapp.injection.processor;

import javax.lang.model.element.Element;

/**
 * 由huoqisheng于2016/6/9创建
 */
public class InjectionMenuGenerator {
    private String mClassName;
    private String mInjectorName;
    private String mPackageName;
    private String mValue;

    public InjectionMenuGenerator(Element target, String packageName, String value) {
        mPackageName = packageName;
        mClassName = target.getSimpleName().toString();
        mInjectorName = mPackageName + "." + mClassName + "$$MenuInjector";
        mValue = value;
    }
    
    public String generate() {
        JavaStringBuilder builder = new JavaStringBuilder();
        
        builder.append("package ").append(mPackageName).append(";\n");
        builder.appendEnter();

        builder.appendImport(JavaStringBuilder.Context);
        builder.appendImport(JavaStringBuilder.View);
        builder.appendEnter();

        builder.appendImport("com.harreke.easyapp.injection.IMenuInject");
        builder.appendEnter();
        
        builder.append("public class ").append(mClassName).append("$$MenuInjector").append("<TARGET extends ").append(mClassName)
                .append("> implements IMenuInject<TARGET> {");
        builder.appendEnter();
        builder.appendSpaceOverride(1);
        builder.appendSpace(1).append("public int menu(TARGET target) {\n");
        String menuName = mValue;

        if (menuName.length() == 0) {
            menuName = mClassName.toLowerCase();
            if (menuName.endsWith("activity")) {
                menuName = "menu_" + menuName.substring(0, menuName.length() - 8);
            }
        }
        builder.appendSpace(2).append("return target.getResources().getIdentifier(\"").append(menuName).append("\", \"menu\", target.getPackageName());\n");
        builder.appendSpace(1).append("}");
        builder.appendEnter();
        
        builder.append("}");
        
        return builder.toString();
    }
    
    public String getInjectorName() {
        return mInjectorName;
    }
}