package peaksoft.service;

import peaksoft.entity.House;

import java.util.List;

public interface HouseService {
    void saveHouse(Long agencyId, House house);
    List<House> getAllHouse(Long agencyId, String word);
    House getHouseById(Long id);
    void updateHouseById(Long id,House house);
    void deleteHouseById(Long id) ;
}
