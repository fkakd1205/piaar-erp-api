package com.piaar_erp.erp_api.domain.erp_download_excel_header.service;

import com.piaar_erp.erp_api.domain.erp_download_excel_header.entity.ErpDownloadExcelHeaderEntity;
import com.piaar_erp.erp_api.domain.erp_download_excel_header.repository.ErpDownloadExcelHeaderRepository;
import com.piaar_erp.erp_api.domain.exception.CustomNotFoundDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ErpDownloadExcelHeaderService {
    private final ErpDownloadExcelHeaderRepository erpDownloadExcelHeader;

    /**
     * <b>DB Insert Or Update Related Method</b>
     * <p>
     * erp download excel header를 저장 or 수정한다.
     *
     * @param entity : ErpDownloadExcelHeaderEntity
     * @see ErpDownloadExcelHeaderRepository#save
     */
    public void saveAndModify(ErpDownloadExcelHeaderEntity entity) {
        erpDownloadExcelHeader.save(entity);
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * erp download excel header을 조회한다.
     *
     * @return List::ErpDownloadExcelHeaderEntity::
     * @see ErpDownloadExcelHeaderRepository#findAll
     */
    public List<ErpDownloadExcelHeaderEntity> searchAll() {
        return erpDownloadExcelHeader.findAll();
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * id에 대응하는 erp download excel header을 조회한다.
     *
     * @param id : UUID
     * @return ErpDownloadExcelHeaderEntity
     * @see ErpDownloadExcelHeaderRepository#findById
     */
    public ErpDownloadExcelHeaderEntity searchOne(UUID id) {
        Optional<ErpDownloadExcelHeaderEntity> entityOpt = erpDownloadExcelHeader.findById(id);

        if(entityOpt.isPresent()) {
            return entityOpt.get();
        }else {
            throw new CustomNotFoundDataException("수정하려는 데이터를 찾을 수 없습니다.");
        }
    }

    /**
     * <b>DB Delete Related Method</b>
     * <p>
     * id에 대응하는 erp download excel header을 삭제한다.
     *
     * @param id : UUID
     * @see ErpDownloadExcelHeaderRepository#findById
     * @see ErpDownloadExcelHeaderRepository#delete
     */
    public void deleteOne(UUID id) {
        erpDownloadExcelHeader.findById(id).ifPresent(header -> erpDownloadExcelHeader.delete(header));
    }
}
