package com.example.orderplanning.service.impl;

import com.example.orderplanning.dto.OrderRequestDTO;
import com.example.orderplanning.dto.OrderResponseDTO;
import com.example.orderplanning.exception.CustomerNotFoundException;
import com.example.orderplanning.exception.ProductNotFoundException;
import com.example.orderplanning.model.Customer;
import com.example.orderplanning.model.Product;
import com.example.orderplanning.model.Warehouse;
import com.example.orderplanning.repository.CustomerRepository;
import com.example.orderplanning.repository.ProductRepository;
import com.example.orderplanning.repository.WarehouseRepository;
import com.example.orderplanning.service.OrderService;
import com.example.orderplanning.util.DistanceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final WarehouseRepository warehouseRepository;


    /**
     * Method for placing an order for customer.
     *
     * @param dto  dto with customer's id and product's id
     * @return warehouse and distance to customer
     */
    @Override
    public OrderResponseDTO createOrder(final OrderRequestDTO dto)
            throws CustomerNotFoundException, ProductNotFoundException {
        final Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer " + dto.getCustomerId() + " not found"));

        final Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(("Product " + dto.getProductId() + " not found")));

        final Set<String> warehouseIds = product.getWarehouseIds();
        final Iterable<Warehouse> warehouses = warehouseRepository.findAllById(warehouseIds);

        final Warehouse closest = StreamSupport.stream(warehouses.spliterator(), false)
                .min(Comparator.comparing(w -> DistanceUtil.calculateSquaredDistance(customer.getLocation(), w.getLocation())))
                .get();

        final OrderResponseDTO response = new OrderResponseDTO();
        response.setWarehouseId(closest.getId());
        response.setWarehouseName(closest.getName());
        response.setDistance(Math.sqrt(
                DistanceUtil.calculateSquaredDistance(
                        customer.getLocation(),
                        closest.getLocation())));
        return response;
    }
}
