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

package top.continew.admin.business.study.filter.autoreserve;

import org.springframework.core.Ordered;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;

/**
 * 自动预约执行过滤器
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomAutoReserveFilter extends Ordered {

    /**
     * 执行当前过滤环节
     *
     * @param context 执行上下文
     * @return 响应数据
     */
    FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context);
}
