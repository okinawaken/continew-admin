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

package top.continew.admin.business.study.filter.autosign.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.http.StudyRoomSignV2Client;
import top.continew.admin.business.study.client.model.resp.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomIndexRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomUserPackageRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomUserReserveRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomVenueRespDTO;
import top.continew.admin.business.study.enums.StudyRoomAutoSignFilterOrderEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autosign.StudyRoomAutoSignFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoSignContext;

import java.util.Objects;

/**
 * 签到扣费套餐查询过滤器
 * <p>
 * 按待签到预约的门店查询签到首页，取 {@code signInUserPackage.signInUserPackageId} 作为签到扣费套餐。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoSignPackageFilter implements StudyRoomAutoSignFilter {

    private final StudyRoomSignV2Client signV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoSignFilterOrderEnum.SIGN_IN_PACKAGE.getValue();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoSignContext context) {
        StudyRoomUserReserveRespDTO reserveRespDTO = context.getReserveRespDTO();
        StudyRoomVenueRespDTO venueRespDTO = context.getVenueRespDTO();

        StudyRoomApiResultDTO<StudyRoomIndexRespDTO> resultDTO =
            signV2Client.studyIndex(venueRespDTO.getVenueLng(), venueRespDTO.getVenueLat(),
                reserveRespDTO.getVenueId(), null, null, context.getUserId());
        if (Objects.isNull(resultDTO) || Objects.isNull(resultDTO.getData())
            || Objects.isNull(resultDTO.getData().getSignInUserPackage())) {
            return FilterResultDTO.fail("签到首页返回为空");
        }

        StudyRoomUserPackageRespDTO signInUserPackage = resultDTO.getData().getSignInUserPackage();
        context.setUserPackageRespDTO(signInUserPackage);
        return FilterResultDTO.success(signInUserPackage);
    }
}
