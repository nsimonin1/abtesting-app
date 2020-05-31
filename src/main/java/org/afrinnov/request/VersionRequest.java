package org.afrinnov.request;

import javax.validation.constraints.NotNull;

public class VersionRequest {
    @NotNull
    private  Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
