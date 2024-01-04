package com.gm2.pdv.pdv.service;

import com.gm2.pdv.pdv.dto.ProductDTO;
import com.gm2.pdv.pdv.dto.SaleDTO;
import com.gm2.pdv.pdv.model.ItemSale;
import com.gm2.pdv.pdv.model.Product;
import com.gm2.pdv.pdv.model.Sale;
import com.gm2.pdv.pdv.model.User;
import com.gm2.pdv.pdv.repository.ItemSaleRepository;
import com.gm2.pdv.pdv.repository.ProductRepository;
import com.gm2.pdv.pdv.repository.SaleRepository;
import com.gm2.pdv.pdv.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final ItemSaleRepository itemSaleRepository;

    @Transactional
    public long save(SaleDTO sale) {
        User user = userRepository.findById(sale.getUserid()).get();

        Sale newSale = new Sale();
        newSale.setUser(user);
        newSale.setDate(LocalDate.now());
        List<ItemSale> items = getItemSale(sale.getItems());

        newSale = saleRepository.save(newSale);
        setItemSale(items, newSale);

        return newSale.getId();
    }

    private void setItemSale(List<ItemSale> items, Sale newSale) {
        for (ItemSale item: items) {
            item.setSale(newSale);
            itemSaleRepository.save(item);
        }
    }

    private List<ItemSale> getItemSale(List<ProductDTO> products) {
        return products.stream().map(item -> {
            Product product = productRepository.getReferenceById(item.getProductid());

            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);
            itemSale.setQuantity(item.getQuantity());

            return itemSale;
        }).collect(Collectors.toList());
    }
}
