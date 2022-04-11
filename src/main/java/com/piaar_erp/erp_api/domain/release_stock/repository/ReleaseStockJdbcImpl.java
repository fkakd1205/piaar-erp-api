package com.piaar_erp.erp_api.domain.release_stock.repository;

import com.piaar_erp.erp_api.domain.release_stock.entity.ReleaseStockEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReleaseStockJdbcImpl implements ReleaseStockCustomJdbc{
    private final JdbcTemplate jdbcTemplate;
    private int batchSize = 300;

    @Override
    public void jdbcBulkInsert(List<ReleaseStockEntity> entities){
        int batchCount = 0;
        List<ReleaseStockEntity> subItems = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            subItems.add(entities.get(i));
            if ((i + 1) % batchSize == 0) {
                batchCount = batchInsert(batchSize, batchCount, subItems);
            }
        }
        if (!subItems.isEmpty()) {
            batchCount = batchInsert(batchSize, batchCount, subItems);
        }
//        log.info("batchCount: " + batchCount);
    }

    private int batchInsert(int batchSize, int batchCount, List<ReleaseStockEntity> subItems){
        String sql = "INSERT INTO release_stock" +
                "(cid, id, release_unit, memo, created_at, created_by, product_option_cid, product_option_id, erp_order_item_id)" +
                "VALUES" +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ReleaseStockEntity entity = subItems.get(i);
                ps.setObject(1, entity.getCid());
                ps.setObject(2, entity.getId().toString());
                ps.setInt(3, entity.getReleaseUnit());
                ps.setString(4, entity.getMemo());
                ps.setObject(5, entity.getCreatedAt());
                ps.setObject(6, entity.getCreatedBy().toString());
                ps.setInt(7, entity.getProductOptionCid());
                ps.setObject(8, entity.getProductOptionId().toString());
                ps.setObject(9, entity.getErpOrderItemId().toString());

            }

            @Override
            public int getBatchSize() {
                return subItems.size();
            }
        });

        subItems.clear();
        batchCount++;
        return batchCount;
    }

}
