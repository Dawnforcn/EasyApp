package com.harreke.easyapp.injection.processor.generators;

import com.harreke.easyapp.injection.processor.InjectionElement;
import com.harreke.easyapp.injection.processor.JavaStringBuilder;

/**
 * 由 huoqisheng 于 2016/6/21 创建
 */
public class GenerateIntArray extends AbsGenerator {
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
        String intArrayName = injectionElement.values[0];

        if (intArrayName.length() == 0) {
            intArrayName = injectionElement.elementName;
        }
        builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").appendIntArray(intArrayName).append(";\n");
    }
}
