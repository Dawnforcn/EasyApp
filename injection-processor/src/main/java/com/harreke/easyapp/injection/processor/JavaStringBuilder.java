package com.harreke.easyapp.injection.processor;

/**
 * 由huoqisheng于2016/6/13创建
 */
public class JavaStringBuilder {
    public static final String AppCompatActivity = "android.support.v7.app.AppCompatActivity";
    public static final String Boolean = "boolean";
    public static final String CompoundButton = "android.widget.CompoundButton";
    public static final String Context = "android.content.Context";
    public static final String Double = "double";
    public static final String Drawable = "android.graphics.drawable.Drawable";
    public static final String Float = "float";
    public static final String IDestroyable = "com.harreke.easyapp.common.interf.IDestroyable";
    public static final String Int = "int";
//    public static final String JsonArray = "com.harreke.easyapp.misc.widgets.easyjson.JsonArray";
//    public static final String JsonObject = "com.harreke.easyapp.misc.widgets.easyjson.JsonObject";
    public static final String List = "java.util.List";
    public static final String Long = "long";
    public static final String MotionEvent = "android.view.MotionEvent";
    public static final String Object = "java.lang.Object";
    public static final String RadioButton = "android.widget.RadioButton";
    public static final String RadioGroup = "android.widget.RadioGroup";
    public static final String Resources = "android.content.res.Resources";
    public static final String String = "java.lang.String";
    public static final String Toolbar = "android.support.v7.widget.Toolbar";
    public static final String View = "android.view.View";
    private StringBuilder mBuilder = new StringBuilder();

    public JavaStringBuilder append(char ch) {
        mBuilder.append(ch);

        return this;
    }

    public JavaStringBuilder append(String input) {
        mBuilder.append(input);

        return this;
    }

    public JavaStringBuilder append(int input) {
        mBuilder.append(input);

        return this;
    }
    
    public JavaStringBuilder appendBoolean(String name) {
        return append("resources.getBoolean(").appendId(name, "bool").append(")");
    }
    
    public JavaStringBuilder appendColor(String name) {
        return append("resources.getColor(").appendId(name, "color").append(")");
    }

    public JavaStringBuilder appendDimension(String name) {
        return append("resources.getDimension(").appendId(name, "dimen").append(")");
    }

    public JavaStringBuilder appendDrawable(String name) {
        return append("resources.getDrawable(").appendId(name, "drawable").append(")");
    }

    public JavaStringBuilder appendEnter() {
        mBuilder.append("\n");

        return this;
    }

    public JavaStringBuilder appendGetPackageName() {
        mBuilder.append("String packageName = view.getContext().getPackageName();\n");

        return this;
    }

    public JavaStringBuilder appendGetResources() {
        mBuilder.append("Resources resources = view.getResources();\n");

        return this;
    }

    public JavaStringBuilder appendId(String name, String type) {
        mBuilder.append("resources.getIdentifier(\"").append(name).append("\", \"").append(type).append("\", packageName)");

        return this;
    }

    public JavaStringBuilder appendImport(String input) {
        mBuilder.append("import ").append(input).append(";\n");

        return this;
    }

    public JavaStringBuilder appendInt(String name) {
        return append("resources.getInteger(").appendId(name, "integer").append(")");
    }

    public JavaStringBuilder appendIntArray(String name) {
        return append("resources.getIntArray(").appendId(name, "integer-array").append(")");
    }

    public JavaStringBuilder appendOverride() {
        mBuilder.append("@Override\n");

        return this;
    }

    public JavaStringBuilder appendSpace(int count) {
        for (int i = 0; i < count; i++) {
            mBuilder.append("    ");
        }

        return this;
    }

    public JavaStringBuilder appendSpaceEnter(int count) {
        return appendSpace(count).appendEnter();
    }

    public JavaStringBuilder appendSpaceOverride(int count) {
        return appendSpace(count).appendOverride();
    }
    
    public JavaStringBuilder appendString(String name) {
        return append("resources.getString(").appendId(name, "string").append(")");
    }

    public JavaStringBuilder appendStringArray(String name) {
        return append("resources.getStringArray(").appendId(name, "array").append(")");
    }

    public JavaStringBuilder appendView(String viewName) {
        return append("view.findViewById(").appendId(viewName, "id").append(")");
    }

    public JavaStringBuilder appendViewCast(String viewName, String castCanonicalName) {
        return append("((").append(castCanonicalName).append(") ").appendView(viewName).append(")");
    }

    @Override
    public String toString() {
        return mBuilder.toString();
    }
}