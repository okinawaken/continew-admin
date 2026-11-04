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

package top.continew.admin.business.study.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.client.http.StudyRoomVenueClient;
import top.continew.admin.business.study.client.model.resp.StudyRoomAreaSelectRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomDouYinVenueSelectRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomReserveIndexRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomVenueRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomVenueSelectRespDTO;
import top.continew.admin.business.study.model.resp.StudyRoomAreaSelectVO;
import top.continew.admin.business.study.model.resp.StudyRoomDouYinVenueSelectVO;
import top.continew.admin.business.study.model.resp.StudyRoomReserveIndexVO;
import top.continew.admin.business.study.model.resp.StudyRoomVenueSelectVO;
import top.continew.admin.business.study.model.resp.StudyRoomVenueVO;
import top.continew.starter.web.model.R;

/**
 * 自习室门店域接口
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/venue")
public class StudyRoomVenueController {

    private final StudyRoomVenueClient venueClient;

    /**
     * 按定位查询附近门店（签到首页、报修页用）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @SaCheckPermission("business:study:venue:list")
    @GetMapping(value = "/select")
    public R<StudyRoomVenueSelectVO> venueSelect(
        @RequestParam(value = "currentLng") Double currentLng,
        @RequestParam(value = "currentLat") Double currentLat) {
        StudyRoomVenueSelectRespDTO dto = venueClient.venueSelect(currentLng, currentLat).getData();
        StudyRoomVenueSelectVO vo = BeanUtil.copyProperties(dto, StudyRoomVenueSelectVO.class);
        return R.ok(vo);
    }

    /**
     * 预约可选门店列表（无定位参数）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:venue:list")
    @GetMapping(value = "/reserve/select")
    public R<StudyRoomVenueSelectVO> reserveVenueSelect() {
        StudyRoomVenueSelectRespDTO dto = venueClient.reserveVenueSelect().getData();
        StudyRoomVenueSelectVO vo = BeanUtil.copyProperties(dto, StudyRoomVenueSelectVO.class);
        return R.ok(vo);
    }

    /**
     * 门店详情
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:venue:list")
    @GetMapping(value = "/reserve/detail")
    public R<StudyRoomVenueVO> reserveVenueDetail(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomVenueRespDTO dto = venueClient.reserveVenueDetail(venueId).getData();
        StudyRoomVenueVO vo = BeanUtil.copyProperties(dto, StudyRoomVenueVO.class);
        return R.ok(vo);
    }

    /**
     * 预约首页（banner + 门店列表 + 区域筛选）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @param venueName 门店名称
     * @param channelBili 渠道标识
     * @param areaId 区域 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:venue:list")
    @GetMapping(value = "/reserve/index")
    public R<StudyRoomReserveIndexVO> reserveIndex(
        @RequestParam(name = "currentLng", required = false) Double currentLng,
        @RequestParam(name = "currentLat", required = false) Double currentLat,
        @RequestParam(name = "venueName", required = false) String venueName,
        @RequestParam(name = "channelBili", required = false) Integer channelBili,
        @RequestParam(name = "areaId", required = false) Long areaId) {
        StudyRoomReserveIndexRespDTO dto = venueClient
            .reserveIndex(currentLng, currentLat, venueName, channelBili, areaId).getData();
        StudyRoomReserveIndexVO vo = BeanUtil.copyProperties(dto, StudyRoomReserveIndexVO.class);
        return R.ok(vo);
    }

    /**
     * 区域列表（预约首页筛选）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:venue:list")
    @GetMapping(value = "/reserve/area/select")
    public R<StudyRoomAreaSelectVO> reserveAreaSelect() {
        StudyRoomAreaSelectRespDTO dto = venueClient.reserveAreaSelect().getData();
        StudyRoomAreaSelectVO vo = BeanUtil.copyProperties(dto, StudyRoomAreaSelectVO.class);
        return R.ok(vo);
    }

    /**
     * 收银台门店列表（美团团购核销）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @SaCheckPermission("business:study:venue:list")
    @GetMapping(value = "/receipt/select")
    public R<StudyRoomVenueSelectVO> receiptVenueSelect(
        @RequestParam(value = "currentLng") Double currentLng,
        @RequestParam(value = "currentLat") Double currentLat) {
        StudyRoomVenueSelectRespDTO dto =
            venueClient.receiptVenueSelect(currentLng, currentLat).getData();
        StudyRoomVenueSelectVO vo = BeanUtil.copyProperties(dto, StudyRoomVenueSelectVO.class);
        return R.ok(vo);
    }

    /**
     * 抖音门店列表（比普通门店多 poiId / poiName）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @SaCheckPermission("business:study:venue:list")
    @GetMapping(value = "/douyin/select")
    public R<StudyRoomDouYinVenueSelectVO> douYinVenueSelect(
        @RequestParam(value = "currentLng") Double currentLng,
        @RequestParam(value = "currentLat") Double currentLat) {
        StudyRoomDouYinVenueSelectRespDTO dto =
            venueClient.douYinVenueSelect(currentLng, currentLat).getData();
        StudyRoomDouYinVenueSelectVO vo =
            BeanUtil.copyProperties(dto, StudyRoomDouYinVenueSelectVO.class);
        return R.ok(vo);
    }
}
