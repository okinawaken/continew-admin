package top.continew.admin.system.service;

import org.springframework.web.multipart.MultipartFile;
import top.continew.admin.system.model.entity.FileDO;
import top.continew.admin.system.model.req.MultipartUploadCreateReq;
import top.continew.admin.system.model.resp.file.MultipartUploadCreateResp;
import top.continew.admin.system.model.resp.file.MultipartUploadPartResp;

/**
 * 分片上传业务接口
 *
 * @author KAI
 * @since 4.1.0
 */
public interface MultipartUploadService {

    MultipartUploadCreateResp initMultipartUpload(MultipartUploadCreateReq multiPartUploadInitReq);

    MultipartUploadPartResp uploadPart(MultipartFile file, String uploadId, Integer partNumber,
        String path);

    FileDO completeMultipartUpload(String uploadId);

    void cancelMultipartUpload(String uploadId);
}
