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

package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomUserLeaveEnterRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserLeaveEnterVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户套餐ID
     */
    private Long userPackageId;

    /**
     * 是否可请假：0 不可
     */
    private Integer canLeaveState;

    /**
     * 不可请假原因
     */
    private String canNotLeaveMsg;

    /**
     * 剩余可请假天数
     */
    private Integer leftLeaveDays;

    /**
     * 今日使用状态
     */
    private Integer todayStudyState;

    /**
     * 请假记录
     */
    private List<StudyRoomUserLeaveVO> leaveList;
}
