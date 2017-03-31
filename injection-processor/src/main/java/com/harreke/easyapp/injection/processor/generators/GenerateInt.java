package com.harreke.easyapp.injection.processor.generators;

import com.harreke.easyapp.injection.processor.InjectionElement;
import com.harreke.easyapp.injection.processor.JavaStringBuilder;

/**
 * 由 huoqisheng 于 2016/6/21 创建
 */
public class GenerateInt extends AbsGenerator {
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
        String[] intNames = injectionElement.values;
        String intName;

        if (intNames.length == 0) {
            intName = injectionElement.elementName;
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").appendInt(intName).append(";\n");
        } else if (intNames.length == 1) {
            intName = intNames[0];
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").appendInt(intName).append(";\n");
        } else {
            builder.appendSpace(2).append("int[] ").append(injectionElement.elementName).append(" = new int[").append(intNames.length).append("];\n");
            for (int i = 0; i < intNames.length; i++) {
                builder.appendSpace(2).append(injectionElement.elementName).append("[").append(i).append("] = ").appendInt(intNames[i]).append(";\n");
            }
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").append(injectionElement.elementName).append(";\n");
        }
    }
}
