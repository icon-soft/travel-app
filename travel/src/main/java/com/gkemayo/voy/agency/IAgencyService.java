package com.gkemayo.voy.agency;

import java.util.List;

import org.springframework.data.domain.Page;

public interface IAgencyService {

    public Agency saveAgency(Agency agency);

    public Agency updateAgency(Agency agency);

    public void deleteAgency(Integer agencyId);

    public boolean checkIfIdexists(Integer id);

    public Agency findAgencyByName(String name);

    public Agency findAgencyById(Integer agencyId);

    public Page<Agency> getPaginatedAgencysList(int begin, int end);

}
