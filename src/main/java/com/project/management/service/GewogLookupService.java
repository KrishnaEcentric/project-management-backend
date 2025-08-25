package com.project.management.service;

import com.project.management.dto.DzongkhagDTO;
import com.project.management.dto.GewogDTO;
import com.project.management.entity.GewogLookup;
import com.project.management.repository.GewogLookupRepository;
import com.project.management.repository.VillageLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GewogLookupService {

    @Autowired
    private GewogLookupRepository gewogLookupRepository;

    @Autowired
    private VillageLookupRepository villageLookupRepository;

    public List<GewogDTO> getAllGewogs() {
        List<GewogLookup> gewogs = gewogLookupRepository.findAll();
        return gewogs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public GewogDTO getGewogByVillageSerialNo(Integer villageSerialNo) {
        com.project.management.entity.GewogLookup gewog = villageLookupRepository.findGewogByVillageSerialNo(villageSerialNo);
        if (gewog != null) {
            return convertToDTO(gewog);
        }
        return null;
    }

    public List<GewogDTO> getGewogsByDzongkhagSerialNo(Integer dzongkhagSerialNo) {
        List<GewogLookup> gewogs = gewogLookupRepository.findByDzongkhagSerialNo(dzongkhagSerialNo);
        return gewogs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public GewogDTO getGewogById(Integer gewogSerialNo) {
        return gewogLookupRepository.findById(gewogSerialNo)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public GewogDTO getGewogByGewogId(String gewogId) {
        return gewogLookupRepository.findByGewogId(gewogId)
                .map(this::convertToDTO)
                .orElse(null);
    }

    private GewogDTO convertToDTO(GewogLookup gewog) {
        return new GewogDTO(
                gewog.getGewogSerialNo(),
                gewog.getGewogId(),
                gewog.getGewogName(),
                gewog.getGewogNameBh(),
                convertToDzongkhagDTO(gewog.getDzongkhag()),
                gewog.getDungkhagSerialNo(),
                gewog.getCreatedAt()
        );
    }

    private DzongkhagDTO convertToDzongkhagDTO(com.project.management.entity.DzongkhagLookup dzongkhag) {
        if (dzongkhag == null) {
            return null;
        }
        return new DzongkhagDTO(
                dzongkhag.getDzongkhagSerialNo(),
                dzongkhag.getDzongkhagId(),
                dzongkhag.getDzongkhagName(),
                dzongkhag.getDzongkhagNameBh(),
                dzongkhag.getCreatedAt()
        );
    }
}