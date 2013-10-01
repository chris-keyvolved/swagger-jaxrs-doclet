package com.hypnoticocelot.jaxrs.doclet.parser;

import java.util.LinkedList;
import java.util.List;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationValue;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.ProgramElementDoc;

public class AnnotationParser {

    private final AnnotationDesc[] annotations;

    public AnnotationParser(ProgramElementDoc element) {
        annotations = element.annotations();
    }

    public AnnotationParser(Parameter parameter) {
        annotations = parameter.annotations();
    }

    public String getAnnotationValue(String qualifiedAnnotationType, String key) {
        AnnotationDesc annotation = getAnnotation(qualifiedAnnotationType);
        if (annotation == null) {
            return null;
        }
        for (AnnotationDesc.ElementValuePair evp : annotation.elementValues()) {
            if (evp.element().name().equals(key)) {
                return evp.value().value().toString();
            }
        }
        return null;
    }

    public List<String> getAnnotationValues(String qualifiedAnnotationType, String key)
    {
        List<String> values = new LinkedList<String>();
        if (qualifiedAnnotationType == null || key == null)
        {
            return values;
        }
        AnnotationDesc annotation = getAnnotation(qualifiedAnnotationType);
        if (annotation == null)
        {
            return values;
        }
        for (AnnotationDesc.ElementValuePair evp : annotation.elementValues())
        {
            if (evp == null || evp.element() == null || evp.value() == null)
            {
                continue;
            }
            if (key.equalsIgnoreCase(evp.element().name()))
            {
                getAnnotationValues(values, evp.value());
                return values;
            }
        }
        return values;
    }
    
    private void getAnnotationValues(List<String> values, AnnotationValue annotationValue)
    {
        if (annotationValue == null)
        {
            return;
        }
        Object value = annotationValue.value();
        if (value == null)
        {
            return;
        }
        if (value instanceof AnnotationValue[])
        {
            AnnotationValue[] elements = (AnnotationValue[]) value;
            for (AnnotationValue element : elements)
            {
                getAnnotationValues(values, element);
            }
        }
        else
        {
            values.add(value.toString());
        }
    }    

    public boolean isAnnotatedBy(String qualifiedAnnotationType) {
        return getAnnotation(qualifiedAnnotationType) != null;
    }

    private AnnotationDesc getAnnotation(String qualifiedAnnotationType) {
        AnnotationDesc found = null;
        for (AnnotationDesc annotation : annotations) {
            try {
                if (annotation.annotationType().qualifiedTypeName().equals(qualifiedAnnotationType)) {
                    found = annotation;
                    break;
                }
            } catch (RuntimeException e) {
                System.err.println(annotation + " has invalid javadoc: " + e.getClass() + ": " + e.getMessage());
            }
        }
        return found;
    }

}
