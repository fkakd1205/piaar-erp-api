package com.piaar_erp.erp_api.domain.product_option.service;

import java.util.List;
import java.util.stream.Collectors;

import com.piaar_erp.erp_api.domain.product_option.dto.ProductOptionDto;
import com.piaar_erp.erp_api.domain.product_option.entity.ProductOptionEntity;
import com.piaar_erp.erp_api.domain.product_option.proj.ProductOptionProj;
import com.piaar_erp.erp_api.domain.product_option.vo.ProductOptionVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductOptionBusinessService {
    private ProductOptionService productOptionService;

    @Autowired
    public ProductOptionBusinessService(
        ProductOptionService productOptionService
    ) {
        this.productOptionService = productOptionService;
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * ProductOption 데이터를 모두 조회한다.
     *
     * @return List::ProductOptionDto::
     * @see ProductOptionService#findAll
     * @see ProductOptionDto#toDto
     */
    public List<ProductOptionDto> searchList() {
        List<ProductOptionEntity> entities = productOptionService.findAll();
        List<ProductOptionDto> dtos = entities.stream().map(entity -> ProductOptionDto.toDto(entity)).collect(Collectors.toList());
        return dtos;
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * ProductOption 데이터를 모두 조회한다.
     * 해당 ProductOption와 연관관계에 놓여있는 Many To One JOIN(m2oj) 상태를 조회한다.
     *
     * @return List::ProductOptionVo::
     * @see ProductOptionService#searchListM2OJ
     * @see ProductOptionVo#toVo
     */
    public List<ProductOptionVo> searchListM2OJ() {
        List<ProductOptionProj> productOptionProjs = productOptionService.qfindAllM2OJ();
        List<ProductOptionVo> resDtos = productOptionProjs.stream().map(proj -> ProductOptionVo.toVo(proj)).collect(Collectors.toList());
        return resDtos;
    }
}
