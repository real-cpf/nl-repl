package tech.realcpf.pong.run;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class GroovyRun {
    private final static Map<String, GroovyObject> FUNC_CACHE = new HashMap<>(16);
    private GroovyClassLoader loader;
    private final String DEFAULT_START_METHOD_NAME = "main";

    private GroovyRun(){
        this.loader = new GroovyClassLoader();
    }

    private static class GroovyRunHolder {
        static GroovyRun INSTANCE = new GroovyRun();
    }
    public static GroovyRun get(){
        return GroovyRunHolder.INSTANCE;
    }

    public void newGroovy(String script,String name){
        Class clazz = loader.parseClass(script);
        try{
            GroovyObject object = (GroovyObject) clazz.getConstructor().newInstance();
            FUNC_CACHE.put(name,object);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public Optional<Object> doRun(String name, Map<String,Object> params) {
        if (FUNC_CACHE.containsKey(name)) {
             Object result = FUNC_CACHE.get(name).invokeMethod(DEFAULT_START_METHOD_NAME,new Object[]{params});
             return Optional.of(result);
        }else {

            return Optional.empty();
        }
    }
    private String processLangName(String script) {
        return String.format("import tech.realcpf.pong.dsl.FunctionExporter;\n %s",script)
                .replaceAll("\\$\\.","FunctionExporter.");
    }
    public Optional<Object> doOnce(String script) {
        try {
            Class clazz = loader.parseClass(processLangName(script));
            GroovyObject object = (GroovyObject) clazz.getConstructor().newInstance();
            Object result = object.invokeMethod(DEFAULT_START_METHOD_NAME,new Object[]{});
            return Optional.of(result);
        }catch (Exception e){
            throw new RuntimeException();
        }

    }

}
