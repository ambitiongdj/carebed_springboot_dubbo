package com.carebed.business.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.carebed.common.annotation.Excel;
import com.carebed.common.core.domain.BaseEntity;

/**
 * 微信用户对象 t_wechat_user
 * 
 * @author wjl
 * @date 2020-06-17
 */
public class TWechatUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 会员ID */
    @Excel(name = "会员ID")
    private Long memberId;

    /** 微信open_id */
    @Excel(name = "微信open_id")
    private String openId;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nick;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 头像 */
    @Excel(name = "头像")
    private String headImgUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMemberId(Long memberId) 
    {
        this.memberId = memberId;
    }

    public Long getMemberId() 
    {
        return memberId;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setNick(String nick) 
    {
        this.nick = nick;
    }

    public String getNick() 
    {
        return nick;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setHeadImgUrl(String headImgUrl) 
    {
        this.headImgUrl = headImgUrl;
    }

    public String getHeadImgUrl() 
    {
        return headImgUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("openId", getOpenId())
            .append("sex", getSex())
            .append("nick", getNick())
            .append("country", getCountry())
            .append("province", getProvince())
            .append("city", getCity())
            .append("headImgUrl", getHeadImgUrl())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
