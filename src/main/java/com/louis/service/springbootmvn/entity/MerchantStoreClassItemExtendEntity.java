package com.louis.service.springbootmvn.entity;

import java.util.Date;

/**
 * MerchantStoreClassItemExtendEntity
 *
 * @author jipengcheng
 */
public class MerchantStoreClassItemExtendEntity {
    private Integer id;

    private Integer merchantId;

    private Integer storeId;

    private Integer classId;

    private Integer itemId;

    private Integer valueId;

    private Integer sortOrder;

    private String majorUrl;

    private String hoverUrl;

    private Byte isDeleted;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getMajorUrl() {
        return majorUrl;
    }

    public void setMajorUrl(String majorUrl) {
        this.majorUrl = majorUrl == null ? null : majorUrl.trim();
    }

    public String getHoverUrl() {
        return hoverUrl;
    }

    public void setHoverUrl(String hoverUrl) {
        this.hoverUrl = hoverUrl == null ? null : hoverUrl.trim();
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}