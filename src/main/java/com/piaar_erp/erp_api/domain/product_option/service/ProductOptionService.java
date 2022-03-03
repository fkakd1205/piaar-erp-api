package com.piaar_erp.erp_api.domain.product_option.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import com.piaar_erp.erp_api.domain.product_option.dto.ProductOptionDto;
import com.piaar_erp.erp_api.domain.product_option.dto.ReceiveReleaseSumOnlyDto;
import com.piaar_erp.erp_api.domain.product_option.entity.ProductOptionEntity;
import com.piaar_erp.erp_api.domain.product_option.proj.ProductOptionProj;
import com.piaar_erp.erp_api.domain.product_option.repository.ProductOptionRepository;

@Service
public class ProductOptionService {
    private ProductOptionRepository productOptionRepository;

    @Autowired
    public ProductOptionService(
        com.piaar_erp.erp_api.domain.product_option.repository.ProductOptionRepository productOptionRepository
    ) {
        this.productOptionRepository = productOptionRepository;
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * ProductOption 데이터를 모두 조회한다.
     *
     * @return List::ProductOptionEntity::
     * @see ProductOptionRepository#findAll
     */
    public List<ProductOptionEntity> findAll() {
        return productOptionRepository.findAll();
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * ProductOption 데이터를 모두 조회한다.
     * 해당 ProductOption와 연관관계에 놓여있는 Many To One JOIN(m2oj) 상태를 조회한다.
     *
     * @return List::ProductOptionProj::
     * @see ProductOptionRepository#qfindAllM2OJ
     */
    public List<ProductOptionProj> qfindAllM2OJ() {
        return productOptionRepository.qfindAllM2OJ();
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * Option Code들에 대응되는 ProductOption 데이터를 모두 조회한다.
     * ProductOption의 입고수량과 출고수량을 통해 재고수량을 계산한다.
     *
     * @return List::ProductOptionGetDto::
     * @param optionCodes : List::String::
     * @see ProductOptionRepository#findAllByCode
     * @see ProductOptionService#searchStockUnit
     */
    public List<ProductOptionDto> searchListByProductListOptionCode(List<String> optionCodes) {
        List<ProductOptionEntity> productOptionEntities = productOptionRepository.findAllByCode(optionCodes);
        List<ProductOptionDto> productOptionGetDtos = this.searchStockUnit(productOptionEntities);
        return productOptionGetDtos;
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * ProductOption의 재고수량을 계산한다.
     * 입고수량과 출고수량을 이용해 ProductOption의 stockUnit을 세팅한다.
     * (receivedSumUnit, releasedSumUnit, stockSunUnit을 반환하기 위해 dto사용)
     *
     * @return List::ProductOptionGetDto::
     * @param entities : List::ProductOptionEntity::
     * @see ProductOptionDto#toDto
     * @see ProductOptionBusinessService#sumStockUnit
     */
    public List<ProductOptionDto> searchStockUnit(List<ProductOptionEntity> entities) {
        List<Integer> productOptionCids = entities.stream().map(r -> r.getCid()).collect(Collectors.toList());
        List<ProductOptionDto> productOptionGetDtos = entities.stream().map(entity -> ProductOptionDto.toDto(entity)).collect(Collectors.toList());

        List<ReceiveReleaseSumOnlyDto> stockUnitByOption = this.sumStockUnit(productOptionCids);

        for(ProductOptionDto dto : productOptionGetDtos) {
            stockUnitByOption.stream().forEach(r -> {
                if(dto.getCid().equals(r.getOptionCid())){
                    dto.setReceivedSumUnit(r.getReceivedSum()).setReleasedSumUnit(r.getReleasedSum())
                        .setStockSumUnit(r.getReceivedSum() - r.getReleasedSum());
                }
            });
        }

        return productOptionGetDtos;
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * ProductOption cid 값과 상응되는 입고데이터와 출고데이터의 합을 모두 조회한다.
     * 입고데이터와 출고데이터를 이용해 재고수량을 계산한다.
     * 
     * @param cids : List::Integer::
     * @see ProductOptionRepository#sumStockUnitByOption
     */
    public List<ReceiveReleaseSumOnlyDto> sumStockUnit(List<Integer> cids) {
        List<Tuple> stockUnitTuple = productOptionRepository.sumStockUnitByOption(cids);
        List<ReceiveReleaseSumOnlyDto> stockUnitByOption = stockUnitTuple.stream().map(r -> {
            ReceiveReleaseSumOnlyDto dto = ReceiveReleaseSumOnlyDto.builder()
                    .optionCid(r.get("cid", Integer.class))
                    .receivedSum(r.get("receivedSum", BigDecimal.class) != null ? r.get("receivedSum", BigDecimal.class).intValue() : 0)
                    .releasedSum(r.get("releasedSum", BigDecimal.class) != null ? r.get("releasedSum", BigDecimal.class).intValue() : 0)
                    .build();

            return dto;
        }).collect(Collectors.toList());

        return stockUnitByOption;
    }
}
