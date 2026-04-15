package re.edu.mapper;

import re.edu.dto.request.ReaderCreate;
import re.edu.dto.response.ReaderResponse;
import re.edu.model.Reader;

public class ReaderMapper {
    public static Reader toEntity(ReaderCreate dto) {
        return Reader.builder()
                .email(dto.getEmail())
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .build();
    }

    public static ReaderResponse toResponse(Reader reader) {
        return ReaderResponse.builder()
                .id(reader.getId())
                .email(reader.getEmail())
                .fullName(reader.getFullName())
                .phoneNumber(reader.getPhoneNumber())
                .address(reader.getAddress())
                .avatar(reader.getAvatar())
                .build();
    }
}
