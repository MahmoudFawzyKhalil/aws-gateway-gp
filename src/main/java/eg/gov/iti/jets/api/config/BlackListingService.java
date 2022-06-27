package eg.gov.iti.jets.api.config;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BlackListingService {

    @CachePut(CacheConfig.BLACKLIST_CACHE_NAME)
    public String blackListJwt(String jwt) {

        System.out.println(jwt);
        return jwt;
    }

    @Cacheable(value = CacheConfig.BLACKLIST_CACHE_NAME, unless = "#result == null")
    public String getJwtBlackList(String jwt) {
        return null;
    }

}