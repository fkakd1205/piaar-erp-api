package com.piaar_erp.erp_api.domain.erp_order_item.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ErpOrderItemRepository extends JpaRepository<ErpOrderItemEntity, Integer>, ErpOrderItemRepositoryCustom {
    Optional<ErpOrderItemEntity> findById(UUID id);

    @Query(
        "SELECT item FROM ErpOrderItemEntity item\n" + 
        "WHERE (item.orderNumber1 IN :orderNumber1 AND item.receiver IN :receiver AND item.prodName IN :prodName AND item.optionName IN :optionName AND item.unit IN :unit)"
    )
    List<ErpOrderItemEntity> findDuplicationItems(List<String> orderNumber1, List<String> receiver, List<String> prodName, List<String> optionName, List<Integer> unit);

    @Transactional
    @Modifying
    @Query(
        "DELETE FROM ErpOrderItemEntity item\n" +
        "WHERE item.id IN :ids"
    )
    void deleteAllById(List<UUID> ids);
}
