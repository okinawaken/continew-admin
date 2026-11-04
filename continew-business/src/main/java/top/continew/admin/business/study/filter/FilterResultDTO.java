package top.continew.admin.business.study.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 过滤器执行结果，包含成功标识、数据和失败原因。
 *
 * @author bixiu
 * @param <T> 结果数据类型
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterResultDTO<T> {

    private boolean success;

    private T data;

    private String errorMsg;

    /**
     * 构造成功结果
     *
     * @param <T> 泛型参数
     * @return 成功结果
     */
    public static <T> FilterResultDTO<T> success() {
        return success(null);
    }

    /**
     * 构造带数据的成功结果
     *
     * @param data 数据
     * @param <T> 泛型参数
     * @return 成功结果
     */
    public static <T> FilterResultDTO<T> success(T data) {
        FilterResultDTO<T> result = new FilterResultDTO<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    /**
     * 构造失败结果
     *
     * @param <T> 泛型参数
     * @param errorMsg 失败原因
     * @return 失败结果
     */
    public static <T> FilterResultDTO<T> fail(String errorMsg) {
        FilterResultDTO<T> result = new FilterResultDTO<>();
        result.setSuccess(false);
        result.setErrorMsg(errorMsg);
        return result;
    }
}
