package com.harreke.easyapp.injection.processor.generators;

import com.harreke.easyapp.injection.processor.InjectionElement;
import com.harreke.easyapp.injection.processor.JavaStringBuilder;

/**
 由 huoqisheng 于 2016/6/20 创建
 */
public class GenerateButtonsCheck extends AbsGenerator {
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

        builder.appendSpace(2)
                .append("CompoundButtonHelper.OnButtonCheckedChangeListener ")
                .append(methodName)
                .append("ButtonCheckedChangeListener = new CompoundButtonHelper.OnButtonCheckedChangeListener() { \n");
        builder.appendSpace(3).appendOverride();
        builder.appendSpace(3).append("public void onButtonCheck(CompoundButton compoundButton, int position) {\n");
        builder.appendSpace(4).append("target.").append(methodName).append("(compoundButton, position);\n");
        builder.appendSpace(3).append("}\n");
        builder.appendSpace(2).append("};\n");
        builder.appendSpace(2).append("new CompoundButtonHelper(");
        builder.appendViewCast(viewNames[0], "CompoundButton");
        if (viewNames.length > 1) {
            for (int i = 1; i < viewNames.length; i++) {
                builder.append(", ").appendViewCast(viewNames[i], "CompoundButton");
            }
        }
        builder.append(").setOnButtonCheckedChangeListener(").append(methodName).append("ButtonCheckedChangeListener);\n");
    }
}
