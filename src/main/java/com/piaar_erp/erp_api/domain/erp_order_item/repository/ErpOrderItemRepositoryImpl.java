package com.piaar_erp.erp_api.domain.erp_order_item.repository;

import java.util.List;
import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.entity.QErpOrderItemEntity;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ErpOrderItemRepositoryImpl implements ErpOrderItemRepositoryCustom{
    private final JPAQueryFactory query;

    private final QErpOrderItemEntity qErpOrderItemEntity = QErpOrderItemEntity.erpOrderItemEntity;

    @Autowired
    public ErpOrderItemRepositoryImpl(
        JPAQueryFactory query
    ) {
        this.query = query;
    }

    @Override
    public List<ErpOrderItemEntity> qfindAllByIdList(List<UUID> idList) {
        JPQLQuery customQuery = query.from(qErpOrderItemEntity)
            .select(qErpOrderItemEntity)
            .where(qErpOrderItemEntity.id.in(idList));

        QueryResults<ErpOrderItemEntity> result = customQuery.fetchResults();
        return result.getResults();
    }
}
