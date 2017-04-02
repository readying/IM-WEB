package com.gd.domain.userinfo;

import com.gd.domain.base.BaseModel;

/**
 * Created by dell on 2017/1/12.
 * Good Luck !
 * へ　　　　　／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */
@SuppressWarnings("unused")
//id,ifuse,createTime,updateTime,orderNum,realName,phone,mail,org,station,gender,nation,nativePlace,birthDate,politicalStatus,maritalStatus,nickName,picture,policeNum,identityCode,qr,qq,weChat,jobCode,autoGraph,homeAddress,officeAddress,otherAddress,officeTelephone,otherTelephone,ifHideNum
public class UserInfo extends BaseModel {
    private String realName;
    private String phone;
    private String mail;
    private String org;
    private String station;
    //张伯轩add
    private String gender;
    private String nation;
    private String nativePlace;
    private String birthDate;
    private String politicalStatus;
    private String maritalStatus;
    private String nickName;
    private String picture;
    private String policeNum;
    private String identityCode;
    private String qr;
    private String qq;
    private String weChat;
    private String jobCode;
    private String autoGraph;
    private String homeAddress;

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setPoliceNum(String policeNum) {
        this.policeNum = policeNum;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public void setAutoGraph(String autoGraph) {
        this.autoGraph = autoGraph;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public void setOtherAddress(String otherAddress) {
        this.otherAddress = otherAddress;
    }

    public void setOfficeTelephone(String officeTelephone) {
        this.officeTelephone = officeTelephone;
    }

    public void setOtherTelephone(String otherTelephone) {
        this.otherTelephone = otherTelephone;
    }

    public void setIfHideNum(String ifHideNum) {
        this.ifHideNum = ifHideNum;
    }

    public String getNation() {

        return nation;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPicture() {
        return picture;
    }

    public String getPoliceNum() {
        return policeNum;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public String getQr() {
        return qr;
    }

    public String getQq() {
        return qq;
    }

    public String getWeChat() {
        return weChat;
    }

    public String getJobCode() {
        return jobCode;
    }

    public String getAutoGraph() {
        return autoGraph;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public String getOtherAddress() {
        return otherAddress;
    }

    public String getOfficeTelephone() {
        return officeTelephone;
    }

    public String getOtherTelephone() {
        return otherTelephone;
    }

    public String getIfHideNum() {
        return ifHideNum;
    }

    private String officeAddress;
    private String otherAddress;
    private String officeTelephone;
    private String otherTelephone;
    private String ifHideNum;
    //张伯轩add
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
