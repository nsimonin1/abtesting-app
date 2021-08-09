package org.afrinnov.tools;

import lombok.RequiredArgsConstructor;
import org.afrinnov.config.AbtestApplicationProperties;
import org.afrinnov.config.AbtestApplicationProperties.Abtest;
import org.ff4j.FF4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FeatureTools {

    private final FF4j getFF4j;
    private final AbtestApplicationProperties abtestApplicationProperties;
    private final HttpSession session;

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
        if(Objects.isNull(session.getAttribute(featureName))) {
            session.setAttribute(featureName, getFF4j.check(featureName));
        }
        return (Boolean) session.getAttribute(featureName);
    }
    private Abtest getAbtest() {
        return abtestApplicationProperties.getAbtest();
    }


}
