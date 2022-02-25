package com.piaar_erp.erp_api.domain.product.repository;

import com.piaar_erp.erp_api.domain.product.entity.ProductEntity;
import com.piaar_erp.erp_api.domain.product.entity.QProductEntity;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom{
    private final JPAQueryFactory query;

    private final QProductEntity qProductEntity = QProductEntity.productEntity;

    @Autowired
    public ProductRepositoryImpl(
            JPAQueryFactory query
    ) {
        this.query = query;
    }

    @Override
    public List<ProductEntity> qSelectAll() {
        
        JPQLQuery customQuery = query.from(qProductEntity)
                .select(qProductEntity)
                ;

        QueryResults<ProductEntity> result = customQuery.fetchResults();
        return result.getResults();
    }

    // @Override
    // public List<ProductEntity> qSelectById(Map<String, Object> params) {
    //     UUID id = params.get("id");
    //     // TODO Auto-generated method stub
    //     JPQLQuery customQuery = query.from(qProductEntity)
    //             .select(qProductEntity)
    //             .where(eqId(params))
    //             ;
                

    //     QueryResults<ProductEntity> result = customQuery.fetchResults();
    //     return result.getResults();
    // }


    // private BooleanExpression eqId(Map<String, Object> params){
    //     Object idObj = params.get("id");
    //     UUID id = null;

    //     if(idObj == null){
    //         return null;
    //     }

    //     try{
    //         id = UUID.fromString(idObj.toString());
    //     } catch(IllegalArgumentException e){
    //         // throw new NotMatched()
    //     }
        
    //     return qProductEntity.id.eq(id);
    // }
}
