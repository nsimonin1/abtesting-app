package org.afrinnov.tools;

import org.afrinnov.config.AbtestApplicationProperties;
import org.ff4j.FF4j;
import org.springframework.stereotype.Component;

@Component
public class FeatureTools {

    private final FF4j getFF4j;
    private final AbtestApplicationProperties abtestApplicationProperties;

    public FeatureTools(AbtestApplicationProperties applicationProperties, FF4j getFF4j) {
        this.abtestApplicationProperties = applicationProperties;
        this.getFF4j = getFF4j;
    }

    public boolean isNewFeatureCarsListAllow() {
        return checkFeature(FeatureType.CARS_LIST.getFeatureName(getAbtest()));
    }

    public boolean isNewFeatureCarsNewAllow() {
        return checkFeature(FeatureType.CARS_NEW.getFeatureName(getAbtest()));
    }

    public boolean isNewFeatureCarsEditAllow() {
        return checkFeature(FeatureType.CARS_EDIT.getFeatureName(getAbtest()));
    }

    private boolean checkFeature(String featureName) {
        return getFF4j.check(featureName);
    }
    private AbtestApplicationProperties.Abtest getAbtest() {
        return abtestApplicationProperties.getAbtest();
    }


}
