package com.gkemayo.voy.agency;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("agencyService")
@Transactional
public class AgencyServiceImpl implements IAgencyService {

    @Autowired
    private IAgencyDao agencyDao;

    @Override
    public Agency saveAgency(Agency agency) {
        return agencyDao.save(agency);
    }

    @Override
    public Agency updateAgency(Agency agency) {
        return agencyDao.save(agency);
    }

    @Override
    public void deleteAgency(Integer agencyId) {
        agencyDao.deleteById(agencyId);
    }

    @Override
    public boolean checkIfIdexists(Integer id) {
        return agencyDao.existsById(id);
    }

    @Override
    public Agency findAgencyById(Integer agencyId) {
        return agencyDao.getOne(agencyId);
    }

    @Override
    public Page<Agency> getPaginatedAgencysList(int begin, int end) {
        Pageable page = PageRequest.of(begin, end);
        return agencyDao.findAll(page);
    }

    @Override
    public Agency findAgencyByName(String name) {
        return agencyDao.findAgencyByNameIgnoreCase(name);
    }

}
