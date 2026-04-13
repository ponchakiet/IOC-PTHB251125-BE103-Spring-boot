package re.edu.service.implement;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import re.edu.dto.request.ReaderCreate;
import re.edu.dto.response.ReaderResponse;
import re.edu.exception.BadRequestException;
import re.edu.mapper.ReaderMapper;
import re.edu.model.Reader;
import re.edu.repositories.ReaderRepository;
import re.edu.service.IReaderService;
import re.edu.validation.FileValidator;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements IReaderService {

    private final ReaderRepository readerRepository;
    private final Cloudinary cloudinary;

    @Override
    public ReaderResponse createReader(ReaderCreate request) {

        if (readerRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email đã tồn tại trong hệ thống");
        }

        MultipartFile file = request.getAvatarFile();
        FileValidator.validateImage(file);

        try {
            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap()
            );

            String imageUrl = uploadResult.get("url").toString();

            Reader entity = ReaderMapper.toEntity(request);

            entity.setAvatar(imageUrl);
            Reader saved = readerRepository.save(entity);

            return ReaderMapper.toResponse(saved);

        } catch (IOException e) {
            throw new BadRequestException("Upload ảnh thất bại");
        }
    }
}
