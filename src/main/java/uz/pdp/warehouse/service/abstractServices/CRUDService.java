package uz.pdp.warehouse.service.abstractServices;

import uz.pdp.warehouse.exceptions.exception.InsufficientStockException;

import java.util.List;

/**
 * <p>E  -> Element</p>
 * <p>CD -> CreateDto</p>
 * <p>D  -> Dto</p>
 * <p>UD  -> UpdateDto</p>
 */
public interface CRUDService<E,CD,UD,D> {
    D save(CD dto) throws InsufficientStockException;
    D update(UD updateDto, Long id);
    void delete(Long id);
    E findById(Long id);
    List<D> findAll();
    D getDto(E e);
}