package com.piaar_erp.erp_api.domain.erp_order_item.repository;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
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
public class ErpOrderItemJdbcImpl implements ErpOrderItemCustomJdbc {
    private final JdbcTemplate jdbcTemplate;
    private int batchSize = 300;

    @Override
    public void jdbcBulkInsert(List<ErpOrderItemEntity> entities) {
        int batchCount = 0;
        List<ErpOrderItemEntity> subItems = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            subItems.add(entities.get(i));
            if ((i + 1) % batchSize == 0) {
                batchCount = batchInsert(batchSize, batchCount, subItems);
            }
        }
        if (!subItems.isEmpty()) {
            batchCount = batchInsert(batchSize, batchCount, subItems);
        }
    }

    private int batchInsert(int batchSize, int batchCount, List<ErpOrderItemEntity> subItems) {
        String sql = "INSERT INTO erp_order_item" +
                "(cid, id, unique_code, prod_name, option_name, unit, receiver, receiver_contact1, receiver_contact2, destination," +
                " sales_channel, order_number1, order_number2, channel_prod_code, channel_option_code, zip_code, courier, transport_type, delivery_message,waybill_number, " +
                " price, delivery_charge, barcode, prod_code, option_code, release_option_code, management_memo1, management_memo2, management_memo3, management_memo4, " +
                " management_memo5, management_memo6, management_memo7, management_memo8, management_memo9, management_memo10, freight_code, sales_yn, sales_at, release_yn," +
                " release_at, stock_reflect_yn, created_at, created_by)" +
                "VALUES" +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                " ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ErpOrderItemEntity entity = subItems.get(i);
                ps.setObject(1, entity.getCid());
                ps.setObject(2, entity.getId().toString());
                ps.setObject(3, entity.getUniqueCode());
                ps.setString(4, entity.getProdName());
                ps.setString(5, entity.getOptionName());
                ps.setInt(6, entity.getUnit());
                ps.setString(7, entity.getReceiver());
                ps.setString(8, entity.getReceiverContact1());
                ps.setString(9, entity.getReceiverContact2());
                ps.setString(10, entity.getDestination());
                ps.setString(11, entity.getSalesChannel());
                ps.setString(12, entity.getOrderNumber1());
                ps.setString(13, entity.getOrderNumber2());
                ps.setString(14, entity.getChannelProdCode());
                ps.setString(15, entity.getChannelOptionCode());
                ps.setString(16, entity.getZipCode());
                ps.setString(17, entity.getCourier());
                ps.setString(18, entity.getTransportType());
                ps.setString(19, entity.getDeliveryMessage());
                ps.setString(20, entity.getWaybillNumber());
                ps.setInt(21, entity.getPrice());
                ps.setInt(22, entity.getDeliveryCharge());
                ps.setString(23, entity.getBarcode());
                ps.setString(24, entity.getProdCode());
                ps.setString(25, entity.getOptionCode());
                ps.setString(26, entity.getReleaseOptionCode());
                ps.setString(27, entity.getManagementMemo1());
                ps.setString(28, entity.getManagementMemo2());
                ps.setString(29, entity.getManagementMemo3());
                ps.setString(30, entity.getManagementMemo4());
                ps.setString(31, entity.getManagementMemo5());
                ps.setString(32, entity.getManagementMemo6());
                ps.setString(33, entity.getManagementMemo7());
                ps.setString(34, entity.getManagementMemo8());
                ps.setString(35, entity.getManagementMemo9());
                ps.setString(36, entity.getManagementMemo10());
                ps.setString(37, entity.getFreightCode());
                ps.setString(38, entity.getSalesYn());
                ps.setObject(39, entity.getSalesAt());
                ps.setString(40, entity.getReleaseYn());
                ps.setObject(41, entity.getReleaseAt());
                ps.setString(42, entity.getStockReflectYn());
                ps.setObject(43, entity.getCreatedAt());
                ps.setObject(44, entity.getCreatedBy().toString());

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
