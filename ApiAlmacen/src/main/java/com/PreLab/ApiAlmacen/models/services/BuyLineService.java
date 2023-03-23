package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Buy;
import com.PreLab.ApiAlmacen.entities.BuyLine;
import com.PreLab.ApiAlmacen.models.dao.IAnnouncementDao;
import com.PreLab.ApiAlmacen.models.dao.IBuyLineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyLineService implements IBuyLineService{
    @Autowired
    private IBuyLineDao iBuyLineDao;
    @Override
    public BuyLine findById(Long id){return iBuyLineDao.findById(id).orElse(null);};
    @Override
    public void deleteById(Long id){iBuyLineDao.deleteById(id);};
}
