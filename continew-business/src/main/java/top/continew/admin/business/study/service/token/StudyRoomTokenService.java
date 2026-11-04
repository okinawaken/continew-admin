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

package top.continew.admin.business.study.service.token;

/**
 * 系统用户与小程序 token 映射服务
 * <p>
 * 小程序 token 由用户手动录入，按系统用户维度持久化，供自习室接口鉴权时取用。
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomTokenService {

    /**
     * 保存系统用户与小程序 token 的映射，已存在则覆盖
     *
     * @param userId 用户 ID
     * @param token 小程序 token
     */
    void save(Long userId, String token);

    /**
     * 获取系统用户绑定的小程序 token
     *
     * @param userId 用户 ID
     * @return 详情数据
     */
    String get(Long userId);

    /**
     * 删除系统用户绑定的小程序 token
     *
     * @param userId 用户 ID
     */
    void remove(Long userId);
}
