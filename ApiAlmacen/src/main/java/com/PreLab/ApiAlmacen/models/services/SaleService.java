package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Sale;
import com.PreLab.ApiAlmacen.entities.SellLine;
import com.PreLab.ApiAlmacen.exceptions.NotEnoughStockException;
import com.PreLab.ApiAlmacen.models.dao.ISaleDao;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SaleService implements ISaleService{

    @Autowired
    private ISaleDao saleDao;

    @Override
    public List<Sale> findAll() {
        return (List<Sale>) saleDao.findAll();
    }

    @Override
    public Sale findById(Long id) {
        return saleDao.findById(id).orElse(null);
    }

    @Override
    public Sale save(Sale sale) throws NotEnoughStockException{

        Integer total = 0;

        // Update the stock of each product included in the sale and obtains the total amount.
        for(SellLine sl: sale.getSellLines()){

            if(sl.getProduct().getStock() < sl.getQuantity()){
                throw new NotEnoughStockException("There is not enough stock to sell " + sl.getQuantity() + " of " + sl.getProduct().getName());
            }

            sl.getProduct().setStock(sl.getProduct().getStock() - sl.getQuantity());
            sl.setPrice(sl.getQuantity() * sl.getProduct().getSell_price()); // Calculate the sale line total
            total = total + sl.getPrice(); // Add the sale line total to the buy total
        }

        sale.setTotal(total);

        return saleDao.save(sale);
    }

    @Override
    public void deleteById(Long id) {
        saleDao.deleteById(id);

    }
}
