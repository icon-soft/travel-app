package com.gkemayo.voy.ville;

import java.util.List;

import org.springframework.data.domain.Page;

public interface IVilleService {

    public Ville saveVille(Ville ville);

    public Ville updateVille(Ville ville);

    public void deleteVille(Integer agencyId);

    public boolean checkIfIdexists(Integer id);

    public List<Ville> findVilleByName(String name);

    public Ville findVilleById(Integer agencyId);

    public Page<Ville> getPaginatedVillesList(int begin, int end);

}
