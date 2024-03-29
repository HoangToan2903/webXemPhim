package webxemphim.com.demo.Util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
public class UploadImage {
    public File handerUpLoadFile(MultipartFile uploadFile) {
        String folderPath = "C:\\webXemPhim\\src\\main\\resources\\static\\img";
        File myUpLoadFolder = new File(folderPath);
        // thiếu thì tạo
        if (!myUpLoadFolder.exists()) {
            myUpLoadFolder.mkdirs();
        }
        File saveFile = null;
        try {
            // Lưu file vào thư mực
            saveFile = new File(myUpLoadFolder, uploadFile.getOriginalFilename());
            // chuyển dữ liệu sang file vừa tạo
            uploadFile.transferTo(saveFile);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return saveFile;
    }
}
