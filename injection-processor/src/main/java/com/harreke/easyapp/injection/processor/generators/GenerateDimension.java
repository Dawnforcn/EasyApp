package com.harreke.easyapp.injection.processor.generators;

import com.harreke.easyapp.injection.processor.InjectionElement;
import com.harreke.easyapp.injection.processor.JavaStringBuilder;

/**
 * 由 huoqisheng 于 2016/6/21 创建
 */
public class GenerateDimension extends AbsGenerator {
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
        String[] dimensionNames = injectionElement.values;
        String dimensionName;

        if (dimensionNames.length == 0) {
            dimensionName = injectionElement.elementName;
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").appendDimension(dimensionName).append(";\n");
        } else if (dimensionNames.length == 1) {
            dimensionName = dimensionNames[0];
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").appendDimension(dimensionName).append(";\n");
        } else {
            builder.appendSpace(2).append("float[] ").append(injectionElement.elementName).append(" = new float[").append(dimensionNames.length).append("];\n");
            for (int i = 0; i < dimensionNames.length; i++) {
                builder.appendSpace(2).append(injectionElement.elementName).append("[").append(i).append("] = ").appendDimension(dimensionNames[i])
                        .append(";\n");
            }
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").append(injectionElement.elementName).append(";\n");
        }
    }
}
