//package com.harreke.easyapp.injection.processor;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.lang.model.element.Element;
//import javax.lang.model.element.ElementKind;
//import javax.lang.model.element.ExecutableElement;
//import javax.lang.model.element.TypeElement;
//import javax.lang.model.type.ArrayType;
//import javax.lang.model.type.DeclaredType;
//import javax.lang.model.type.ExecutableType;
//import javax.lang.model.type.TypeMirror;
//import javax.tools.Diagnostic;
//
//import static javax.lang.model.type.TypeKind.VOID;
//
///**
// * 由huoqisheng于2016/6/9创建
// */
//public class InjectionJsonGenerator {
//    private String mClassName;
//    private List<Element> mGetterList;
//    private String mInjectorName;
//    private String mPackageName;
//    private List<Element> mSetterList;
//
//    public InjectionJsonGenerator(Element target) {
//        mPackageName = InjectionProcessor.elementUtils.getPackageOf(target).getQualifiedName().toString();
//        mClassName = target.getSimpleName().toString();
//        mInjectorName = mPackageName + "." + mClassName + "$$JsonInjector";
//        mSetterList = new ArrayList<>();
//        mGetterList = new ArrayList<>();
//        while (true) {
//            for (Element element : target.getEnclosedElements()) {
//                String methodName = element.getSimpleName().toString();
//                if (element.getKind() == ElementKind.METHOD) {
//                    if (methodName.startsWith("set")) {
//                        ProcessorUtil.checkReturnTypeForMethod(element, VOID);
//                        List<? extends TypeMirror> paramList = ((ExecutableType) element.asType()).getParameterTypes();
//                        if (paramList.size() != 1) {
//                            throw new IllegalArgumentException("Not a valid setter as " + methodName + "!");
//                        }
//                        TypeMirror methodParam = paramList.get(0);
//                        mSetterList.add(element);
//                    } else if (methodName.startsWith("get")) {
//                        if (!(element instanceof ExecutableElement) || ((ExecutableElement) element).getReturnType().getKind() == VOID) {
//                            throw new IllegalArgumentException("Not a valid getter as " + methodName + "!");
//                        }
//                        mGetterList.add(element);
//                    }
//                }
//            }
//            if (target instanceof TypeElement) {
//                target = InjectionProcessor.typeUtils.asElement(((TypeElement) target).getSuperclass());
//            }
//            if (target == null) {
//                break;
//            }
//            String className = target.getSimpleName().toString();
//            if (className.startsWith("android.") || className.startsWith("java.")) {
//                break;
//            }
//        }
//    }
//
//    public String generate() {
//        JavaStringBuilder builder = new JavaStringBuilder();
//
//        builder.append("package ").append(mPackageName).append(";\n");
//        builder.appendEnter();
//
//        builder.appendImport(JavaStringBuilder.JsonObject);
//        builder.appendImport(JavaStringBuilder.JsonArray);
//        builder.appendEnter();
//
//        builder.appendImport("com.harreke.easyapp.injection.IJsonInject");
//        builder.appendEnter();
//
//        builder.append("public class ").append(mClassName).append("$$JsonInjector").append(" implements IJsonInject<").append(mClassName).append("> {");
//        builder.appendEnter();
//        builder.appendSpaceOverride(1);
//        builder.appendSpace(1).append("public String toJson(").append(mClassName).append(" item) {\n");
//        builder.appendSpace(2).append("JsonObject jsonObject = new JsonObject();\n");
//        for (Element getter : mGetterList) {
//            TypeMirror returnType = ProcessorUtil.getReturnTypeForMethod(getter);
//            String returnTypeName = returnType.toString();
//            String getterName = getter.getSimpleName().toString();
//            String keyName = Character.toLowerCase(getterName.charAt(3)) + getterName.substring(4);
//            InjectionProcessor.messager.printMessage(Diagnostic.Kind.WARNING, "inject getter " + getterName + " for key " + keyName);
//            InjectionProcessor.messager.printMessage(Diagnostic.Kind.WARNING, "type " + returnTypeName);
//            if (ProcessorUtil.isArray(returnType)) {
//                generateArray(builder, getterName, keyName, returnType);
//            } else if (returnTypeName.startsWith(JavaStringBuilder.List)) {
//                generateList(builder, getterName, keyName, returnType);
//            } else {
//
//            }
//        }
//
//        builder.appendSpace(2).append("return jsonObject.toString();\n");
//        builder.appendSpace(1).append("}\n");
//
//        builder.appendEnter();
//
//        builder.appendSpace(1).append("public ").append(mClassName).append(" toObject(String item) {\n");
//        builder.appendSpace(2).append("return null;\n");
//        builder.appendSpace(1).append("}\n");
//
//        builder.append("}");
//
//        return builder.toString();
//    }
//
//    private void generateObject(JavaStringBuilder builder, String keyName, String getterName, String returnTypeName) {
//        switch (returnTypeName) {
//            case JavaStringBuilder.String:
//            case JavaStringBuilder.Int:
//            case JavaStringBuilder.Float:
//            case JavaStringBuilder.Double:
//            case JavaStringBuilder.Long:
//            case JavaStringBuilder.Boolean:
//                builder.appendSpace(2).append("jsonObject.put(\"").append(keyName).append("\", item.").append(getterName).append("());\n");
//                break;
//        }
//    }
//
//    private void generateArray(JavaStringBuilder builder, String getterName, String keyName, TypeMirror type) {
//        String componentTypeName = InjectionProcessor.typeUtils.asElement(((ArrayType) type).getComponentType()).getSimpleName().toString();
//        builder.appendSpace(2).append("JsonArray ").append(keyName).append("Array = new JsonArray();\n");
//        builder.appendSpace(2).append("for (").append(componentTypeName).append(" key : item.").append(getterName).append("()) " + "{\n");
//        builder.appendSpace(3).append(keyName).append("Array.add(key);\n");
//        builder.appendSpace(2).append("}\n");
//        builder.appendSpace(2).append("jsonObject.put(\"").append(keyName).append("\", ").append(keyName).append("Array);\n");
//    }
//
//    private void generateList(JavaStringBuilder builder, String getterName, String keyName, TypeMirror type) {
//        List<? extends TypeMirror> typeList = ((DeclaredType) type).getTypeArguments();
//        String componentTypeName;
//        if (typeList == null || typeList.size() == 0) {
//            componentTypeName = JavaStringBuilder.Object;
//        } else {
//            componentTypeName = typeList.get(0).toString();
//        }
//        builder.appendSpace(2).append("JsonArray ").append(keyName).append("Array = new JsonArray();\n");
//        builder.appendSpace(2).append("for (").append(componentTypeName).append(" key : item.").append(getterName).append("()) " + "{\n");
//        builder.appendSpace(3).append(keyName).append("Array.add(key);\n");
//        builder.appendSpace(2).append("}\n");
//        builder.appendSpace(2).append("jsonObject.put(\"").append(keyName).append("\", ").append(keyName).append("Array);\n");
//    }
//
//    public String getInjectorName() {
//        return mInjectorName;
//    }
//}