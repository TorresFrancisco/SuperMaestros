package com.hector.cinturonnegro.services;

import com.hector.cinturonnegro.models.User;

import com.hector.cinturonnegro.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UserService extends BaseService<User> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public User registerUser(User user){
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }

    public boolean autenticarUsuario(String email, String password){
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            return false;
        } else{
            if (BCrypt.checkpw(password, user.getPassword())){
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean emailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
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
