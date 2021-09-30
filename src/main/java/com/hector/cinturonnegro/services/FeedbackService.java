package com.hector.cinturonnegro.services;

import com.hector.cinturonnegro.models.Feedback;
import com.hector.cinturonnegro.models.User;
import com.hector.cinturonnegro.repositories.FeedbackRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FeedbackService extends BaseService<Feedback>{
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        super(feedbackRepository);
        this.feedbackRepository = feedbackRepository;
    }

    public void setImage(MultipartFile file, String url){
        File folder = new File(url);
        System.out.println(url);
        if (folder.exists() == false) {
            folder.mkdirs();
            System.out.println("directorios creados");
        }
        byte[] bytes;
        try {
            bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(folder.getAbsolutePath() + "/" + file.getOriginalFilename())));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}