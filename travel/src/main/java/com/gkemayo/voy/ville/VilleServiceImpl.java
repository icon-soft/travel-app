package com.gkemayo.voy.ville;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("villeService")
@Transactional
public class VilleServiceImpl implements IVilleService {

    @Autowired
    private IVilleDao villeDao;

    @Override
    public Ville saveVille(Ville agency) {
        return villeDao.save(agency);
    }

    @Override
    public Ville updateVille(Ville agency) {
        return villeDao.save(agency);
    }

    @Override
    public void deleteVille(Integer agencyId) {
        villeDao.deleteById(agencyId);
    }

    @Override
    public boolean checkIfIdexists(Integer id) {
        return villeDao.existsById(id);
    }

    @Override
    public Ville findVilleById(Integer agencyId) {
        return villeDao.getOne(agencyId);
    }

    @Override
    public Page<Ville> getPaginatedVillesList(int begin, int end) {
        Pageable page = PageRequest.of(begin, end);
        return villeDao.findAll(page);
    }

    @Override
    public List<Ville> findVilleByName(String name) {
        return villeDao.findVilleByNameIgnoreCase(name);
    }

}
