package com.harreke.easyapp.injection.processor.generators;

import com.harreke.easyapp.injection.processor.InjectionElement;
import com.harreke.easyapp.injection.processor.JavaStringBuilder;

/**
 * 由huoqisheng于2016/6/20创建
 */
public class GenerateGroupCheck extends AbsGenerator {
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

        builder.appendSpace(2).append("RadioGroup.OnCheckedChangeListener ").append(methodName)
                .append("CheckedChangeListener = new RadioGroup.OnCheckedChangeListener() { \n");
        builder.appendSpace(3).appendOverride();
        builder.appendSpace(3).append("public void onCheckedChanged(RadioGroup group, int checkedId) {\n");
        builder.appendSpace(4).append("RadioButton button = (").append(JavaStringBuilder.RadioButton).append(") group.findViewById(checkedId);\n");
        builder.appendSpace(4).append("int index = group.indexOfChild(button);\n");
        builder.appendSpaceEnter(4);
        builder.appendSpace(4).append("target.").append(methodName).append(massInjection ? "(group, button, index);\n" : "(button, index);\n");
        builder.appendSpace(3).append("}\n");
        builder.appendSpace(2).append("};\n");
        if (massInjection) {
            for (String viewName : viewNames) {
                builder.appendSpace(2).appendViewCast(viewName, "RadioGroup").append(".setOnCheckedChangeListener(").append(methodName)
                        .append("CheckedChangeListener);\n");
            }
        } else {
            builder.appendSpace(2).appendViewCast(methodName, "RadioGroup").append(".setOnCheckedChangeListener(").append(methodName)
                    .append("CheckedChangeListener);\n");
        }
    }
}
