package com.harreke.easyapp.injection.processor.generators;

import com.harreke.easyapp.injection.processor.InjectionElement;
import com.harreke.easyapp.injection.processor.JavaStringBuilder;

/**
 * 由 huoqisheng 于 2016/6/21 创建
 */
public class GenerateString extends AbsGenerator {
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
        String[] names = injectionElement.values;
        String name;

        if (names.length == 0) {
            name = injectionElement.elementName;
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").appendString(name).append(";\n");
        } else if (names.length == 1) {
            name = names[0];
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").appendString(name).append(";\n");
        } else {
            builder.appendSpace(2).append("String[] ").append(injectionElement.elementName).append(" = new String[").append(names.length).append("];\n");
            for (int i = 0; i < names.length; i++) {
                builder.appendSpace(2).append(injectionElement.elementName).append("[").append(i).append("] = ").appendString(names[i]).append(";\n");
            }
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").append(injectionElement.elementName).append(";\n");
        }
    }
}
