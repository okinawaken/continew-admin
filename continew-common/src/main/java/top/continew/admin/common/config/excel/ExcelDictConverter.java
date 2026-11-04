package top.continew.admin.common.config.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.extra.spring.SpringUtil;
import cn.idev.excel.converters.Converter;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.ReadCellData;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;
import top.continew.admin.common.api.system.DictItemApi;
import top.continew.starter.core.constant.StringConstants;
import top.continew.starter.extension.crud.model.resp.LabelValueResp;

import java.util.List;
import java.util.Objects;

/**
 * Easy Excel 字典转换器
 *
 * @author bixiu
 * @since 3.6.0
 */
public class ExcelDictConverter implements Converter<Object> {

    @Override
    public Object convertToJavaData(ReadCellData<?> cellData,
        ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        // 获取字典项数据
        List<LabelValueResp> dictItemList = this.getDictCode(contentProperty);
        // 单元格中填写的是字典标签，转换为字典值
        String value = dictItemList.stream()
            .filter(item -> Objects.equals(cellData.getStringValue(), item.getLabel()))
            .findFirst()
            .map(LabelValueResp::getValue)
            .map(String::valueOf)
            .orElse(null);
        // 转换字典值为对应类型
        return Convert.convert(contentProperty.getField().getType(), value);
    }

    @Override
    public WriteCellData<String> convertToExcelData(Object data,
        ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        if (data == null) {
            return new WriteCellData<>(StringConstants.EMPTY);
        }
        // 获取字典项数据
        List<LabelValueResp> dictItemList = this.getDictCode(contentProperty);
        if (CollUtil.isEmpty(dictItemList)) {
            return new WriteCellData<>(StringConstants.EMPTY);
        }
        // 转换字典值为字典标签
        return new WriteCellData<>(dictItemList.stream()
            .filter(item -> Objects.equals(data, item.getValue()))
            .findFirst()
            .map(LabelValueResp::getLabel)
            .orElse(StringConstants.EMPTY));
    }

    /**
     * 获取字典项数据
     *
     * @param contentProperty Excel 内容属性
     * @return 字典项数据
     */
    private List<LabelValueResp> getDictCode(ExcelContentProperty contentProperty) {
        DictExcelProperty dictExcelProperty =
            contentProperty.getField().getAnnotation(DictExcelProperty.class);
        if (dictExcelProperty == null) {
            throw new IllegalArgumentException("Excel 字典转换器异常：请为字段添加 @DictExcelProperty 注解");
        }
        DictItemApi dictItemApi = SpringUtil.getBean(DictItemApi.class);
        return dictItemApi.listByDictCode(dictExcelProperty.value());
    }
}
