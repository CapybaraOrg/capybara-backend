package org.capybara.capybarabackend.common.clients.github;

import javax.validation.constraints.NotBlank;

public class WorkflowDispatchRequest {

    private String owner;

    private String name;

    private String workflowId;

    private String ref;

    @NotBlank
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    @NotBlank
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return String.format(
                "WorkflowDispatchRequest[" +
                        "owner='%s', " +
                        "name='%s', " +
                        "workflowId='%s', " +
                        "ref='%s'" +
                        "]",
                getOwner(),
                getName(),
                getWorkflowId(),
                getRef()
        );
    }

}
