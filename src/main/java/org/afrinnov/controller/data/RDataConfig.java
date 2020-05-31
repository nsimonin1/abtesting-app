package org.afrinnov.controller.data;

import java.util.Map;

public class RDataConfig {
    private String name;
    private String app;
    private String page;
    private Map<String, Object> context;

    public String getName() {
        return name;
    }

    public String getApp() {
        return app;
    }

    public String getPage() {
        return page;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public static RDataConfigBuilder aRDataConfig() {
        return new RDataConfigBuilder();
    }

    public static final class RDataConfigBuilder {
        private String name;
        private String app;
        private String page;
        private Map<String, Object> context;

        private RDataConfigBuilder() {
        }


        public RDataConfigBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public RDataConfigBuilder withApp(String app) {
            this.app = app;
            return this;
        }

        public RDataConfigBuilder withPage(String page) {
            this.page = page;
            return this;
        }

        public RDataConfig build() {
            RDataConfig rDataConfig = new RDataConfig();
            rDataConfig.page = this.page;
            rDataConfig.app = this.app;
            rDataConfig.name = this.name;
            rDataConfig.context = this.context;
            return rDataConfig;
        }

        public RDataConfigBuilder withContext(Map<String, Object> context) {
            this.context = context;
            return this;
        }
    }
}
