package re.edu.department.validation;

import org.springframework.web.multipart.MultipartFile;
import re.edu.department.exception.BadRequestException;

import java.util.List;

public class FileValidator {

    private static final List<String> ALLOWED_TYPES = List.of(
            "image/jpeg",
            "image/png",
            "image/jpg"
    );

    private static final long MAX_SIZE = 5 * 1024 * 1024; // 5MB

    public static void validateImage(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new BadRequestException("Vui lòng upload ảnh");
        }

        String contentType = file.getContentType();

        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
            throw new BadRequestException("Chỉ chấp nhận file jpg, jpeg, png");
        }

        if (file.getSize() > MAX_SIZE) {
            throw new BadRequestException("File không được vượt quá 5MB");
        }
    }
}
