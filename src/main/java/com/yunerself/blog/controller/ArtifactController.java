package com.yunerself.blog.controller;

import com.yunerself.blog.service.ArtifactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtifactController {
    private static final Logger LOGGER  = LoggerFactory.getLogger(ArtifactController.class);

    @Autowired
    ArtifactService artifactService;

    @GetMapping("/artifact")
    public void fetchArtifact() {
        LOGGER.info("/artifact GET 请求已收到");
        artifactService.updateArtifact();
    }
}
