package com.piaar_erp.erp_api.service;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.repository.ErpOrderItemRepository;
import com.piaar_erp.erp_api.utils.CustomDateUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@WebAppConfiguration
@Transactional
public class ErpOrderItemServiceTest {

    @Autowired
    private ErpOrderItemRepository erpOrderItemRepository;

//    @Test
//    public void 엑셀데이터_생성() throws DataIntegrityViolationException {
//        UUID userId = UUID.fromString("#USER_ID#");
//        UUID itemId = UUID.randomUUID();
//
//        ErpOrderItemEntity erpOrderItemEntity = ErpOrderItemEntity.builder()
//                .cid(1)
//                .id(itemId)
//                .receiver("이름이 아주 긴 수취인의 이름입니다. 이름이 길어서 데이터베이스에 저장할 수 없습니다.")
//                .salesYn("n")
//                .releaseYn("n")
//                .stockReflectYn("n")
//                .createdAt(CustomDateUtils.getCurrentDateTime())
//                .createdBy(userId)
//                .build();
//
//        assertThrows(DataIntegrityViolationException.class, () -> {
//            ErpOrderItemEntity savedEntity = erpOrderItemRepository.save(erpOrderItemEntity);
//
//        });
//
//        System.out.println("##### ErpOrderItemServiceTest.엑셀데이터_생성 성공 #####");
//    }
}
