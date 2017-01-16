package FinTechOne.FOGS.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HALResourceSupport extends ResourceSupport {

    private final Map<String, List<Resource>> embedded = new HashMap<String, List<Resource>>();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("_embedded")
    public Map<String, List<Resource>> getEmbeddedResources() {
        return embedded;
    }

    public void embedResource(String relationship, List<Resource> resources) {
        embedded.put(relationship, resources);
    }
}
