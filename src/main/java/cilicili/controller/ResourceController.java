package cilicili.controller;

import cilicili.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ResourceController {
    private ResourceService imageService;

    @Autowired
    public void setImageService(ResourceService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path = "/image/{imageId}")
    public ResponseEntity<Resource> serveFile(@PathVariable Integer imageId) {
        String path = imageService.getResourcePath(imageId);
        Resource file = imageService.load(path);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
