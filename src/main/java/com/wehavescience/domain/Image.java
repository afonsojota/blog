package com.wehavescience.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.mongodb.morphia.annotations.Entity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Entity("images")
@Getter
@Setter
@Accessors(fluent = true)
public class Image extends AbstractEntity {
    private byte[] image;
    private String contentType;

    public Image() {
    }

    public Image(byte[] image) {
        this.image = image;
    }

    public Image(File image) throws IOException {
        Path path = Paths.get(image.getAbsolutePath());
        this.image = Files.readAllBytes(path);
    }

    public Image(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        this.image = Files.readAllBytes(path);
    }
}
