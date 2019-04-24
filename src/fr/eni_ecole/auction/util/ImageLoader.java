package fr.eni_ecole.auction.util;
import fr.eni_ecole.auction.exceptions.FileException;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.*;


public class ImageLoader {

    // TODO Make it work for any path
    private static final String LOCAL_UPLOAD_PATH = "uploaded_img";
    String path, fileName;

    public ImageLoader(ServletContext sc) {

        this.path = LOCAL_UPLOAD_PATH;
        File uploadDir = new File(this.path);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
    }


    public ImageLoader(String fileName, ServletContext sc) {
        this(sc);
        this.fileName = fileName;
    }

    public String saveFile(Part part) throws FileException {

        // Create path components to save the file
        Part filePart = part;
        String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;

        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            System.out.println("New file " + fileName + " created at " + path);

        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    private String getFileName(Part part) throws FileException {
        if(part == null) {
            return "no-image.jpg";
        }

        if (!part.getContentType().matches("^.*.(png|(jpg|jpeg))$")) {
            throw new FileException(FileException.BAD_TYPE + " : " + part.getContentType());
        }

        if (this.fileName == null) {
            throw new FileException(FileException.NO_NAME_GIVEN);
        }

        this.fileName += '.' + part.getContentType().substring(part.getContentType().indexOf('/') + 1);

        return fileName;
    }

    public static File getImg(String path) {
        File image = new File(LOCAL_UPLOAD_PATH + File.separator + path);
        if(!image.exists()) {
            image = new File(LOCAL_UPLOAD_PATH + File.separator + "no-image.jpg");
        }

        return image;
    }
}
