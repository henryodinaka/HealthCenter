/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health.center.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import javax.servlet.http.Part;

/**
 *
 * @author Kelechi
 */
public class FileUpload {

    private final String directory = System.getProperty("user.home") + "\\Receipts\\";

    public FileUpload() {
        try {
            if (Files.notExists(Paths.get(directory), LinkOption.NOFOLLOW_LINKS)) {
                Files.createDirectories(Paths.get(directory));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String upload(Part file, String username) {
        String fileType = "." + file.getContentType().split("/")[1];
        String fileName = null;
        if (file.getContentType().contains("pdf") || file.getContentType().contains("image") && file.getSize() <= 2000000) {
            File f = new File(directory + username + "_receipt" + fileType);
            try {
                if (!f.exists()) {
                    f.createNewFile();
                }
                try(InputStream in = file.getInputStream(); OutputStream out = new FileOutputStream(f)){
                    int c;
                    while((c = in.read()) != -1){
                        out.write(c);
                    }
                    fileName = username + "_receipt" + fileType;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return fileName;
        } else {
            return "error";
        }
    }
    
    public void download(){
    }
}
