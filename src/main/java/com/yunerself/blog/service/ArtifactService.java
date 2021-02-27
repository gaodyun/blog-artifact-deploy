package com.yunerself.blog.service;

import com.yunerself.blog.entity.Artifact;
import com.yunerself.blog.entity.Artifacts;
import com.yunerself.blog.util.ArtifactFile;
import com.yunerself.blog.util.GithubApiUrl;
import com.yunerself.blog.util.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ArtifactService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtifactService.class);

    @Value("${github.owner}")
    private String owner;

    @Value("${github.repo}")
    private String repo;

    @Value("${github.scope}")
    private Boolean scope;

    @Value("${github.token}")
    private String token;

    @Value("${artifact.name}")
    private String artifactFileName;

    @Value("${shell.path}")
    private String shellFilePath;

    @Value("${shell.name}")
    private String blogName;

    @Value("${shell.nginx}")
    private String nginxFilePath;

    @Value("${shell.artifact}")
    private String artifactFilePath;

    public void updateArtifact() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

        if (scope) {
            restTemplateBuilder = new RestTemplateBuilder().
                    defaultHeader("Authorization", "Token " + token);
        }

        RestTemplate restTemplate = restTemplateBuilder.build();

        String artifactsApi = GithubApiUrl.getArtifacts(owner, repo);
        LOGGER.info("请求Github[{}]获取Artifacts列表", artifactsApi);

        Artifacts artifacts = restTemplate.getForObject(artifactsApi, Artifacts.class);
        LOGGER.info("接口应答信息: {}", artifacts.toString());

        List<Artifact> artifactList = artifacts.getArtifacts();
        Collections.sort(artifactList);
        LOGGER.info("Artifacts列表排序结果: {}", artifactList);

        Artifact artifactLatest = artifactList.get(0);
        LOGGER.info("最新的Artifact: {}", artifactLatest);

        String downloadURL = artifactLatest.getArchiveDownloadUrl();
        byte[] artifactBytes = restTemplate.getForObject(downloadURL, byte[].class);
        ArtifactFile.createFile(artifactBytes, artifactFileName);

        int shellExecResult = Shell.exec(shellFilePath, blogName, nginxFilePath, artifactFilePath);
        if (0 == shellExecResult) {
            LOGGER.info("Artifact更新成功");
        } else {
            LOGGER.info("Artifact更新失败");
        }
    }

    public static void main(String[] args) {
        Artifact artifact = new Artifact();
        artifact.setUpdatedDate(new Date(99, 2, 18));
        Artifact artifact1 = new Artifact();
        artifact1.setUpdatedDate(new Date(99, 3, 18));
        Artifact artifact2 = new Artifact();
        artifact2.setUpdatedDate(new Date(99, 4, 18));
        List<Artifact> artifacts = new ArrayList<>();
        artifacts.add(artifact);
        artifacts.add(artifact1);
        artifacts.add(artifact2);
        System.out.println("before: " + artifacts.toString());
        Collections.sort(artifacts);
        System.out.println("after: " + artifacts.toString());
    }
}
