package org.example.ind11api.service;

import jakarta.transaction.Transactional;
import org.example.ind11api.exceptions.StudentNotFoundException;
import org.example.ind11api.model.Avatar;
import org.example.ind11api.model.Student;
import org.example.ind11api.repository.AvatarRepository;
import org.example.ind11api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.zip.GZIPInputStream;

@Service
public class AvatarService {

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;
    private final String avatarsDir;

    public AvatarService(StudentRepository studentRepository,
                         AvatarRepository avatarRepository,
                         @Value("${avatars.dir}") String avatarsDir) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
        this.avatarsDir = avatarsDir;
    }

    @Transactional
    public void upload(long studentId, MultipartFile file) throws IOException {
        var student = studentRepository.findById(studentId)
                .orElseThrow(StudentNotFoundException::new);

        var dir = Path.of(avatarsDir);
        if (!dir.toFile().exists()) {
            Files.createDirectories(dir);
        }
        var path = saveFile(file, student);

        Avatar avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setFilePath(path);
        avatar.setData(file.getBytes());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setStudent(student);
        avatarRepository.save(avatar);
    }

    private String saveFile(MultipartFile file, Student student) {
        var originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            originalFilename = "file.jpg";
        }
        var dotIndex = originalFilename.lastIndexOf('.');
        var ext = originalFilename.substring(dotIndex + 1);
        var path = avatarsDir + "/" + student.getId() + "_" + student.getName() + "." + ext;
        try (var in = file.getInputStream();
             var out = new FileOutputStream(path)) {
            in.transferTo(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    @Transactional
    public Avatar find(long studentId) {
        return avatarRepository.findByStudentId(studentId).orElse(null);
    }

    public Collection<Avatar> find(int page, int pageSize) {
        return avatarRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    /*  FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            out.write(121);
        } catch (IOException e) {

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }*/
}
