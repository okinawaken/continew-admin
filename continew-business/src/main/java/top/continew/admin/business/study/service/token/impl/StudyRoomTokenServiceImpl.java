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

package top.continew.admin.business.study.service.token.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.business.study.client.http.StudyRoomUserClient;
import top.continew.admin.business.study.mapper.StudyRoomUserTokenMapper;
import top.continew.admin.business.study.model.entity.StudyRoomUserTokenDO;
import top.continew.admin.business.study.service.token.StudyRoomTokenService;

import java.util.Objects;

/**
 * 系统用户与小程序 token 映射服务实现
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Service
@RequiredArgsConstructor
public class StudyRoomTokenServiceImpl implements StudyRoomTokenService {

    private final StudyRoomUserTokenMapper tokenMapper;
    private final StudyRoomUserClient studyRoomUserClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Long userId, String token) {
        LambdaQueryWrapper<StudyRoomUserTokenDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomUserTokenDO::getUserId, userId);
        StudyRoomUserTokenDO existing = tokenMapper.selectOne(lqw);
        if (Objects.nonNull(existing)) {
            existing.setToken(token);
            tokenMapper.updateById(existing);
        } else {
            StudyRoomUserTokenDO entity = new StudyRoomUserTokenDO();
            entity.setUserId(userId);
            entity.setToken(token);
            tokenMapper.insert(entity);
        }

        this.validateToken();
    }

    @Override
    public String get(Long userId) {
        LambdaQueryWrapper<StudyRoomUserTokenDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomUserTokenDO::getUserId, userId);
        StudyRoomUserTokenDO entity = tokenMapper.selectOne(lqw);
        return Objects.nonNull(entity) ? entity.getToken() : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long userId) {
        LambdaQueryWrapper<StudyRoomUserTokenDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomUserTokenDO::getUserId, userId);
        tokenMapper.delete(lqw);
    }

    private void validateToken() {
        studyRoomUserClient.userDetail();
    }
}
