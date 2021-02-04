package com.yunerself.blog.entity;

import java.util.List;

public class Artifacts {
    private Integer total;
    private List<Artifact> artifacts;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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
                "total=" + total +
                ", artifacts=" + artifacts +
                '}';
    }
}
