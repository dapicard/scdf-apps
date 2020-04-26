package dapicard.stream.common.configuration.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "domain")
public class DomainReference {

    /**
     * Domain model name (e.g. adresse)
     */
    private String domainName;

    /**
     * Domain model profile (e.g. ban, stands for Base Adresse Nationale)
     */
    private String domainProfile;

    /**
     * Domain model version
     */
    private String domainVersion;

    public String getDomainName() {
        return this.domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainProfile() {
        return this.domainProfile;
    }

    public void setDomainProfile(String domainProfile) {
        this.domainProfile = domainProfile;
    }

    public String getDomainVersion() {
        return this.domainVersion;
    }

    public void setDomainVersion(String domainVersion) {
        this.domainVersion = domainVersion;
    }

}