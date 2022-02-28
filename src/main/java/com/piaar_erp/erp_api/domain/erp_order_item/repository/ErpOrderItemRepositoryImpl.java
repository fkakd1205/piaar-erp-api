package com.piaar_erp.erp_api.domain.erp_order_item.repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.entity.QErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.proj.ErpOrderItemProj;
import com.piaar_erp.erp_api.domain.product.entity.QProductEntity;
import com.piaar_erp.erp_api.domain.product_category.entity.QProductCategoryEntity;
import com.piaar_erp.erp_api.domain.product_option.entity.QProductOptionEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ErpOrderItemRepositoryImpl implements ErpOrderItemRepositoryCustom{
    private final JPAQueryFactory query;

    private final QErpOrderItemEntity qErpOrderItemEntity = QErpOrderItemEntity.erpOrderItemEntity;
    private final QProductEntity qProductEntity = QProductEntity.productEntity;
    private final QProductOptionEntity qProductOptionEntity = QProductOptionEntity.productOptionEntity;
    private final QProductCategoryEntity qProductCategoryEntity = QProductCategoryEntity.productCategoryEntity;

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

    @Override
    public List<ErpOrderItemProj> qfindAllMappingDataByPiaarOptionCode(Map<String, Object> params) {
        String salesYn = params.get("salesYn") == null ? null : params.get("salesYn").toString();
        String releaseYn = params.get("releaseYn") == null ? null : params.get("releaseYn").toString();
        
        JPQLQuery customQuery = query.from(qErpOrderItemEntity)
                .select(Projections.fields(ErpOrderItemProj.class,
                        qErpOrderItemEntity.as("erpOrderItem"),
                        qProductEntity.defaultName.as("prodDefaultName"),
                        qProductEntity.managementName.as("prodManagementName"),
                        qProductOptionEntity.defaultName.as("optionDefaultName"),
                        qProductOptionEntity.managementName.as("optionManagementName"),
                        qProductCategoryEntity.name.as("categoryName")
                       ))
                .where(eqSalesYn(salesYn), eqReleaseYn(releaseYn))
                .leftJoin(qProductOptionEntity).on(qErpOrderItemEntity.optionCode.eq(qProductOptionEntity.code))
                .leftJoin(qProductEntity).on(qProductOptionEntity.productCid.eq(qProductEntity.cid))
                .leftJoin(qProductCategoryEntity).on(qProductEntity.productCategoryCid.eq(qProductCategoryEntity.cid));

        QueryResults<ErpOrderItemProj> result = customQuery.fetchResults();
        return result.getResults();
    }

    private BooleanExpression eqSalesYn(String salesYn) {
        if(salesYn == null){
            return null;
        } else {
            return qErpOrderItemEntity.salesYn.eq(salesYn);
        }
    }

    private BooleanExpression eqReleaseYn(String releaseYn) {
        if(releaseYn == null){
            return null;
        } else {
            return qErpOrderItemEntity.releaseYn.eq(releaseYn);
        }
    }
}
