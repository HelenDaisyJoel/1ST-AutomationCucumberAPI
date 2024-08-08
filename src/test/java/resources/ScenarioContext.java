package resources;

import java.util.HashMap;
import java.util.Map;
//
////public class ScenarioContext {
////    private Map<String, Object> scenarioContext;
////
////    public ScenarioContext() {
////        scenarioContext = new HashMap<>();
////    }
////
////    public void setContext(String key, Object value) {
////        scenarioContext.put(key, value);
////    }
////
////    public Object getContext(String key) {
////        return scenarioContext.get(key);
////    }
////
////    public Boolean isContains(String key) {
////        return scenarioContext.containsKey(key);
////    }
////}
//public class ScenarioContext {
//    private Map<String, Object> scenarioContextMap;
//
//    public ScenarioContext() {
//        scenarioContextMap = new HashMap<>();
//    }
//
//    public void setContext(String key, Object value) {
//        scenarioContextMap.put(key, value);
//    }
//
//    public Object getContext(String key) {
//        return scenarioContextMap.get(key);
//    }
//}


public class ScenarioContext {
    private static Map<String, Object> scenarioContextMap = new HashMap<>();

    public static void setContext(String key, Object value) {
        scenarioContextMap.put(key, value);
    }

    public static Object getContext(String key) {
        return scenarioContextMap.get(key);
    }
}
