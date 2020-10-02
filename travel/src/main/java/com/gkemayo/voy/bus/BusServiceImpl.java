package com.gkemayo.voy.bus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("busService")
@Transactional
public class BusServiceImpl implements IBusService {

    @Autowired
    private IBusDao busDao;

    @Override
    public Bus saveBus(Bus bus) {
        return busDao.save(bus);
    }

    @Override
    public Bus updateBus(Bus bus) {
        return busDao.save(bus);
    }

    @Override
    public void deleteBus(Integer busId) {
        busDao.deleteById(busId);
    }

    @Override
    public boolean checkIfIdexists(Integer id) {
        return busDao.existsById(id);
    }

    @Override
    public Bus findBusById(Integer busId) {
        return busDao.getOne(busId);
    }

    @Override
    public Page<Bus> getPaginatedBussList(int begin, int end) {
        Pageable page = PageRequest.of(begin, end);
        return busDao.findAll(page);
    }

    @Override
    public Bus findBusByImmatricule(String immatricule) {
        return busDao.findBusByImmatriculeIgnoreCase(immatricule);
    }

}
