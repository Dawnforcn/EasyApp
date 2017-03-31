package com.harreke.easyapp.injection.processor.generators;

import com.harreke.easyapp.injection.processor.InjectionElement;
import com.harreke.easyapp.injection.processor.JavaStringBuilder;

/**
 * 由 huoqisheng 于 2016/6/21 创建
 */
public class GenerateBoolean extends AbsGenerator {
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
        String[] booleanNames = injectionElement.values;
        String booleanName;

        if (booleanNames.length == 0) {
            booleanName = injectionElement.elementName;
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").appendBoolean(booleanName).append(";\n");
        } else if (booleanNames.length == 1) {
            booleanName = booleanNames[0];
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").appendBoolean(booleanName).append(";\n");
        } else {
            builder.appendSpace(2).append("boolean[] ").append(injectionElement.elementName).append(" = new boolean[").append(booleanNames.length)
                    .append("];\n");
            for (int i = 0; i < booleanNames.length; i++) {
                builder.appendSpace(2).append(injectionElement.elementName).append("[").append(i).append("] = ").appendBoolean(booleanNames[i]).append(";\n");
            }
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").append(injectionElement.elementName).append(";\n");
        }
    }
}
