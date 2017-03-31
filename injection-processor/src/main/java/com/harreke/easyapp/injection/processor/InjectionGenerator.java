package com.harreke.easyapp.injection.processor;

import com.harreke.easyapp.injection.processor.generators.GenerateAutoDestroy;
import com.harreke.easyapp.injection.processor.generators.GenerateBoolean;
import com.harreke.easyapp.injection.processor.generators.GenerateButtonsCheck;
import com.harreke.easyapp.injection.processor.generators.GenerateCheck;
import com.harreke.easyapp.injection.processor.generators.GenerateClick;
import com.harreke.easyapp.injection.processor.generators.GenerateColor;
import com.harreke.easyapp.injection.processor.generators.GenerateDimension;
import com.harreke.easyapp.injection.processor.generators.GenerateDrawable;
import com.harreke.easyapp.injection.processor.generators.GenerateGroupCheck;
import com.harreke.easyapp.injection.processor.generators.GenerateInt;
import com.harreke.easyapp.injection.processor.generators.GenerateIntArray;
import com.harreke.easyapp.injection.processor.generators.GenerateString;
import com.harreke.easyapp.injection.processor.generators.GenerateStringArray;
import com.harreke.easyapp.injection.processor.generators.GenerateToolbar;
import com.harreke.easyapp.injection.processor.generators.GenerateTouch;
import com.harreke.easyapp.injection.processor.generators.GenerateView;

import javax.lang.model.element.Element;

/**
 * 由huoqisheng于2016/6/9创建
 */
public class InjectionGenerator {
    private String mClassName;
    private GenerateAutoDestroy mGenerateAutoDestroy;
    private GenerateBoolean mGenerateBoolean;
    private GenerateButtonsCheck mGenerateButtonsCheck;
    private GenerateCheck mGenerateCheck;
    private GenerateClick mGenerateClick;
    private GenerateColor mGenerateColor;
    private GenerateDimension mGenerateDimension;
    private GenerateDrawable mGenerateDrawable;
    private GenerateGroupCheck mGenerateGroupCheck;
    private GenerateInt mGenerateInt;
    private GenerateIntArray mGenerateIntArray;
    private GenerateString mGenerateString;
    private GenerateStringArray mGenerateStringArray;
    private GenerateToolbar mGenerateToolbar;
    private GenerateTouch mGenerateTouch;
    private GenerateView mGenerateView;
    private String mInjectorName;
    private boolean mNeedInjectResource = false;
    private String mPackageName;
    
    public InjectionGenerator(Element target, String packageName) {
        mPackageName = packageName;
        mClassName = target.getSimpleName().toString();
        mInjectorName = mPackageName + "." + mClassName + "$$Injector";
    }
    
    public void addAutoDestroy(Element element) {
        if (mGenerateAutoDestroy == null) {
            mGenerateAutoDestroy = new GenerateAutoDestroy();
        }
        mGenerateAutoDestroy.add(new InjectionElement(element, ""));
    }
    
    public void addBoolean(Element element, String[] values) {
        if (mGenerateBoolean == null) {
            mGenerateBoolean = new GenerateBoolean();
        }
        mGenerateBoolean.add(new InjectionElement(element, values));
        mNeedInjectResource = true;
    }
    
    public void addButtonCheck(Element element, String[] values) {
        if (mGenerateButtonsCheck == null) {
            mGenerateButtonsCheck = new GenerateButtonsCheck();
        }
        mGenerateButtonsCheck.add(new InjectionElement(element, values));
        mNeedInjectResource = true;
    }
    
    public void addCheck(Element element, String[] values) {
        if (mGenerateCheck == null) {
            mGenerateCheck = new GenerateCheck();
        }
        mGenerateCheck.add(new InjectionElement(element, values));
        mNeedInjectResource = true;
    }
    
    public void addClick(Element element, String[] values) {
        if (mGenerateClick == null) {
            mGenerateClick = new GenerateClick();
        }
        mGenerateClick.add(new InjectionElement(element, values));
        mNeedInjectResource = true;
    }
    
    public void addColor(Element element, String[] values) {
        if (mGenerateColor == null) {
            mGenerateColor = new GenerateColor();
        }
        mGenerateColor.add(new InjectionElement(element, values));
        mNeedInjectResource = true;
    }
    
    public void addDimension(Element bool, String[] values) {
        if (mGenerateDimension == null) {
            mGenerateDimension = new GenerateDimension();
        }
        mGenerateDimension.add(new InjectionElement(bool, values));
        mNeedInjectResource = true;
    }
    
    public void addDrawable(Element bool, String[] values) {
        if (mGenerateDrawable == null) {
            mGenerateDrawable = new GenerateDrawable();
        }
        mGenerateDrawable.add(new InjectionElement(bool, values));
        mNeedInjectResource = true;
    }
    
    public void addGroupCheck(Element element, String[] values) {
        if (mGenerateGroupCheck == null) {
            mGenerateGroupCheck = new GenerateGroupCheck();
        }
        mGenerateGroupCheck.add(new InjectionElement(element, values));
        mNeedInjectResource = true;
    }
    
    public void addInt(Element element, String[] values) {
        if (mGenerateInt == null) {
            mGenerateInt = new GenerateInt();
        }
        mGenerateInt.add(new InjectionElement(element, values));
        mNeedInjectResource = true;
    }
    
    public void addIntArray(Element element, String value) {
        if (mGenerateIntArray == null) {
            mGenerateIntArray = new GenerateIntArray();
        }
        mGenerateIntArray.add(new InjectionElement(element, value));
        mNeedInjectResource = true;
    }
    
    public void addString(Element element, String[] values) {
        if (mGenerateString == null) {
            mGenerateString = new GenerateString();
        }
        mGenerateString.add(new InjectionElement(element, values));
        mNeedInjectResource = true;
    }
    
    public void addStringArray(Element element, String value) {
        if (mGenerateStringArray == null) {
            mGenerateStringArray = new GenerateStringArray();
        }
        mGenerateStringArray.add(new InjectionElement(element, value));
        mNeedInjectResource = true;
    }
    
    public void addToolbar(Element element, String value) {
        if (mGenerateToolbar == null) {
            mGenerateToolbar = new GenerateToolbar();
        } else {
            mGenerateToolbar.clear();
        }
        mGenerateToolbar.add(new InjectionElement(element, value));
        mNeedInjectResource = true;
    }
    
    public void addTouch(Element element, String[] values) {
        if (mGenerateTouch == null) {
            mGenerateTouch = new GenerateTouch();
        }
        mGenerateTouch.add(new InjectionElement(element, values));
        mNeedInjectResource = true;
    }
    
    public void addView(Element element, String[] values) {
        if (mGenerateView == null) {
            mGenerateView = new GenerateView();
        }
        mGenerateView.add(new InjectionElement(element, values));
        mNeedInjectResource = true;
    }
    
    public String generate() {
        JavaStringBuilder builder = new JavaStringBuilder();
        
        builder.append("package ").append(mPackageName).append(";\n");
        builder.appendEnter();

        builder.appendImport(JavaStringBuilder.Context);
        if (mGenerateDrawable != null) {
            builder.appendImport(JavaStringBuilder.Drawable);
        }
        if (mNeedInjectResource) {
            builder.appendImport(JavaStringBuilder.Resources);
        }
        if (mGenerateTouch != null) {
            builder.appendImport(JavaStringBuilder.MotionEvent);
        }
        builder.appendImport(JavaStringBuilder.View);
        if (mGenerateCheck != null || mGenerateButtonsCheck != null) {
            builder.appendImport(JavaStringBuilder.CompoundButton);
        }
        if (mGenerateGroupCheck != null) {
            builder.appendImport(JavaStringBuilder.RadioButton);
            builder.appendImport(JavaStringBuilder.RadioGroup);
        }
        if (mGenerateToolbar != null) {
            builder.appendImport(JavaStringBuilder.Toolbar);
        }
        builder.appendEnter();
        if (mGenerateButtonsCheck != null) {
            builder.appendImport("com.harreke.easyapp.helper.CompoundButtonHelper");
        }
        builder.appendImport("com.harreke.easyapp.injection.IInject");
        builder.appendEnter();
        
        builder.append("public class ").append(mClassName).append("$$Injector").append("<TARGET extends ").append(mClassName)
                .append("> implements IInject<TARGET> {");
        builder.appendEnter();
        builder.appendSpaceOverride(1);
        builder.appendSpace(1).append("public void inject(final TARGET target, View view) {\n");
        if (mNeedInjectResource) {
            builder.appendSpace(2).appendGetResources();
            builder.appendSpace(2).appendGetPackageName();
        }
        
        //InjectView
        if (mGenerateView != null) {
            mGenerateView.generate(builder);
        }
        
        //InjectClick
        if (mGenerateClick != null) {
            mGenerateClick.generate(builder);
        }
        
        //InjectTouch
        if (mGenerateTouch != null) {
            mGenerateTouch.generate(builder);
        }
        
        //InjectCheck
        if (mGenerateCheck != null) {
            mGenerateCheck.generate(builder);
        }
        
        //InjectGroupCheck
        if (mGenerateGroupCheck != null) {
            mGenerateGroupCheck.generate(builder);
        }
        
        //InjectButtonsCheck
        if (mGenerateButtonsCheck != null) {
            mGenerateButtonsCheck.generate(builder);
        }
        
        //InjectAutoDestroy
        if (mGenerateAutoDestroy != null) {
            mGenerateAutoDestroy.generate(builder);
        }
        
        //InjectToolbar
        if (mGenerateToolbar != null) {
            mGenerateToolbar.generate(builder);
        }
        
        //InjectBoolean
        if (mGenerateBoolean != null) {
            mGenerateBoolean.generate(builder);
        }
        
        //InjectColor
        if (mGenerateColor != null) {
            mGenerateColor.generate(builder);
        }
        
        //InjectDimension
        if (mGenerateDimension != null) {
            mGenerateDimension.generate(builder);
        }
        
        //InjectDrawable
        if (mGenerateDrawable != null) {
            mGenerateDrawable.generate(builder);
        }
        
        //InjectInt
        if (mGenerateInt != null) {
            mGenerateInt.generate(builder);
        }

        //InjectIntArray
        if (mGenerateIntArray != null) {
            mGenerateIntArray.generate(builder);
        }
        
        //InjectString
        if (mGenerateString != null) {
            mGenerateString.generate(builder);
        }

        //InjectStringArray
        if (mGenerateStringArray != null) {
            mGenerateStringArray.generate(builder);
        }
        
        builder.appendSpace(1).append("}\n");
        builder.appendSpaceEnter(1);
        builder.append("}");
        
        return builder.toString();
    }
    
    public String getInjectorName() {
        return mInjectorName;
    }
}