package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 图片信息（门店图、banner、头像等）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomFileResp {

    /**
     * 文件 key
     */
    private String fileKey;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 可直接访问的 URL
     */
    private String fileUrl;

    /**
     * 跳转类型：0 不跳转 1 跳 H5
     */
    private Integer linkHtmlType;

    /**
     * 跳转地址
     */
    private String linkHtmlUrl;
}
