package com.piaar_erp.erp_api.domain.product_option.repository;

import java.util.List;

import javax.persistence.Tuple;

import com.piaar_erp.erp_api.domain.product_option.entity.ProductOptionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOptionEntity, Integer>, ProductOptionRepositoryCustom{

    /**
     * ProductOption 데이터의 code들에 대응하는 옵션데이터를 조회한다.
     * 
     * @param codes : List::String::
     * @return List::ProductOptionEntity::
     */
    @Query(
        "SELECT po FROM ProductOptionEntity po\n" +
        "WHERE po.code IN :codes"
    )
    List<ProductOptionEntity> findAllByCode(List<String> codes);

    /**
     * 다중 ProductOption cid에 대응하는 옵션데이터의 재고수량을 계산한다.
     * option cid값에 대응하는 입고데이터의 모든 수량합을 조회한다.
     * option cid값에 대응하는 출고데이터의 모든 수량합을 조회한다.
     * 
     * @param optionCids : List::Integer::
     * @return po.cid, sum(receive_unit), sum(release_unit)
     */
    @Query(value="SELECT po.cid AS cid, \n" +
                "(SELECT SUM(prl.release_unit) FROM product_release prl WHERE po.cid=prl.product_option_cid) AS releasedSum, \n" + 
                "(SELECT SUM(prc.receive_unit) FROM product_receive prc WHERE po.cid=prc.product_option_cid) AS receivedSum \n" +
                "FROM product_option po WHERE po.cid IN :optionCids", nativeQuery = true)
    List<Tuple> sumStockUnitByOption(List<Integer> optionCids);
}
