package com.piaar_erp.erp_api.domain.erp_order_item.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.entity.QErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.proj.ErpOrderItemProj;
import com.piaar_erp.erp_api.domain.exception.CustomInvalidDataException;
import com.piaar_erp.erp_api.domain.product.entity.QProductEntity;
import com.piaar_erp.erp_api.domain.product_category.entity.QProductCategoryEntity;
import com.piaar_erp.erp_api.domain.product_option.entity.QProductOptionEntity;
import com.piaar_erp.erp_api.utils.CustomFieldUtils;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class ErpOrderItemRepositoryImpl implements ErpOrderItemRepositoryCustom {
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
    public List<ErpOrderItemProj> qfindAllM2OJ(Map<String, Object> params) {
        JPQLQuery customQuery = query.from(qErpOrderItemEntity)
                .select(Projections.fields(ErpOrderItemProj.class,
                        qErpOrderItemEntity.as("erpOrderItem"),
                        qProductEntity.as("product"),
                        qProductOptionEntity.as("productOption"),
                        qProductCategoryEntity.as("productCategory")
                       ))
                .where(eqSalesYn(params), eqReleaseYn(params))
                .where(lkSearchCondition(params))
                .where(withinDateRange(params))
                .leftJoin(qProductOptionEntity).on(qErpOrderItemEntity.optionCode.eq(qProductOptionEntity.code))
                .leftJoin(qProductEntity).on(qProductOptionEntity.productCid.eq(qProductEntity.cid))
                .leftJoin(qProductCategoryEntity).on(qProductEntity.productCategoryCid.eq(qProductCategoryEntity.cid));
    
        QueryResults<ErpOrderItemProj> result = customQuery.fetchResults();
       
        return result.getResults();
    }

    @Override
    public Page<ErpOrderItemProj> qfindAllM2OJByPage(Map<String, Object> params, Pageable pageable) {
        JPQLQuery customQuery = query.from(qErpOrderItemEntity)
                .select(Projections.fields(ErpOrderItemProj.class,
                        qErpOrderItemEntity.as("erpOrderItem"),
                        qProductEntity.as("product"),
                        qProductOptionEntity.as("productOption"),
                        qProductCategoryEntity.as("productCategory")
                       ))
                .where(eqSalesYn(params), eqReleaseYn(params))
                .where(lkSearchCondition(params))
                .where(withinDateRange(params))
                .leftJoin(qProductOptionEntity).on(qErpOrderItemEntity.optionCode.eq(qProductOptionEntity.code))
                .leftJoin(qProductEntity).on(qProductOptionEntity.productCid.eq(qProductEntity.cid))
                .leftJoin(qProductCategoryEntity).on(qProductEntity.productCategoryCid.eq(qProductCategoryEntity.cid))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
                
        for(Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(qErpOrderItemEntity.getType(), qErpOrderItemEntity.getMetadata());
            customQuery.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
         }

        customQuery.orderBy(qErpOrderItemEntity.createdAt.desc());
    
        QueryResults<ErpOrderItemProj> result = customQuery.fetchResults();
        return new PageImpl<ErpOrderItemProj>(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression eqSalesYn(Map<String, Object> params) {
        String salesYn = params.get("salesYn") == null ? null : params.get("salesYn").toString();

        if(salesYn == null){
            return null;
        } else {
            return qErpOrderItemEntity.salesYn.eq(salesYn);
        }
    }

    private BooleanExpression eqReleaseYn(Map<String, Object> params) {
        String releaseYn = params.get("releaseYn") == null ? null : params.get("releaseYn").toString();

        if(releaseYn == null){
            return null;
        } else {
            return qErpOrderItemEntity.releaseYn.eq(releaseYn);
        }
    }

    private BooleanExpression withinDateRange(Map<String, Object> params) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        String periodType = params.get("periodType") == null ? null : params.get("periodType").toString();

        if (params.get("startDate") == null || params.get("endDate") == null || periodType == null) {
            return null;
        }

        startDate = LocalDateTime.parse(params.get("startDate").toString(), formatter);
        endDate = LocalDateTime.parse(params.get("endDate").toString(), formatter);

        if(startDate.isAfter(endDate)) {
            throw new CustomInvalidDataException("조회기간을 정확히 선택해 주세요.");
        }

        if(periodType.equals("registration")) {
            return qErpOrderItemEntity.createdAt.between(startDate, endDate);
        } else if (periodType.equals("sales")) {
            return qErpOrderItemEntity.salesAt.between(startDate, endDate);
        } else if (periodType.equals("release")) {
            return qErpOrderItemEntity.releaseAt.between(startDate, endDate);
        }
        return null;
    }

    private BooleanExpression lkSearchCondition(Map<String, Object> params) {
        String columnName = params.get("searchColumnName") == null ? null : params.get("searchColumnName").toString();
        String searchQuery = params.get("searchQuery") == null ? null : params.get("searchQuery").toString();
        if(columnName == null || searchQuery == null){
            return null;
        }

        StringPath columnNameStringPath = CustomFieldUtils.getFieldValue(qErpOrderItemEntity, columnName);
        return columnNameStringPath.contains(searchQuery);
    }
}
