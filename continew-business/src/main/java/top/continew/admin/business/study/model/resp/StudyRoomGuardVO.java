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

/**
 * StudyRoomGuardRespDTO 视图对象
 * <p>
 * 包含 {@code configJsonTwo}（TTLock ekey），供管理后台在浏览器蓝牙开锁时使用。
 * 该字段属于门锁钥匙，前端只能在内存中短暂持有，禁止持久化或输出到日志。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomGuardVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 门禁 ID
     */
    private Long id;

    /**
     * 区域别名
     */
    private String areaAliasName;

    /**
     * 是否可开门：1 可开，2 不可开
     */
    private Integer canOpen;

    /**
     * 不可开门时的提示文案
     */
    private String canNotOpenDesc;

    /**
     * TTLock ekey / lockData，浏览器蓝牙开锁使用
     * <p>
     * 敏感数据：只在管理后台登录态下下发，前端用完即弃，禁止持久化或写日志。
     */
    private String configJsonTwo;

}
