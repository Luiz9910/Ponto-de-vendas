package com.gm2.pdv.pdv.service;

import com.gm2.pdv.pdv.dto.ProductDTO;
import com.gm2.pdv.pdv.dto.ProductinfoDTO;
import com.gm2.pdv.pdv.dto.SaleDTO;
import com.gm2.pdv.pdv.dto.SaleinfoDTO;
import com.gm2.pdv.pdv.exceptions.InvalidOperationInvalidException;
import com.gm2.pdv.pdv.exceptions.NoItemException;
import com.gm2.pdv.pdv.exceptions.NotFoundUserException;
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
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final ItemSaleRepository itemSaleRepository;

    public List<SaleinfoDTO> findAll() {
        return saleRepository.findAll().stream().map(this::getSaleInfo).collect(Collectors.toList());
    }

    public SaleinfoDTO getById(long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new NoItemException("Venda não encontrada"));

        return getSaleInfo(sale);
    }

    @Transactional
    public long save(SaleDTO sale) throws Exception {
        User user = userRepository.findById(sale.getUserid())
                .orElseThrow(() -> new NotFoundUserException("Usuário não encontrado"));

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

    private SaleinfoDTO getSaleInfo(Sale sale) {
        return SaleinfoDTO.builder()
                .user(sale.getUser().getName())
                .date(sale.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .products(getProductInFor(sale.getItems()))
                .build();
    }

    public List<ProductinfoDTO> getProductInFor(List<ItemSale> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items.stream().map(
                item -> ProductinfoDTO.builder()
                     .id(item.getId())
                     .description(item.getProduct().getDescription())
                     .quantity((int) item.getQuantity())
                     .build()
        ).collect(Collectors.toList());
    }

    private List<ItemSale> getItemSale(List<ProductDTO> products) throws Exception {
        return products.stream().map(item -> {
            Product product = productRepository.findById(item.getProductid())
                    .orElseThrow(() -> new NoItemException("Item da venda não encontrado"));

            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);
            itemSale.setQuantity(item.getQuantity());

            if (item.getQuantity() == 0) {
                throw new NoItemException("Valores considerados para venda é a partir de 1");
            } else if (product.getQuantity() == 0) {
                throw new NoItemException("Produto sem estoque");
            } else if (product.getQuantity() < item.getQuantity()) {
                throw new InvalidOperationInvalidException("A quantidade de itens da venda (" + item.getQuantity() + ") é maior que a quantidade dísponivel em estoque (" + product.getQuantity() + ")");
            }

            int total = product.getQuantity() - item.getQuantity();
            product.setQuantity(total);
            productRepository.save(product);

            return itemSale;
        }).collect(Collectors.toList());
    }
}
