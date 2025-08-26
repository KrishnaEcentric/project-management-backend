package com.project.management.service;

import com.project.management.dto.QualificationLookupDTO;
import com.project.management.entity.QualificationLookup;
import com.project.management.repository.QualificationLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QualificationLookupService {

    @Autowired
    private QualificationLookupRepository qualificationLookupRepository;

    public QualificationLookupDTO createQualification(QualificationLookupDTO qualificationDTO) {
        // Check if qualificationId already exists
        if (qualificationLookupRepository.existsByQualificationId(qualificationDTO.getQualificationId())) {
            throw new RuntimeException("Qualification ID already exists: " + qualificationDTO.getQualificationId());
        }

        QualificationLookup qualification = convertToEntity(qualificationDTO);
        QualificationLookup savedQualification = qualificationLookupRepository.save(qualification);
        return convertToDTO(savedQualification);
    }

    public List<QualificationLookupDTO> getAllQualifications() {
        List<QualificationLookup> qualifications = qualificationLookupRepository.findAll();
        return qualifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public QualificationLookupDTO getQualificationByQualificationId(Integer qualificationId) {
        return qualificationLookupRepository.findByQualificationId(qualificationId)
                .map(this::convertToDTO)
                .orElse(null);
    }

    private QualificationLookup convertToEntity(QualificationLookupDTO dto) {
        QualificationLookup qualification = new QualificationLookup();
        qualification.setQualificationId(dto.getQualificationId());
        qualification.setQualificationName(dto.getQualificationName());
        qualification.setCreatedBy(dto.getCreatedBy());
        qualification.setUpdatedBy(dto.getUpdatedBy());
        return qualification;
    }

    private QualificationLookupDTO convertToDTO(QualificationLookup qualification) {
        return new QualificationLookupDTO(
                qualification.getId(),
                qualification.getQualificationId(),
                qualification.getQualificationName(),
                qualification.getCreatedBy(),
                qualification.getCreatedAt(),
                qualification.getUpdatedBy(),
                qualification.getUpdatedAt()
        );
    }
}