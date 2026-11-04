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

/**
 * 自动预约执行器
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomAutoReserveExecutorService {

    /**
     * 提交一次执行，方法立即返回，实际执行在异步线程中进行
     *
     * @param taskId 任务 ID
     * @param triggerType 参数 triggerType
     */
    void execute(Long taskId, Integer triggerType);
}
