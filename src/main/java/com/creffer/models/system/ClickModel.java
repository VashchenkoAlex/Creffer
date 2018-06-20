package com.creffer.models.system;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "clicks")
public class ClickModel {
    @Column(name = "click_count_id")
    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    private UUID countId;
    @Column
    private String transactionId;
    @Column
    private int offerId;
    @Column
    private int pubId;
    @Column
    private String subPubId;
    @Column
    private String clickIp;
    @Column
    private String clickCountry;
    @Column
    private String deviceBrand;
    @Column
    private String deviceModel;
    @Column
    private String deviceOs;
    @Column
    private String deviceOsVersion;
    @Column
    private String deviceId;
    @Column
    private String macAddress;
    @Column
    private String devicePlatform;
    @Column
    private String idfaGaid;
    @Column
    private Date clickDate;
    @Column
    private int advId;
    @Column
    private String pageLanguage;
    @Column
    private String clickForwarded;
    @Column
    private String referer;
    @Column
    private String userAgent;
    @Column
    private String viaProxy;

    public ClickModel() {
    }

    public ClickModel(String transactionId, int offerId, int pubId, String subPubId, String clickIp, String clickCountry, String deviceBrand, String deviceModel, String deviceOs, String deviceOsVersion, String deviceId, String macAddress, String devicePlatform, String idfaGaid, Date clickDate, int advId, String pageLanguage, String clickForwarded, String referer, String userAgent, String viaProxy) {
        this.transactionId = transactionId;
        this.offerId = offerId;
        this.pubId = pubId;
        this.subPubId = subPubId;
        this.clickIp = clickIp;
        this.clickCountry = clickCountry;
        this.deviceBrand = deviceBrand;
        this.deviceModel = deviceModel;
        this.deviceOs = deviceOs;
        this.deviceOsVersion = deviceOsVersion;
        this.deviceId = deviceId;
        this.macAddress = macAddress;
        this.devicePlatform = devicePlatform;
        this.idfaGaid = idfaGaid;
        this.clickDate = clickDate;
        this.advId = advId;
        this.pageLanguage = pageLanguage;
        this.clickForwarded = clickForwarded;
        this.referer = referer;
        this.userAgent = userAgent;
        this.viaProxy = viaProxy;
    }

    //Getters and setters

    public UUID getCountId() {
        return countId;
    }

    public void setCountId(UUID countId) {
        this.countId = countId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getPubId() {
        return pubId;
    }

    public void setPubId(int pubId) {
        this.pubId = pubId;
    }

    public String getClickIp() {
        return clickIp;
    }

    public void setClickIp(String clickIp) {
        this.clickIp = clickIp;
    }

    public String getClickCountry() {
        return clickCountry;
    }

    public void setClickCountry(String clickCountry) {
        this.clickCountry = clickCountry;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    public String getDeviceOsVersion() {
        return deviceOsVersion;
    }

    public void setDeviceOsVersion(String deviceOsVersion) {
        this.deviceOsVersion = deviceOsVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getDevicePlatform() {
        return devicePlatform;
    }

    public void setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

    public String getIdfaGaid() {
        return idfaGaid;
    }

    public void setIdfaGaid(String idfaGaid) {
        this.idfaGaid = idfaGaid;
    }

    public Date getClickDate() {
        return clickDate;
    }

    public void setClickDate(Date clickDate) {
        this.clickDate = clickDate;
    }

    public int getAdvId() {
        return advId;
    }

    public void setAdvId(int advId) {
        this.advId = advId;
    }

    public String getPageLanguage() {
        return pageLanguage;
    }

    public void setPageLanguage(String pageLanguage) {
        this.pageLanguage = pageLanguage;
    }

    public String getClickForwarded() {
        return clickForwarded;
    }

    public void setClickForwarded(String clickForwarded) {
        this.clickForwarded = clickForwarded;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getViaProxy() {
        return viaProxy;
    }

    public void setViaProxy(String viaProxy) {
        this.viaProxy = viaProxy;
    }

    public String getSubPubId() {
        return subPubId;
    }

    public void setSubPubId(String subPubId) {
        this.subPubId = subPubId;
    }
    //Override hashcode and equals


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClickModel that = (ClickModel) o;
        return offerId == that.offerId &&
                pubId == that.pubId &&
                advId == that.advId &&
                Objects.equals(countId, that.countId) &&
                Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(subPubId, that.subPubId) &&
                Objects.equals(clickIp, that.clickIp) &&
                Objects.equals(clickCountry, that.clickCountry) &&
                Objects.equals(deviceBrand, that.deviceBrand) &&
                Objects.equals(deviceModel, that.deviceModel) &&
                Objects.equals(deviceOs, that.deviceOs) &&
                Objects.equals(deviceOsVersion, that.deviceOsVersion) &&
                Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(macAddress, that.macAddress) &&
                Objects.equals(devicePlatform, that.devicePlatform) &&
                Objects.equals(idfaGaid, that.idfaGaid) &&
                Objects.equals(clickDate, that.clickDate) &&
                Objects.equals(pageLanguage, that.pageLanguage) &&
                Objects.equals(clickForwarded, that.clickForwarded) &&
                Objects.equals(referer, that.referer) &&
                Objects.equals(userAgent, that.userAgent) &&
                Objects.equals(viaProxy, that.viaProxy);
    }

    @Override
    public int hashCode() {

        return Objects.hash(countId, transactionId, offerId, pubId, subPubId, clickIp, clickCountry, deviceBrand, deviceModel, deviceOs, deviceOsVersion, deviceId, macAddress, devicePlatform, idfaGaid, clickDate, advId, pageLanguage, clickForwarded, referer, userAgent, viaProxy);
    }
}
