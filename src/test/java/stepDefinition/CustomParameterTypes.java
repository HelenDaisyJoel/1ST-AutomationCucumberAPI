package stepDefinition;

import io.cucumber.java.ParameterType;

public class CustomParameterTypes {

    @ParameterType("true|false")
    public boolean booleanType(String value) {
        return Boolean.parseBoolean(value);
    }
}
