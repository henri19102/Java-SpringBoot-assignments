/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Henri
 */
@Service
public class LocationCache {

    @Autowired
    private LocationRepository locationRepo;

    @Cacheable(cacheNames = "locations")
    public List<Location> all() {
        return locationRepo.findAll();
    }

    @Cacheable(cacheNames = "locations")
    public Location findOne(Long id) {
        return locationRepo.getOne(id);
    }

    @CacheEvict(value="locations", allEntries = true)
    public Location saveLocation(Location location) {
        return locationRepo.save(location);
    }

    @CacheEvict(value="locations", allEntries = true)
    public void flushAll() {

    }

}
