package com.yunerself.blog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class Artifact implements Comparable<Artifact> {

    private String id;

    private String name;

    @JsonProperty(value = "archive_download_url")
    private String archiveDownloadUrl;

    private Boolean expired;

    @JsonProperty(value = "created_at")
    private Date createdDate;

    @JsonProperty(value = "updated_at")
    private Date updatedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArchiveDownloadUrl() {
        return archiveDownloadUrl;
    }

    public void setArchiveDownloadUrl(String archiveDownloadUrl) {
        this.archiveDownloadUrl = archiveDownloadUrl;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Artifact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", archiveDownloadUrl='" + archiveDownloadUrl + '\'' +
                ", expired=" + expired +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    @Override
    public int compareTo(Artifact artifact) {
        return (-DateUtils.truncatedCompareTo(this.updatedDate, artifact.updatedDate, Calendar.SECOND));
    }
}
