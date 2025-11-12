package uz.pdp.warehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.warehouse.exceptions.exception.*;
import uz.pdp.warehouse.model.dto.order.*;
import uz.pdp.warehouse.model.dto.orderItem.OrderItemCreateDto;
import uz.pdp.warehouse.model.entity.*;
import uz.pdp.warehouse.mapper.OrderMapper;
import uz.pdp.warehouse.repository.*;
import uz.pdp.warehouse.service.abstractServices.*;
import uz.pdp.warehouse.validator.OrderValidator;

import java.math.BigDecimal; // Import the correct type
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService
        extends
        AbstractService<
                OrderMapper,
                OrderValidator,
                OrderRepository
                >
        implements
        CRUDService<
                Order,
                OrderCreateDto,
                OrderUpdateDto,
                OrderDto
                > {

    private final CustomerRepository customerRepo;
    private final ProductRepository productRepo;
    private final InventoryRepository inventoryRepo;
    private final OrderItemRepository orderItemRepo;

    protected OrderService(
            OrderMapper mapper,
            OrderValidator validator,
            OrderRepository repository,
            CustomerRepository customerRepo,
            ProductRepository productRepo,
            InventoryRepository inventoryRepo,
            OrderItemRepository orderItemRepo) {
        super(mapper, validator, repository);
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
        this.inventoryRepo = inventoryRepo;
        this.orderItemRepo = orderItemRepo;
    }

    @Override
    public OrderDto save(OrderCreateDto dto) throws InsufficientStockException {
        validator.validateOnCreate(dto);

        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(dto.getCustomerId()));

        Order order = mapper.fromCreateDto(dto);
        order.setCustomer(customer);

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemCreateDto itemDto : dto.getOrderItems()) {
            Product product = productRepo.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(itemDto.getProductId()));

            Inventory inventory = inventoryRepo.findByProductId(itemDto.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(itemDto.getProductId()));

            if (inventory.getQuantityInStock() >= itemDto.getQuantity()) {
                throw new InsufficientStockException(product.getName(), itemDto.getQuantity());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setPrice(itemDto.getPrice());

            BigDecimal itemTotal = itemDto.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);

            order.getOrderItems().add(orderItem);
        }

        order.setTotalPrice(totalAmount);

        repository.save(order);

        return mapper.toDto(order);
    }

    @Override
    public OrderDto update(OrderUpdateDto dto, Long id) {
        Order order = findById(id);
        mapper.fromUpdateDto(dto, order);

        if (dto.getCustomerId() != null && !dto.getCustomerId().equals(order.getCustomer().getId())) {
            Customer customer = customerRepo.findById(dto.getCustomerId())
                    .orElseThrow(() -> new CustomerNotFoundException(dto.getCustomerId()));
            order.setCustomer(customer);
        }

        repository.save(order);
        return mapper.toDto(order);
    }

    @Override
    public void delete(Long id) {
        Order order = findById(id);
        order.softDelete();
        repository.save(order);
    }

    @Override
    public Order findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public List<OrderDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public OrderDto getDto(Order order) {
        return mapper.toDto(order);
    }
}