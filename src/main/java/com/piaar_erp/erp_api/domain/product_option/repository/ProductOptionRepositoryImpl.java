package com.piaar_erp.erp_api.domain.product_option.repository;

import java.util.List;

import com.piaar_erp.erp_api.domain.product.entity.QProductEntity;
import com.piaar_erp.erp_api.domain.product_category.entity.QProductCategoryEntity;
import com.piaar_erp.erp_api.domain.product_option.entity.QProductOptionEntity;
import com.piaar_erp.erp_api.domain.product_option.proj.ProductOptionProj;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductOptionRepositoryImpl implements ProductOptionRepositoryCustom {
    private final JPAQueryFactory query;

    private final QProductEntity qProductEntity = QProductEntity.productEntity;
    private final QProductOptionEntity qProductOptionEntity = QProductOptionEntity.productOptionEntity;
    private final QProductCategoryEntity qProductCategoryEntity = QProductCategoryEntity.productCategoryEntity;

    @Autowired
    public ProductOptionRepositoryImpl(
        JPAQueryFactory query
    ) {
        this.query = query;
    }

    @Override
    public List<ProductOptionProj> qfindAllM2OJ() {
        JPQLQuery customQuery = query.from(qProductOptionEntity)
            .select(Projections.fields(ProductOptionProj.class,
                    qProductOptionEntity.as("productOption"),
                    qProductEntity.as("product"),
                    qProductCategoryEntity.as("productCategory")
                    ))
            .leftJoin(qProductEntity).on(qProductEntity.cid.eq(qProductOptionEntity.productCid))
            .leftJoin(qProductCategoryEntity).on(qProductCategoryEntity.cid.eq(qProductEntity.productCategoryCid));

        QueryResults<ProductOptionProj> result = customQuery.fetchResults();
        return result.getResults();
    }
}
