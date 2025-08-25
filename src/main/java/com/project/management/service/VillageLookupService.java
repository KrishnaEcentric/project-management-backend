package com.project.management.service;

import com.project.management.dto.DzongkhagDTO;
import com.project.management.dto.GewogDTO;
import com.project.management.dto.VillageDTO;
import com.project.management.entity.DzongkhagLookup;
import com.project.management.entity.VillageLookup;
import com.project.management.repository.VillageLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VillageLookupService {

    @Autowired
    private VillageLookupRepository villageLookupRepository;

    public List<VillageDTO> getAllVillages() {
        List<VillageLookup> villages = villageLookupRepository.findAll();
        return villages.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<VillageDTO> getVillagesByGewogSerialNo(Integer gewogSerialNo) {
        List<VillageLookup> villages = villageLookupRepository.findByGewogSerialNo(gewogSerialNo);
        return villages.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DzongkhagDTO getDzongkhagByGewogSerialNo(Integer gewogSerialNo) {
        List<VillageLookup> villages = villageLookupRepository.findByGewogSerialNo(gewogSerialNo);
        if (!villages.isEmpty() && villages.get(0).getGewog() != null) {
            return convertToDTO(villages.get(0).getGewog().getDzongkhag());
        }
        return null;
    }

    public DzongkhagDTO getDzongkhagByVillageSerialNo(Integer villageSerialNo) {
        DzongkhagLookup dzongkhag = villageLookupRepository.findDzongkhagByVillageSerialNo(villageSerialNo);
        if (dzongkhag != null) {
            return convertToDTO(dzongkhag);
        }
        return null;
    }

    public GewogDTO getGewogByVillageSerialNo(Integer villageSerialNo) {
        com.project.management.entity.GewogLookup gewog = villageLookupRepository.findGewogByVillageSerialNo(villageSerialNo);
        if (gewog != null) {
            return convertToDTO(gewog);
        }
        return null;
    }

    private VillageDTO convertToDTO(VillageLookup village) {
        return new VillageDTO(
                village.getVillageSerialNo(),
                village.getVillageId(),
                village.getVillageName(),
                village.getVillageNameBh(),
                convertToDTO(village.getGewog()),
                village.getChiwogSerialNo(),
                village.getStatus(),
                village.getCreatedAt()
        );
    }

    private GewogDTO convertToDTO(com.project.management.entity.GewogLookup gewog) {
        return new GewogDTO(
                gewog.getGewogSerialNo(),
                gewog.getGewogId(),
                gewog.getGewogName(),
                gewog.getGewogNameBh(),
                convertToDTO(gewog.getDzongkhag()),
                gewog.getDungkhagSerialNo(),
                gewog.getCreatedAt()
        );
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