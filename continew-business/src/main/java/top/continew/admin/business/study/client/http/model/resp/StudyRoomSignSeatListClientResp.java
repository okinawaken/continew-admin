package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Objects;

/**
 * 签到选座响应数据（/mp/study/seat/list 的 {@code data}）
 * <p>
 * 该接口的 {@code data} 有两种形态：
 * <ul>
 * <li>普通用户：座位图对象，按 {@link StudyRoomSignSeatMapClientResp} 的字段解析；</li>
 * <li>存在待签到预约：上游直接完成签到，{@code data} 为整数 {@code 1}。</li>
 * </ul>
 * 这里用 Jackson 的委托构造器兼容整数形态，对象形态仍按普通 Bean 解析。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSignSeatListClientResp extends StudyRoomSignSeatMapClientResp {

    /**
     * 预约座位签到成功标志
     * <p>
     * 仅当上游直接完成预约座位签到时返回，值为 1
     */
    private Integer signInFlag;

    /**
     * 对象形态（普通座位图）走默认构造器。
     * <p>
     * 仅有委托构造器时 Jackson 会把对象也交给 {@code Integer} 反序列化并抛
     * {@code MismatchedInputException}，因此必须保留无参构造器。
     */
    public StudyRoomSignSeatListClientResp() {
    }

    /**
     * 兼容 {@code data} 为整数 {@code 1} 的响应
     *
     * @param signInFlag 参数 signInFlag
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public StudyRoomSignSeatListClientResp(Integer signInFlag) {
        this.signInFlag = signInFlag;
    }

    /**
     * 是否签到成功
     *
     * @return 是否满足条件
     */
    public boolean isSignInSuccess() {
        return Objects.equals(this.signInFlag, 1);
    }
}
