package cn.superstallion;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@SupportedAnnotationTypes({"cn.superstallion.ToString"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.isEmpty()) {
            return true;
        } else {
            try {
                JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile("cn.superstallion.ToStrings");
                try (PrintWriter out = new PrintWriter(sourceFile.openWriter())) {
                    for (Element e : roundEnv.getElementsAnnotatedWith(ToString.class)) {
                        if (e instanceof TypeElement) {
                            TypeElement te = (TypeElement) e;
                            toStringMethods(out, te);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public void toStringMethods(PrintWriter out, TypeElement element) {

        Name className = element.getQualifiedName();
        StringBuilder result = new StringBuilder();
        result.append("package cn.superstallion;\n");
        result.append("public class ToStrings{\n");
        ToString annotation = element.getAnnotation(ToString.class);
        if (annotation.includeName()) {
            result.append("public static String toString(");
            result.append(className);
            result.append(" obj){\n");
            for (Element e : element.getEnclosedElements()) {
                ToString a = e.getAnnotation(ToString.class);
                if (a != null) {
                    if (a.includeName()) {
                        result.append("return");
                        result.append(" obj.");
                        result.append(e.getSimpleName());
                        result.append("();}\n");
                    }
                }
            }
        }
        result.append("}");
        out.println(result);
    }
}
