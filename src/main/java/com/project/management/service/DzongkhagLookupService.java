package com.project.management.service;

import com.project.management.dto.DzongkhagDTO;
import com.project.management.entity.DzongkhagLookup;
import com.project.management.repository.DzongkhagLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DzongkhagLookupService {

    @Autowired
    private DzongkhagLookupRepository dzongkhagLookupRepository;

    public List<DzongkhagDTO> getAllDzongkhags() {
        List<DzongkhagLookup> dzongkhags = dzongkhagLookupRepository.findAll();
        return dzongkhags.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DzongkhagDTO convertToDTO(DzongkhagLookup dzongkhag) {
        return new DzongkhagDTO(
                dzongkhag.getDzongkhagSerialNo(),
                dzongkhag.getDzongkhagId(),
                dzongkhag.getDzongkhagName(),
                dzongkhag.getDzongkhagNameBh(),
                dzongkhag.getCreatedAt()
        );
    }
}