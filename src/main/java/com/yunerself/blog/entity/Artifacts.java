package com.yunerself.blog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Artifacts {

    @JsonProperty(value = "total_count")
    private Integer totalCount;
    private List<Artifact> artifacts;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    @Override
    public String toString() {
        return "Artifacts{" +
                "totalCount=" + totalCount +
                ", artifacts=" + artifacts +
                '}';
    }
}
