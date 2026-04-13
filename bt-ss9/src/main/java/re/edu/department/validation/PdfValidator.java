package re.edu.department.validation;

import org.springframework.web.multipart.MultipartFile;
import re.edu.department.exception.InvalidFileException;

public class PdfValidator {

    private static final long MAX_SIZE = 5 * 1024 * 1024; // 5MB

    public static void validate(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new InvalidFileException("File CV không được để trống");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.equals("application/pdf")) {
            throw new InvalidFileException("Chỉ chấp nhận file PDF");
        }

        if (file.getSize() > MAX_SIZE) {
            throw new InvalidFileException("File không được vượt quá 5MB");
        }
    }
}
