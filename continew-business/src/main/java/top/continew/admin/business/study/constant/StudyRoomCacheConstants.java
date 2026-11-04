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

package top.continew.admin.business.study.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 缓存的key 常量
 * @author Amadeus
 * @since 4.2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudyRoomCacheConstants {

    /**
     * 自动预约执行锁前缀，完整 key：前缀 + taskId
     */
    public static final String AUTO_RESERVE_EXECUTE_LOCK = "study:auto_reserve:execute:";

    /**
     * 自动签到执行锁前缀，完整 key：前缀 + userId
     */
    public static final String AUTO_SIGN_EXECUTE_LOCK = "study:auto_sign:execute:";

}
