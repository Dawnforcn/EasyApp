package com.harreke.easyapp.injection.processor;

import javax.lang.model.element.Element;

/**
 * 由huoqisheng于2016/6/9创建
 */
public class InjectionLayoutGenerator {
    private String mClassName;
    private String mInjectorName;
    private String mPackageName;
    private String mValue;

    public InjectionLayoutGenerator(Element target, String packageName, String value) {
        mPackageName = packageName;
        mClassName = target.getSimpleName().toString();
        mInjectorName = mPackageName + "." + mClassName + "$$LayoutInjector";
        mValue = value;
    }
    
    public String generate() {
        JavaStringBuilder builder = new JavaStringBuilder();
        
        builder.append("package ").append(mPackageName).append(";\n");
        builder.appendEnter();

        builder.appendImport(JavaStringBuilder.Context);
        builder.appendImport(JavaStringBuilder.View);
        builder.appendEnter();

        builder.appendImport("com.harreke.easyapp.injection.ILayoutInject");
        builder.appendEnter();
        
        builder.append("public class ").append(mClassName).append("$$LayoutInjector").append("<TARGET extends ").append(mClassName)
                .append("> implements ILayoutInject<TARGET> {");
        builder.appendEnter();
        builder.appendSpaceOverride(1);
        builder.appendSpace(1).append("public int layout(TARGET target, Context context) {\n");
        String layoutName = mValue;

        if (layoutName.length() == 0) {
            layoutName = mClassName.toLowerCase();
            if (layoutName.endsWith("activity")) {
                layoutName = "activity_" + layoutName.substring(0, layoutName.length() - 8);
            } else if (layoutName.endsWith("fragment")) {
                layoutName = "fragment_" + layoutName.substring(0, layoutName.length() - 8);
            } else if (layoutName.endsWith("dialog")) {
                layoutName = "dialog_" + layoutName.substring(0, layoutName.length() - 6);
            } else if (layoutName.endsWith("widget")) {
                layoutName = "widget_" + layoutName.substring(0, layoutName.length() - 6);
            }
        }
        builder.appendSpace(2).append("return context.getResources().getIdentifier(\"").append(layoutName)
                .append("\", \"layout\", context.getPackageName());\n");
        builder.appendSpace(1).append("}");
        builder.appendEnter();
        
        builder.append("}");
        
        return builder.toString();
    }
    
    public String getInjectorName() {
        return mInjectorName;
    }
}