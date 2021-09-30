package com.hector.cinturonnegro.services;

import com.hector.cinturonnegro.models.Category;
import com.hector.cinturonnegro.models.Publication;
import com.hector.cinturonnegro.models.User;
import com.hector.cinturonnegro.repositories.PublicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PublicationService extends BaseService<Publication>{
    private final PublicationRepository publicationRepository;

    public PublicationService(PublicationRepository publicationRepository) {
        super(publicationRepository);
        this.publicationRepository = publicationRepository;
    }

    public List<Publication> publicationList(String title) {
        return publicationRepository.findByTitleContaining(title);
    }

    public List<Publication> ouroHenrry(Long query){
        return publicationRepository.findByRegionContainingAndEstadoIsTrue(query);
    }

    public List<Publication> ouroHenrry(String query){
        return publicationRepository.findByRegionContainingAndEstadoIsTrue(query);
    }

    public List<Publication> publicacionesPorComuna(Long query){
        return publicationRepository.findByComunaContainingAndEstadoIsTrue(query);
    }

    public List<Publication> publicacionesPorComuna(String query){
        return publicationRepository.findByComunaContainingAndEstadoIsTrue(query);
    }

    public List<Publication> publicacionesPorCategoria(Category category){
        return publicationRepository.findByCategoryAndEstadoIsTrue(category);
    }

    public List<Publication> publicacionesTrue(){
        return publicationRepository.findByEstadoIsTrue();
    }

    public void setImage(User user, MultipartFile file, String url){
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