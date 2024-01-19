package com.sme.app.service.implementation;

import com.sme.app.criteria.SmeCriteria;
import com.sme.app.entity.Sme;
import com.sme.app.mapper.BaseMapper;
import com.sme.app.mapper.SmeMapper;
import com.sme.app.repo.BaseRepo;
import com.sme.app.repo.SmeRepo;
import com.sme.app.service.CacheManagerService;
import com.sme.app.service.SmeService;
import com.sme.app.vo.SmeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SmeServiceImpl extends SmeManagerImpl<Sme, SmeVo, SmeCriteria> implements SmeService {

    private final SmeRepo smeRepo;
    private final SmeMapper smeMapper;
    private final CacheManagerService cacheManagerService;

    @Override
    public SmeVo findSmeById(Long id) {
        return findById(id);
    }

    @Override
    public SmeVo findSmeByCode(String code) {
        Optional<Sme> sme = smeRepo.findByCode(code);
        if (sme.isPresent()) {
            return smeMapper.entityToVo(sme.get());
        }
        return null;
    }


    @Override
    @Cacheable(value = "findSmeList")
    public List<SmeVo> findAllSmes() {
        List<Sme> all = smeRepo.findAllByActive(true);
        return smeMapper.entityListToVoList(all);
    }

    @Override
    public SmeVo addSme(SmeVo smeVo) {
        smeVo.setActive(true);
        return add(smeVo);
    }

    @Override
    public SmeVo updateSme(Long id, SmeVo smeVo) {
        return update(id, smeVo);
    }

    @Override
    public Long activateSme(Long id) {
        loadById(id);
        smeRepo.activateSme(id);
        clearSmeCache();
        return id;
    }

    @Override
    public Long deleteSme(Long id) {
        Sme sme = loadById(id);
        sme.setActive(false);
        updateSme(id, smeMapper.entityToVo(sme));
        return id;
    }

    @Override
    public BaseMapper<Sme, SmeVo> getMapper() {
        return smeMapper;
    }

    @Override
    public BaseRepo<Sme> getRepo() {
        return smeRepo;
    }

    @Override
    protected void doAfterCreate(SmeVo dto, Sme entity) {
        clearSmeCache();
    }

    @Override
    protected void doAfterUpdate(SmeVo dto, Sme entity) {
        clearSmeCache();
    }

    @Override
    protected void doAfterDelete(Long id) {
        clearSmeCache();
    }

    private void clearSmeCache() {
        cacheManagerService.clearCacheByName("findSmeList");
    }

}
