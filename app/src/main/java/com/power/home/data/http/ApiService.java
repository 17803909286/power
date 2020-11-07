package com.power.home.data.http;

import com.power.home.data.bean.AllCourseBean;
import com.power.home.data.bean.AreaBean;
import com.power.home.data.bean.BankInfoBean;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.BusinessGrowthBean;
import com.power.home.data.bean.CertificationBean;
import com.power.home.data.bean.ChampDetailBean;
import com.power.home.data.bean.ChampGuideBean;
import com.power.home.data.bean.ConvertRecordOutBean;
import com.power.home.data.bean.CourseFreeDailyBean;
import com.power.home.data.bean.CourseOfflineBean;
import com.power.home.data.bean.CoursePlayerBean;
import com.power.home.data.bean.EMBAOnlineBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.bean.EveryDayBean;
import com.power.home.data.bean.FriendsBean;
import com.power.home.data.bean.GiftBean;
import com.power.home.data.bean.GoShareBean;
import com.power.home.data.bean.HomePagePopupData;
import com.power.home.data.bean.InviteRecordBean;
import com.power.home.data.bean.MainHomeBean;
import com.power.home.data.bean.MainK12Bean;
import com.power.home.data.bean.MessageOutBean;
import com.power.home.data.bean.MoneyOutRecordBean;
import com.power.home.data.bean.OrderOutBean;
import com.power.home.data.bean.PayChannelBean;
import com.power.home.data.bean.PayOrderBean;
import com.power.home.data.bean.PersonCenterBean;
import com.power.home.data.bean.ReceiveMaterialBean;
import com.power.home.data.bean.SearchBean;
import com.power.home.data.bean.SearchHotWordBean;
import com.power.home.data.bean.StudyPlanBean;
import com.power.home.data.bean.StudyRecordsBean;
import com.power.home.data.bean.TeamInfoBean;
import com.power.home.data.bean.TokenBean;
import com.power.home.data.bean.UserAssetsBean;
import com.power.home.data.bean.UserInfoBean;
import com.power.home.data.bean.UserInfoChildBean;
import com.power.home.data.bean.VersionBean;
import com.power.home.data.bean.VipBean;
import com.power.home.data.bean.VipRecordBean;
import com.power.home.data.bean.WithdrawInfoBean;
import com.power.home.data.bean.WithdrawOutBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ZHP on 2017/6/25.
 */

public interface ApiService {

    //首页接口
    @GET("/api/page/getHomePage")
    Observable<BaseBean<MainHomeBean>> getHomeData();

    //K12接口(学霸课堂)
    @GET("/api/page/getK12Page")
    Observable<BaseBean<MainK12Bean>> getK12Data();

    //获取验证码接口
    @GET("/api/login/vCode/{mobile}")
    Observable<BaseBean<EmptyBean>> getCode(@Path("mobile") String mobile);

    //登录接口
    @POST("/api/login/login")
    Observable<BaseBean<UserInfoBean>> login(@Body RequestBody body);

    //全部课程
    @GET("/api/courseModule/findAll")
    Observable<BaseBean<List<AllCourseBean>>> findAllCourse(@Query("type") String type);

    //全部免费课程
    @GET("/api/course/freeCourse")
    Observable<BaseBean<List<CourseFreeDailyBean>>> getFreeCourse(@Query("page") String pageNumber, @Query("size") String pageSize, @Query("type") String type);

    //动力5分钟
    @GET("/api/courseTopic/findDailyCourse")
    Observable<BaseBean<List<CourseFreeDailyBean>>> getDailyCourse(@Query("page") String pageNumber, @Query("size") String pageSize);

    //查询用户资料
    @GET("/api/auth/appUser")
    Observable<BaseBean<UserInfoChildBean>> getUserInfo(@Query("userId") String userId);

    //搜索热词
    @GET("/api/searchWords/findAll")
    Observable<BaseBean<List<SearchHotWordBean>>> searchWords();

    //搜索
    @GET("/api/course/search")
    Observable<BaseBean<List<SearchBean>>> search(@Query("title") String title);

    //在线EMBA
    @GET("/api/emba/getEmba")
    Observable<BaseBean<EMBAOnlineBean>> getEmbaData();

    //动力营
    @GET("/api/growing/getGrowing")
    Observable<BaseBean<BusinessGrowthBean>> getGroupingData();

    //⽤户上传图⽚
    @Multipart
    @POST("/api/auth/appUser/avatar/{userId}")
    Observable<BaseBean<String>> uploadPhoto(@Path("userId") String userId, @Part MultipartBody.Part file);

    //用户资料编辑
    @PUT("/api/auth/appUser")
    Observable<BaseBean<String>> saveInfo(@Body RequestBody body);

    //填写邀请码
    @PUT("/api/login/setUserInvitedCode")
    Observable<BaseBean<EmptyBean>> setUserInvitedCode(@Body RequestBody body);

    //更换手机号
    @PUT("/api/auth/appUser/mobile")
    Observable<BaseBean<EmptyBean>> changeMobile(@Body RequestBody body);

    //兑换码兑换
    @PUT("/api/auth/codeMarket")
    Observable<BaseBean<String>> codeMarket(@Body RequestBody body);

    //学习记录
    @GET("/api/studyProgress/getStudyProgress")
    Observable<BaseBean<List<StudyRecordsBean>>> getStudyProgress();

    //邀请好友
    @GET("/api/auth/inviteFriends")
    Observable<BaseBean<FriendsBean>> inviteFriends(@Query("userId") String userId);

    //邀请记录
    @GET("/api/auth/appUser/getInvitedDataByType")
    Observable<BaseBean<List<InviteRecordBean>>> getInviteRecord(@Query("userId") String userId, @Query("page") String page, @Query("size") String size, @Query("queryType") String queryType);

    //账户资料
    @GET("/api/auth/appUserAssets")
    Observable<BaseBean<UserAssetsBean>> getAppUserAssets(@Query("userId") String userId);

    //提现信息
    @GET("/api/auth/withdrawInfo")
    Observable<BaseBean<WithdrawInfoBean>> getWithdrawInfo(@Query("userId") String userId);

    //提现
    @PUT("/api/auth/withdrawInfo/withdraw")
    Observable<BaseBean<EmptyBean>> withdraw(@Body RequestBody body);

    //线下活动、线下课程
    @GET("/api/offlineActivity/findAllByPage/{pageNumber}/{pageSize}")
    Observable<BaseBean<List<CourseOfflineBean>>> getOfflineCourse(@Path("pageNumber") String pageNumber, @Path("pageSize") String pageSize);

    //兑换记录
    @GET("/api/auth/codeMarketRecord")
    Observable<BaseBean<ConvertRecordOutBean>> getCodeMarketRecord(@Query("page") String page, @Query("size") String size, @Query("userId") String userId);

    //查询已购订单列表
    @GET("/api/auth/orderMaster")
    Observable<BaseBean<OrderOutBean>> getOrderMasterRecord(@Query("page") String page, @Query("size") String size, @Query("userId") String userId);

    //提现记录
    @GET("/api/auth/withdrawInfo/list")
    Observable<BaseBean<WithdrawOutBean>> getWithdrawRecord(@Query("page") String page, @Query("size") String size, @Query("userId") String userId);

    //查询用户消息
    @GET("/api/auth/userNotify")
    Observable<BaseBean<MessageOutBean>> getSpreadNotifies(@Query("page") String page, @Query("size") String size, @Query("userId") String userId);

    //根据专辑id查询课程列表
    @GET("/api/course/findDisplayDetailByTypeAndId/{type}/{id}")
    Observable<BaseBean<CoursePlayerBean>> getCourseDetails(@Path("type") String type, @Path("id") String id);

    //获取学习计划
    @GET("/api/studyPlan/getStudyPlan")
    Observable<BaseBean<StudyPlanBean>> getStudyPlan();

    //设置学习记录
    @POST("/api/studyProgress/setStudyProgress")
    Observable<BaseBean<EmptyBean>> setStudyProgress(@Body RequestBody body);

    //个人中心
    @GET("/api/page/myPage")
    Observable<BaseBean<PersonCenterBean>> myPage(@Query("userId") String userId);

    //退出登录
    @DELETE("/api/auth/appUser/logout")
    Observable<BaseBean<EmptyBean>> logout();

    //APP版本升级接口
    @GET("/api/appversion/checkAppVersion")
    Observable<BaseBean<VersionBean>> getVersion();

    //微信登录授权
    @GET("/api/oauth/weChatLogin")
    Observable<BaseBean<UserInfoBean>> weChatLogin(@Query("code") String code);

    //第三方登录 绑定用户手机号
    @POST("/api/oauth/mobile")
    Observable<BaseBean<UserInfoBean>> bindPhone(@Body RequestBody body);

    //统一下单接口
    @POST("/api/auth/pay/create_order")
    Observable<BaseBean<PayOrderBean>> createOrder(@Body RequestBody body);

    //查询支付渠道
    @GET("/api/auth/payChannel")
    Observable<BaseBean<List<PayChannelBean>>> getPayChannel(@Query("payPlatform") String payPlatform);

    //收入明细
    @GET("/api/auth/incomeInfo")
    Observable<BaseBean<MoneyOutRecordBean>> getMoneyIn(@Query("userId") String userId, @Query("page") String page, @Query("size") String size, @Query("incomeType") String incomeType);

    //确认报名
    @POST("/api/offlineActivity/enroll")
    Observable<BaseBean<EmptyBean>> enroll(@Body RequestBody body);

    //赠送
    @GET("/api/auth/appUserAssets/share")
    Observable<BaseBean<GoShareBean>> goShare();

    //我的团队
    @GET("/api/auth/appUser/getMyTeam/{userId}")
    Observable<BaseBean<TeamInfoBean>> getTeamInfo(@Path("userId") String userId);

    //获得线下课程详情
    @GET("/api/offlineActivity/findById/{id}")
    Observable<BaseBean<CourseOfflineBean>> getOfflineCourseDetails(@Path("id") String id);

    //会员专区
    @GET("/api/happiness/getVipArea")
    Observable<BaseBean<VipBean>> getVipInfo();

    //动力营名额明细
    @GET("/api/auth/incomeInfo/getGrowingDetails")
    Observable<BaseBean<List<VipRecordBean>>> getVipRecord(@Query("objectUserId") String objectUserId, @Query("page") String page, @Query("size") String size);

    //领取资料
    @GET("/api/material/materialContactPerson/findOneWithRoundRobin")
    Observable<BaseBean<ReceiveMaterialBean>> receiveMaterial();

    //获取首页弹窗
    @GET("/api/page/getHomePagePopup")
    Observable<BaseBean<List<HomePagePopupData>>> getHomePagePopup();

    //获取最近搜索
    @GET("/api/searchWords/findNearWord")
    Observable<BaseBean<List<String>>> getFindNearWord();

    //清空最近搜索
    @DELETE("/api/searchWords/deleteAll")
    Observable<BaseBean<EmptyBean>> deleteAll();

    //状元指导
    @GET("/api/courseTopic/getChamp")
    Observable<BaseBean<List<ChampGuideBean>>> getChamp();

    //状元信息
    @GET("/api/courseTopic/getChampDetails")
    Observable<BaseBean<ChampDetailBean>> getChampDetails(@Query("id") String id, @Query("page") String page, @Query("size") String size);

    //查询省市
    @GET("/api/zoneProvince/findAll")
    Observable<BaseBean<List<AreaBean>>> getProvince();

    //实名认证第一步
    @Multipart
    @POST("/api/auth/appUserAssets/realNameAuth")
    Observable<BaseBean<CertificationBean>> uploadIdCard(@PartMap Map<String, RequestBody> body);

    //实名认证第二步
    @POST("/api/auth/appUserAssets/realNameAuthSave")
    Observable<BaseBean<EmptyBean>> realNameAuthSave(@Body RequestBody body);

    //获取银行卡信息
    @GET("/api/auth/appUserAssets/getBankCardInfo")
    Observable<BaseBean<BankInfoBean>> getBankCardInfo(@Query("userId") String userId);

    //绑定、修改银行卡信息
    @POST("/api/auth/appUserAssets/bindBankCardInfo")
    Observable<BaseBean<BankInfoBean>> bindBankCardInfo(@Body RequestBody body);

    //每日活动
    @GET("/api/auth/activity")
    Observable<BaseBean<List<EveryDayBean>>> getEveryDayInfo(@Query("userId") String userId);

    //每日活动领奖品
    @GET("/api/auth/activity/activityGift")
    Observable<BaseBean<GiftBean>> activityGift(@Query("dailyId") String dailyId, @Query("userId") String userId);

    //获取七牛云tocken
    @POST("/api/getToken")
    Observable<BaseBean<TokenBean>> getUploadToken();
    //实名认证
    @POST("/api/auth/appUserAssets/realNameAuth/v2")
    Observable<BaseBean<CertificationBean>> getPersonVerifyResult(@Body RequestBody body);

}
