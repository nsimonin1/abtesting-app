package org.afrinnov.tools;

import lombok.Getter;
import org.afrinnov.config.AbtestApplicationProperties;

@Getter
public enum FeatureType {
    CARS_LIST("new_cars_list", "new_cars_list_weight"),
    CARS_NEW("new_cars_new", "new_cars_new_weight"),
    CARS_EDIT("new_cars_edit", "new_cars_edit_weight");
    private final String classicOnOf;
    private final String ponderationOnOf;

    FeatureType(String classicOnOf, String ponderationOnOf) {
        this.classicOnOf = classicOnOf;
        this.ponderationOnOf = ponderationOnOf;
    }

    public String getFeatureName(AbtestApplicationProperties.Abtest abtest) {
        return abtest.isPonderationStrategy() ? ponderationOnOf : classicOnOf;
    }
}
