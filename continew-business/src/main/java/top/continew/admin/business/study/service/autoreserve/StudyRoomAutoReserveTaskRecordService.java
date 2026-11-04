/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.continew.admin.business.study.service.autoreserve;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRecordRespDTO;
import top.continew.starter.extension.crud.model.query.PageQuery;

import java.time.LocalDate;
import java.util.List;

/**
 * 自动预约执行记录服务
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomAutoReserveTaskRecordService {

    /**
     * 任务执行历史分页
     *
     * @param taskId 任务 ID
     * @param pageQuery 分页查询条件
     * @return 列表数据
     */
    IPage<StudyRoomAutoReserveTaskRecordRespDTO> pageByTaskId(Long taskId, PageQuery pageQuery);

    /**
     * 删除任务的全部执行历史，供任务硬删时级联使用
     *
     * @param taskId 任务 ID
     */
    void deleteByTaskId(Long taskId);

    /**
     * 写入一条执行记录
     *
     * @param taskRecordRespDTO 任务记录
     */
    void saveRecord(StudyRoomAutoReserveTaskRecordRespDTO taskRecordRespDTO);

    /**
     * 按主键更新一条执行记录
     *
     * @param taskRecordRespDTO 任务记录
     */
    void updateRecord(StudyRoomAutoReserveTaskRecordRespDTO taskRecordRespDTO);

    /**
     * 查询任务在指定日期的全部执行记录
     *
     * @param taskId 任务 ID
     * @param createDate 创建日期
     * @return 列表数据
     */
    List<StudyRoomAutoReserveTaskRecordRespDTO> listByTaskIdAndCreateDate(Long taskId,
        LocalDate createDate);
}
