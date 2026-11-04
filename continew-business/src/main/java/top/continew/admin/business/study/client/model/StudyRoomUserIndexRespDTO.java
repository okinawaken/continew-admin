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

package top.continew.admin.business.study.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 小程序首页用户信息（/mp/user/index）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserIndexRespDTO {

    /**
     * 头像
     */
    private String headImg;

    /**
     * 头像是否已授权：1 是 0 否
     */
    private Integer headImgAuth;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 累计签到次数
     */
    private Integer signTotalNum;

    /**
     * 是否开启学习排行：true/false（字符串）
     */
    private String studyRankOpen;

    /**
     * 累计学习时长，如 12小时30分钟
     */
    private String studyTotalTimeStr;

    /**
     * 真实姓名
     */
    private String userName;
}
