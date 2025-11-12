package uz.pdp.warehouse.service.abstractServices;

public abstract class AbstractService <M,V,R>{
    protected M mapper;
    protected V validator;
    protected R repository;

    protected AbstractService(M mapper, V validator, R repository) {
        this.mapper = mapper;
        this.validator = validator;
        this.repository = repository;
    }

}
