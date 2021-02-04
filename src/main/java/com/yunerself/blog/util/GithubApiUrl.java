package com.yunerself.blog.util;

public class GithubApiUrl {

    private static final String baseUrl = "https://api.github.com";

    public static String getArtifacts(String owner, String repo) {
        return String.format(baseUrl + "/repos/%s/%s/actions/artifacts", owner, repo);
    }
}
