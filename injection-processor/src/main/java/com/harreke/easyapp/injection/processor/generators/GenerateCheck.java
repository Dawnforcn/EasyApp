package com.harreke.easyapp.injection.processor.generators;

import com.harreke.easyapp.injection.processor.InjectionElement;
import com.harreke.easyapp.injection.processor.JavaStringBuilder;

/**
 * 由 huoqisheng 于 2016/6/20 创建
 */
public class GenerateCheck extends AbsGenerator {
    @Override
    protected void afterLoop(JavaStringBuilder builder) {
    }

    @Override
    protected void beforeLoop(JavaStringBuilder builder) {
        builder.appendSpaceEnter(2);
    }

    @Override
    protected void emptyLoop(JavaStringBuilder builder) {
    }

    @Override
    protected void onLooping(InjectionElement injectionElement, JavaStringBuilder builder) {
        String methodName = injectionElement.elementName;
        String[] viewNames = injectionElement.values;
        boolean massInjection = viewNames.length > 0;

        builder.appendSpace(2).append("CompoundButton.OnCheckedChangeListener ").append(methodName)
                .append("CheckedChangeListener = new CompoundButton" + ".OnCheckedChangeListener() {\n");
        builder.appendSpaceOverride(3);
        builder.appendSpace(3).append("public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {\n");
        builder.appendSpace(4).append("target.").append(methodName).append(massInjection ? "(buttonView, isChecked)" : "(isChecked)").append(";\n");
        builder.appendSpace(3).append("}\n");
        builder.appendSpace(2).append("};\n");
        if (massInjection) {
            for (String viewName : viewNames) {
                builder.appendSpace(2).appendViewCast(viewName, "CompoundButton").append(".setOnCheckedChangeListener(").append(methodName)
                        .append("CheckedChangeListener);\n");
            }
        } else {
            builder.appendSpace(2).appendViewCast(methodName, "CompoundButton").append(".setOnCheckedChangeListener(").append(methodName)
                    .append("CheckedChangeListener);\n");
        }
    }
}